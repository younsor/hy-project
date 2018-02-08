package cn.zyy.zhichuan.webpd.service;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.zyy.oss.share.OssFunc;
import cn.zyy.zhichuan.webpd.domain.PromCode;
import cn.zyy.zhichuan.webpd.domain.TrackDetail;
import cn.zyy.zhichuan.webpd.dto.DtoBasicStatsChart;
import cn.zyy.zhichuan.webpd.dto.DtoBasicStatsTable;
import cn.zyy.zhichuan.webpd.dto.DtoMbStatArticle;
import cn.zyy.zhichuan.webpd.dto.DtoViewRecords;
import cn.zyy.zhichuan.webpd.repo.RepoPromCode;
import cn.zyy.zhichuan.webpd.repo.RepoTrackDetail;
import cn.zyy.zhichuan.webpd.repo.RepoTrackStats;

@Service
public class SrvImplTrackStats
{

    @Autowired
    private RepoTrackStats  trackStatsRepo;
    @Autowired
    private RepoTrackDetail trackDetailRepo;
    @Autowired
    private RepoPromCode    adCodeRepo;

    public List<DtoBasicStatsTable> getBasicStatsTable(Integer uid, Integer mid, Integer adid, String beginDate, String endDate)
    {
        beginDate = beginDate.trim();
        endDate = endDate.trim();
        boolean isDaily = true;
        if (beginDate.equals(endDate))
            isDaily = false;
        endDate = after24hour(endDate);

        List<Object[]> ls = getStats(uid, mid, adid, isDaily, beginDate, endDate);

        List<DtoBasicStatsTable> data;
        final boolean finalIsDaily = isDaily;
        StatsDataShowProcessor sp = new StatsDataShowProcessor(beginDate, endDate);
        if (null == ls)
            data = new ArrayList<>();
        else
        {

            List<Object[]> tmp = StreamSupport.stream(ls.spliterator(), false).map(i -> {
                return sp.process(i, finalIsDaily).stream();
            }).flatMap(s -> s).collect(toList());
            tmp.addAll(sp.end(isDaily));
            data = tmp.stream().map(item -> {
                DtoBasicStatsTable bst = new DtoBasicStatsTable();
                bst.time = (String) item[0];
                bst.impress = (long) item[1];
                bst.click = (long) item[2];
                bst.rate = (0 != bst.impress) ? (new DecimalFormat("#.##")).format(Double.valueOf(bst.click) / bst.impress * 100) : String.valueOf(bst.click);
                return bst;
            }).collect(toList());
        }

        return data;
    }

    public DtoBasicStatsChart getBasicStatsCharts(Integer uid, Integer mid, Integer adid, String quota, String beginDate, String endDate)
    {
        beginDate = beginDate.trim();
        endDate = endDate.trim();
        boolean isDaily = true;
        if (beginDate.equals(endDate))
            isDaily = false;
        endDate = after24hour(endDate);

        List<Object[]> ls = getStats(uid, mid, adid, isDaily, beginDate, endDate);
        if (null == ls)
            return null;

        List<String> x = new ArrayList<>();
        List<String> y = new ArrayList<>();
        final boolean finalIsDaily = isDaily;
        StatsDataShowProcessor sp = new StatsDataShowProcessor(beginDate, endDate);
        ls = ls.stream().map(i -> {
            return sp.process(i, finalIsDaily).stream();
        }).flatMap(s -> s).collect(toList());
        ls.addAll(sp.end(isDaily));
        ls.stream().forEach(item -> {
            String ts = (String) item[0];
            long impress = (long) item[1];
            long click = (long) item[2];

            x.add(ts);
            y.add(chartYTrans(quota, impress, click));
        });

        return getStatsChart(quota, x, y);
    }

    public long getPVByAdCode(Integer adid)
    {
        return trackStatsRepo.sumImpressByAdCode(adid);
    }

    public DtoViewRecords getViewDetailByAdCode(Integer adid, PageRequest page)
    {
        DtoViewRecords rslt = new DtoViewRecords();

        Page<TrackDetail> infos = trackDetailRepo.findByAdidAndIsClickOrderByUpdateTimeDesc(adid, 0, page);
        if (null != infos)
        {
            rslt.total = infos.getTotalElements();
            rslt.content = StreamSupport.stream(infos.spliterator(), false).map(i -> DtoViewRecords.ContentDetail.of(i)).collect(toList());
        }
        else
        {
            rslt.total = 0L;
        }

        return rslt;
    }

    public void saveTrackDetail(Integer adid, Integer articleId, boolean isClick, String ip, String os, String country, String province, String city)
    {
        TrackDetail td = new TrackDetail();
        td.setAdid(adid);
        td.setAid(articleId);
        td.setIsClick((isClick ? 1 : 0));
        if (null != ip)
            td.setIp(ip);
        td.setOs(os);
        td.setCountry(country);
        td.setProvince(province);
        td.setCity(city);

        // Timestamp ts = new Timestamp(new Date().getTime());
        // td.setUpdateTime(ts);

        trackDetailRepo.saveAndFlush(td);
    }

    public void increaseTrack(Integer adid, Integer articleId, boolean isClick)
    {
        PromCode adCode = adCodeRepo.findOne(adid);
        if (null == adCode)
            throw new RuntimeException("ad code not found!");

        long impress = 0;
        long click = 0;
        if (!isClick)
            impress = 1;
        else
            click = 1;

        trackStatsRepo.trackUpdate(adCode.getPdUserId(), adid, adCode.getPromCreativeId(), articleId, impress, click);
    }

    private String chartYTrans(String quota, long impress, long click)
    {
        if ("click".equals(quota))
        {
            return String.valueOf(click);
        }
        else if ("rate".equals(quota))
        {
            if (impress != 0)
                return (new DecimalFormat("#.##")).format(click * 100.0 / impress);
            else
                return String.valueOf(click);
        }
        else
        {
            return String.valueOf(impress);
        }
    }

    private static class StatsDataShowProcessor
    {
        public String beginDate;
        public String endDate;
        public long   beginTs;
        public long   endTs;

        public long   idxTs;

        public StatsDataShowProcessor(String beginDate, String endDate)
        {
            this.beginDate = beginDate;
            this.endDate = endDate;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try
            {
                beginTs = sdf.parse(this.beginDate).getTime();
                endTs = sdf.parse(this.endDate).getTime();
            }
            catch (ParseException e)
            {
                e.printStackTrace();
                throw new RuntimeException("invalid params: end date");
            }

            idxTs = beginTs;
        }

        public List<Object[]> process(Object[] row, boolean isDaily)
        {
            SimpleDateFormat sdf;
            if (isDaily)
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            else
                sdf = new SimpleDateFormat("yyyy-MM-dd HH");

            String tsStr = row[0].toString();
            long ts = 0;
            try
            {
                ts = sdf.parse(tsStr).getTime();
            }
            catch (Throwable e)
            {
                return null;
            }

            List<Object[]> rslt = generateBlankTs(ts, isDaily);
            if (isDaily)
                idxTs += 86400000;
            else
                idxTs += 3600000;

            Object[] tmp = new Object[3];
            tmp[0] = tsShowTrans(row[0].toString(), isDaily);
            tmp[1] = ((BigDecimal) row[1]).longValue();
            tmp[2] = ((BigDecimal) row[2]).longValue();
            rslt.add(tmp);

            return rslt;
        }

        public List<Object[]> end(boolean isDaily)
        {
            return generateBlankTs(endTs, isDaily);
        }

        private List<Object[]> generateBlankTs(long end, boolean isDaily)
        {
            SimpleDateFormat sdf;
            if (isDaily)
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            else
                sdf = new SimpleDateFormat("yyyy-MM-dd HH");

            List<Object[]> rslt = new ArrayList<>();
            while (idxTs < end)
            {
                Object[] tmp = new Object[3];
                String tmpTs = sdf.format(new Date(idxTs));
                tmp[0] = tsShowTrans(tmpTs, isDaily);
                tmp[1] = 0L;
                tmp[2] = 0L;
                rslt.add(tmp);
                if (isDaily)
                    idxTs += 86400000;
                else
                    idxTs += 3600000;
            }

            return rslt;
        }

        public static String tsShowTrans(String ts, boolean isDaily)
        {
            DecimalFormat df = new DecimalFormat("00");
            if (isDaily)
            {
                return ts;
            }
            else
            {
                int hour = Integer.valueOf(ts.split(" ")[1]);
                return df.format(hour) + "~" + df.format(hour + 1);
            }
        }
    }

    private DtoBasicStatsChart getStatsChart(String quota, List<String> x, List<String> y)
    {
        DtoBasicStatsChart.Series seriesItem = new DtoBasicStatsChart.Series();
        seriesItem.name = quota;
        seriesItem.type = "line";
        seriesItem.data = y;
        List<DtoBasicStatsChart.Series> series = new ArrayList<>();
        series.add(seriesItem);

        DtoBasicStatsChart chart = new DtoBasicStatsChart();
        chart.setSeries(series);
        chart.setxAxis(x);

        return chart;
    }

    private String after24hour(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            return sdf.format(sdf.parse(date).getTime() + 86400000);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            throw new RuntimeException("invalid params: end date");
        }
    }

    private Page<Object[]> getStatsByPage(Integer uid, Integer mid, Integer adid, boolean isDaily, String beginDate, String endDate, PageRequest page)
    {
        Page<Object[]> rslt;
        if (isDaily)
        {
            if (null != adid && null != mid)
            {
                rslt = trackStatsRepo.sumByUserIdAndMidAndIdAndTsBetweenDailyPageable(uid, mid, adid, beginDate, endDate, page);
            }
            else if (null != adid)
            {
                rslt = trackStatsRepo.sumByUserIdAndIdAndTsBetweenDailyPageable(uid, adid, beginDate, endDate, page);
            }
            else if (null != mid)
            {
                rslt = trackStatsRepo.sumByUserIdAndMidAndTsBetweenDailyPageable(uid, mid, beginDate, endDate, page);
            }
            else
            {
                rslt = trackStatsRepo.sumByUserIdAndTsBetweenDailyPageable(uid, beginDate, endDate, page);
            }
        }
        else
        {
            if (null != adid && null != mid)
            {
                rslt = trackStatsRepo.sumByUserIdAndMidAndIdAndTsBetweenHourlyPageable(uid, mid, adid, beginDate, endDate, page);
            }
            else if (null != adid)
            {
                rslt = trackStatsRepo.sumByUserIdAndIdAndTsBetweenHourlyPageable(uid, adid, beginDate, endDate, page);
            }
            else if (null != mid)
            {
                rslt = trackStatsRepo.sumByUserIdAndMidAndTsBetweenHourlyPageable(uid, mid, beginDate, endDate, page);
            }
            else
            {
                rslt = trackStatsRepo.sumByUserIdAndTsBetweenHourlyPageable(uid, beginDate, endDate, page);
            }
        }

        return rslt;
    }

    private List<Object[]> getStats(Integer uid, Integer mid, Integer adid, boolean isDaily, String beginDate, String endDate)
    {
        List<Object[]> rslt;
        if (isDaily)
        {
            if (null != adid && null != mid)
            {
                rslt = trackStatsRepo.sumByUserIdAndMidAndIdAndTsBetweenDaily(uid, mid, adid, beginDate, endDate);
            }
            else if (null != adid)
            {
                rslt = trackStatsRepo.sumByUserIdAndIdAndTsBetweenDaily(uid, adid, beginDate, endDate);
            }
            else if (null != mid)
            {
                rslt = trackStatsRepo.sumByUserIdAndMidAndTsBetweenDaily(uid, mid, beginDate, endDate);
            }
            else
            {
                rslt = trackStatsRepo.sumByUserIdAndTsBetweenDaily(uid, beginDate, endDate);
            }
        }
        else
        {
            if (null != adid && null != mid)
            {
                rslt = trackStatsRepo.sumByUserIdAndMidAndIdAndTsBetweenHourly(uid, mid, adid, beginDate, endDate);
            }
            else if (null != adid)
            {
                rslt = trackStatsRepo.sumByUserIdAndIdAndTsBetweenHourly(uid, adid, beginDate, endDate);
            }
            else if (null != mid)
            {
                rslt = trackStatsRepo.sumByUserIdAndMidAndTsBetweenHourly(uid, mid, beginDate, endDate);
            }
            else
            {
                rslt = trackStatsRepo.sumByUserIdAndTsBetweenHourly(uid, beginDate, endDate);
            }
        }

        return rslt;
    }

    public List<DtoMbStatArticle> getArticleStat4PromCode(String promCodeShowId)
    {
        List<DtoMbStatArticle> lstMbStatArticle = Lists.newArrayList();

        PromCode promCode = adCodeRepo.findByShowId(promCodeShowId);
        if (null == promCode)
        {
            return lstMbStatArticle;
        }

        List<Object[]> lstObjectArray = trackDetailRepo.getArticleDetail4PromCode(promCode.getId());
        if (null == lstObjectArray)
        {
            return lstMbStatArticle;
        }

        for (Object[] arrayObject : lstObjectArray)
        {
            DtoMbStatArticle mbStatArticle = new DtoMbStatArticle();
            mbStatArticle.id = OssFunc.DataConvert.toStr(arrayObject[0], "");
            mbStatArticle.title = OssFunc.DataConvert.toStr(arrayObject[1], "");
            mbStatArticle.ts = OssFunc.DataConvert.toStr(arrayObject[2], "");
            mbStatArticle.pv = OssFunc.DataConvert.toInt(arrayObject[3], 0);

            lstMbStatArticle.add(mbStatArticle);
        }

        return lstMbStatArticle;
    }

    public int getArticleNum4PromCode(String promCodeShowId)
    {
        PromCode promCode = adCodeRepo.findByShowId(promCodeShowId);
        if (null == promCode)
        {
            return 0;
        }

        List<Object> lstResult = trackDetailRepo.getArticleNum4PromCode(promCode.getId());
        if (null == lstResult || lstResult.size() <= 0)
        {
            return 0;
        }

        return Integer.parseInt(lstResult.get(0).toString());
    }

    public int getArticlePv4PromCode(String promCodeShowId)
    {
        PromCode promCode = adCodeRepo.findByShowId(promCodeShowId);
        if (null == promCode)
        {
            return 0;
        }

        List<Object> lstResult = trackDetailRepo.getArticlePv4PromCode(promCode.getId());
        if (null == lstResult || lstResult.size() <= 0)
        {
            return 0;
        }

        return Integer.parseInt(lstResult.get(0).toString());
    }
}

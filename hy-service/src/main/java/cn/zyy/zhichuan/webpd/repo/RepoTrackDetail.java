package cn.zyy.zhichuan.webpd.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.zyy.zhichuan.webpd.domain.TrackDetail;

public interface RepoTrackDetail extends JpaRepository<TrackDetail, Integer>
{
    @Query(value = "select count(distinct(aid)) from track_detail where adid=?1", nativeQuery = true)
    List<Object> getArticleNum4PromCode(int promCodeId);

    /**
    select 
            lt.aid as artid, 
            rt.title as title, 
            rt.appear_time as ts, 
            count(*) as pv,
            max(lt.update_time) as sortts
    from 
            track_detail lt LEFT JOIN article_share rt on lt.aid=rt.id 
    where 
            adid=35 and is_click=0
    group by 
            artid, title
    order by 
            sortts desc
     * */
    @Query(value = "select lt.aid as artid, rt.title as title, rt.appear_time as ts, count(*) as pv, max(lt.update_time) as sortts from track_detail lt LEFT JOIN article_share rt on lt.aid=rt.id where adid=?1 and is_click=0 group by artid, title order by sortts desc", nativeQuery = true)
    List<Object[]> getArticleDetail4PromCode(int promCodeId);

    @Query(value = "select count(*) from track_detail where adid=?1 and is_click=false", nativeQuery = true)
    List<Object> getArticlePv4PromCode(int promCodeId);

    Page<TrackDetail> findByAdidAndIsClickOrderByUpdateTimeDesc(int id, int isClick, Pageable pageable);
}

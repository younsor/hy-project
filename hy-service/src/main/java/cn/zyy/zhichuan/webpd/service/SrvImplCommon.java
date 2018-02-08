package cn.zyy.zhichuan.webpd.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zyy.oss.share.OssLog;
import cn.zyy.zhichuan.webpd.domain.PdModule;
import cn.zyy.zhichuan.webpd.dto.DtoPdModule;
import cn.zyy.zhichuan.webpd.repo.RepoPdModule;

@Service
public class SrvImplCommon
{
    private static final OssLog log = new OssLog();

    @Autowired
    private RepoPdModule        repoPdModule;

    public List<DtoPdModule> getPdModules()
    {
        List<PdModule> lstPdModule = repoPdModule.findByIsDelOrderBySort(0);
        if (null == lstPdModule || lstPdModule.isEmpty())
        {
            return new ArrayList<>();
        }
        Map<Integer, List<DtoPdModule>> mtmp = new HashMap<>();

        for (int i = 0; i < lstPdModule.size(); i++)
        {
            PdModule iPdModule = lstPdModule.get(i);

            // 获取这个节点的children节点
            List<DtoPdModule> children = mtmp.get(iPdModule.getId());
            if (null == children)
            {
                children = new ArrayList<>();
                mtmp.put(iPdModule.getId(), children);
            }

            // 将children节点插入当前节点的children字段中
            DtoPdModule m = DtoPdModule.of(iPdModule);
            m.children = children;

            // 将当前节点加入父节点列表中
            List<DtoPdModule> vs = mtmp.get(iPdModule.getParentId());
            if (null == vs)
            {
                vs = new ArrayList<>();
                mtmp.put(iPdModule.getParentId(), vs);
            }
            vs.add(m);
        }

        return mtmp.get(0);
    }
}

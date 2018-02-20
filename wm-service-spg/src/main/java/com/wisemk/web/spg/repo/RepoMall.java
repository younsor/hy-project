package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisemk.web.spg.domain.Mall;

public interface RepoMall extends JpaRepository<Mall, Integer>
{
    Mall findByAppidAndDel(String appid, int del);

    Mall findByIdAndDel(Integer id, int del);

    /*
        select 
            rt.`value`,
            lt.landing_type, 
            lt.landing_info,
            lt.sort
        from 
            mall_roll lt LEFT JOIN mall_asset rt on lt.asset_id=rt.id
        where 
            lt.mall_id=1
        ORDER BY lt.sort asc
     * */
    @Query(value = "select rt.`value`, lt.landing_type, lt.landing_info from mall_roll lt LEFT JOIN mall_asset rt on lt.asset_id=rt.id where lt.mall_id=?1 ORDER BY lt.sort asc", nativeQuery = true)
    List<Object[]> getMallRoll(int mallId);

    /*
        select 
            lt.subject_id,
            rt.`name`
        from 
            mall_subject lt LEFT JOIN conf_subject rt on lt.subject_id=rt.id
        where 
            lt.mall_id=1 and lt.state != 0
        ORDER BY lt.sort asc
    * */
    @Query(value = "select lt.subject_id, rt.`name` from mall_subject lt LEFT JOIN conf_subject rt on lt.subject_id=rt.id where lt.mall_id=?1 and lt.state != 0 ORDER BY lt.sort asc", nativeQuery = true)
    List<Object[]> getMallSubject(int mallId);
}

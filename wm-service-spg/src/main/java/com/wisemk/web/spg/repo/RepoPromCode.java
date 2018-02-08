package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisemk.web.spg.domain.PromCode;

public interface RepoPromCode extends JpaRepository<PromCode, Integer>
{
    @Query(value = "select id, `name` from prom_code where pd_user_id=?1 and state=1 and is_del=0", nativeQuery = true)
    List<Object[]> findPromCodePullDown(int pdUserId);

    /** 查询sql
        select 
            lt.id as id, 
            lt.state as `status`, 
            lt.prom_creative_id as mid,
            rt.`name` as mName,
            lt.`name` as `name`,
            lt.tel as tel,
            (lt.limit_times - lt.times) as limitTimes, 
            lt.end_time as limitTs,
            lt.show_id as url
        from 
            prom_code lt LEFT JOIN prom_creative rt on lt.prom_creative_id=rt.id
        where 
            lt.pd_user_id=1 and lt.is_del=0
        order by
            ?#{#pageable}
        
        数量sql
        select count(*) from prom_code where pd_user_id=1 and is_del=0
     * */
    @Query(value = "select lt.id as id, lt.state, lt.prom_creative_id as mid, rt.`name` as rtName, lt.`name` as ltName, lt.tel as tel, lt.times as limitTimes, lt.end_time as limitTs, lt.show_id as url from prom_code lt LEFT JOIN prom_creative rt on lt.prom_creative_id=rt.id where lt.pd_user_id=?1 and lt.is_del=0 order by ?#{#pageable}", countQuery = "select count(*) from prom_code where pd_user_id=?1 and is_del=0", nativeQuery = true)
    Page<Object[]> findPromCodeListByPdUserId(int pdUserId, Pageable page);

    @Query(value = "select lt.id as id, lt.state, lt.prom_creative_id as mid, rt.`name` as rtName, lt.`name` as ltName, lt.tel as tel, lt.times as limitTimes, lt.end_time as limitTs, lt.show_id as url from prom_code lt LEFT JOIN prom_creative rt on lt.prom_creative_id=rt.id where lt.pd_user_id=?1 and lt.state=?2 and lt.is_del=0 order by ?#{#pageable}", countQuery = "select count(*) from prom_code where pd_user_id=?1 and state=?2 and is_del=0", nativeQuery = true)
    Page<Object[]> findPromCodeListByPdUserIdAndState(int pdUserId, int state, Pageable page);

    @Query(value = "select lt.id as id, lt.state, lt.prom_creative_id as mid, rt.`name` as rtName, lt.`name` as ltName, lt.tel as tel, lt.times as limitTimes, lt.end_time as limitTs, lt.show_id as url from prom_code lt LEFT JOIN prom_creative rt on lt.prom_creative_id=rt.id where lt.pd_user_id=?1 and lt.`name` like ?2 and lt.is_del=0 order by ?#{#pageable}", countQuery = "select count(*) from prom_code where pd_user_id=?1 and name like ?2 and is_del=0", nativeQuery = true)
    Page<Object[]> findPromCodeListByPdUserIdAndNameLike(int pdUserId, String name, Pageable page);

    @Query(value = "select lt.id as id, lt.state, lt.prom_creative_id as mid, rt.`name` as rtName, lt.`name` as ltName, lt.tel as tel, lt.times as limitTimes, lt.end_time as limitTs, lt.show_id as url from prom_code lt LEFT JOIN prom_creative rt on lt.prom_creative_id=rt.id where lt.pd_user_id=?1 and lt.state=?2 and lt.`name` like ?3 and lt.is_del=0 order by ?#{#pageable}", countQuery = "select count(*) from prom_code where pd_user_id=?1 and state=?2 and name like ?3 and is_del=0", nativeQuery = true)
    Page<Object[]> findPromCodeListByPdUserIdAndStateAndNameLike(int pdUserId, int state, String name, Pageable page);

    PromCode findById(int promCodeId);

    PromCode findByShowId(String promCodeShowId);
}

package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisemk.web.spg.domain.PromCreative;

public interface RepoPromCreative extends JpaRepository<PromCreative, Integer>
{
    @Query(value = "select id, `name` from prom_creative where pd_user_id=?1 and is_del=0 and audit_status=1", nativeQuery = true)
    List<Object[]> findPromCreativePullDown(int pdUserId);

    /**
    select 
        lt.id, 
        lt.`name` as lt_name, 
        lt.prom_type_id, 
        rt.`name` as rt_name,
        lt.audit_status, 
        lt.update_time 
    from 
        prom_creative lt LEFT JOIN prom_type rt on lt.prom_type_id=rt.id
    where 
        lt.pd_user_id=5 and lt.is_del=0
    order by 
        lt.id desc
     * */
    @Query(value = "select lt.id, lt.`name` as lt_name, lt.prom_type_id, rt.`name` as rt_name, lt.audit_status, lt.registration_time from prom_creative lt LEFT JOIN prom_type rt on lt.prom_type_id=rt.id where lt.pd_user_id=?1 and lt.is_del=0 order by ?#{#pageable}", countQuery = "select count(*) from prom_creative where pd_user_id=?1 and is_del=0", nativeQuery = true)
    Page<Object[]> findPromCreativeListByPdUserId(int pdUserId, Pageable page);

    PromCreative findById(int id);

    PromCreative findByIdAndPdUserIdAndIsDel(int id, int pdUid, int isDel);
}

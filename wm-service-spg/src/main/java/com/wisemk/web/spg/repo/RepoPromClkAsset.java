package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisemk.web.spg.domain.PromClkAsset;

public interface RepoPromClkAsset extends JpaRepository<PromClkAsset, Integer>
{
    /**
    select 
        lt.id, 
        lt.key_name, 
        lt.prom_clk_layout_elem_id, 
        rt.prom_layout_elem_attr_id,
        lt.cdn_path, 
        rt.description
    from 
        prom_clk_asset lt LEFT JOIN prom_clk_layout_elem rt on lt.prom_clk_layout_elem_id=rt.id
    where 
        lt.prom_creative_id=?1
     * */
    @Query(value = "select lt.id, lt.key_name, lt.prom_clk_layout_elem_id, rt.prom_layout_elem_attr_id, lt.cdn_path, rt.description from prom_clk_asset lt LEFT JOIN prom_clk_layout_elem rt on lt.prom_clk_layout_elem_id=rt.id where lt.prom_creative_id=?1", nativeQuery = true)
    List<Object[]> findDetailByPromCreativeId(int promCreativeId);

    List<PromClkAsset> findByPromCreativeId(int promCreativeId);
}

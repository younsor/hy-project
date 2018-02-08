package cn.zyy.zhichuan.webpd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.zyy.zhichuan.webpd.domain.PromImpAsset;

public interface RepoPromImpAsset extends JpaRepository<PromImpAsset, Integer>
{
    /**
    select 
        lt.id, 
        lt.key_name, 
        lt.prom_imp_layout_elem_id, 
        rt.prom_layout_elem_attr_id,
        lt.cdn_path, 
        rt.description
    from 
        prom_imp_asset lt LEFT JOIN prom_imp_layout_elem rt on lt.prom_imp_layout_elem_id=rt.id
    where 
        lt.prom_creative_id=?1
     * */
    @Query(value = "select lt.id, lt.key_name, lt.prom_imp_layout_elem_id, rt.prom_layout_elem_attr_id, lt.cdn_path, rt.description from prom_imp_asset lt LEFT JOIN prom_imp_layout_elem rt on lt.prom_imp_layout_elem_id=rt.id where lt.prom_creative_id=?1", nativeQuery = true)
    List<Object[]> findDetailByPromCreativeId(int promCreativeId);

    List<PromImpAsset> findByPromCreativeId(int promCreativeId);
}

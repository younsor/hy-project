package cn.zyy.zhichuan.webpd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import cn.zyy.zhichuan.webpd.domain.PromType;

public interface RepoPromType extends JpaRepository<PromType, Integer>
{
    /* 获取推广类型列表信息 */
    @Query(value = "select id, `name` from prom_type", nativeQuery = true)
    List<Object[]> findPromTypePullDown();

    /* 获取推广类型详细信息 */
    PromType findById(int promTypeId);
}

package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisemk.web.spg.domain.PromClkLayoutElem;

public interface RepoPromClkLayout extends JpaRepository<PromClkLayoutElem, Integer>
{
    /* 获取点击样式信息 */
    @Query(value = "select `name`, description from prom_clk_layout where id=?1", nativeQuery = true)
    List<Object[]> getPromClkLayout(int promClkLayoutId);

    /* 获取点击样式元素信息 */
    List<PromClkLayoutElem> findByPromClkLayoutIdOrderById(int promClkLayoutId);

    PromClkLayoutElem findById(int clkLayoutElemId);
}

package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wisemk.web.spg.domain.PromImpLayoutElem;

public interface RepoPromImpLayout extends JpaRepository<PromImpLayoutElem, Integer>
{
    @Query(value = "select `name`, prom_type_id, description from prom_imp_layout where id=?1", nativeQuery = true)
    List<Object[]> getPromImpLayout(int promImpLayoutId);

    List<PromImpLayoutElem> findByPromImpLayoutIdOrderById(int promImpLayoutId);

    PromImpLayoutElem findById(int impLayoutElemId);
}

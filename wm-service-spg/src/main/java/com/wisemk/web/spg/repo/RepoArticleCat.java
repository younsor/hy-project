package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisemk.web.spg.domain.ArticleCat;

public interface RepoArticleCat extends JpaRepository<ArticleCat, Integer>
{
    List<ArticleCat> findByIsDel(int isDel);
}

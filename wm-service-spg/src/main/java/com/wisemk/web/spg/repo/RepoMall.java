package com.wisemk.web.spg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisemk.web.spg.domain.Mall;

public interface RepoMall extends JpaRepository<Mall, Integer>
{
    Mall findByAppidAndDel(String appid, int del);

    Mall findByIdAndDel(Integer id, int del);
}

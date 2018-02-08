package com.wisemk.web.spg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisemk.web.spg.domain.User;

public interface RepoUser extends JpaRepository<User, Integer>
{
    User findByOpenidAndDel(String openid, int del);

    User findByIdAndDel(Integer id, int del);
}

package com.wisemk.web.spg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisemk.web.spg.domain.User;

public interface RepoUser extends JpaRepository<User, Integer>
{
    User findByOpenidAndIsDel(String openid, int isDel);

    User findByIdAndIsDel(Integer id, int isDel);
}

package com.wisemk.web.spg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisemk.web.spg.domain.PdUser;

public interface RepoPdUser extends JpaRepository<PdUser, Integer>
{
    PdUser findByAccountAndPasswordAndIsDel(String account, String passwd, int isDel);

    PdUser findByIdAndIsDel(Integer id, int isDel);
}

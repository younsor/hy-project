package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisemk.web.spg.domain.ShareAccout;

public interface RepoShareAccout extends JpaRepository<ShareAccout, Integer>
{
    List<ShareAccout> findByState(int status);
}

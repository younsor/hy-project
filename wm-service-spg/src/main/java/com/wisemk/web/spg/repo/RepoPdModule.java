package com.wisemk.web.spg.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wisemk.web.spg.domain.PdModule;

public interface RepoPdModule extends JpaRepository<PdModule, Integer>
{
    List<PdModule> findByIsDelOrderBySort(int isDel);
}

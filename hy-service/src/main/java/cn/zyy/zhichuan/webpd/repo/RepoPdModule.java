package cn.zyy.zhichuan.webpd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.zyy.zhichuan.webpd.domain.PdModule;

public interface RepoPdModule extends JpaRepository<PdModule, Integer>
{
    List<PdModule> findByIsDelOrderBySort(int isDel);
}

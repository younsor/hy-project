package cn.zyy.zhichuan.webpd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.zyy.zhichuan.webpd.domain.ShareAccout;

public interface RepoShareAccout extends JpaRepository<ShareAccout, Integer>
{
    List<ShareAccout> findByState(int status);
}

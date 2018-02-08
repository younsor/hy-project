package cn.zyy.zhichuan.webpd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.zyy.zhichuan.webpd.domain.ArticleCat;

public interface RepoArticleCat extends JpaRepository<ArticleCat, Integer>
{
    List<ArticleCat> findByIsDel(int isDel);
}

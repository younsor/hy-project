package cn.zyy.zhichuan.webpd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.zyy.zhichuan.webpd.domain.ArticleShare;

public interface RepoArticleShare extends JpaRepository<ArticleShare, Integer>
{
    List<ArticleShare> findByArticleCatIdOrderByAppearTimeDesc(int articleCatId);

    ArticleShare findByOriginUrl(String originUrl);

    ArticleShare findByUrl(String shareUrl);

    ArticleShare findById(int id);
}

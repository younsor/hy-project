package cn.zyy.zhichuan.webpd.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.zyy.zhichuan.webpd.domain.PdUser;

public interface RepoPdUser extends JpaRepository<PdUser, Integer>
{
    PdUser findByAccountAndPasswordAndIsDel(String account, String passwd, int isDel);

    PdUser findByIdAndIsDel(Integer id, int isDel);
}

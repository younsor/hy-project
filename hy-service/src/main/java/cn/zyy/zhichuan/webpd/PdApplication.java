package cn.zyy.zhichuan.webpd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class PdApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(PdApplication.class, args);
    }
}

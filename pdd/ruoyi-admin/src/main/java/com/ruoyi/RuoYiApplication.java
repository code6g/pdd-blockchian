package com.ruoyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author ruoyi
 */

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@MapperScan({
        "com.ruoyi.pdd.mapperbean",
        "com.ruoyi.blockchain.mapperbean",
        "com.ruoyi.fjzl.mapperbean",
        "com.ruoyi.yc.mapperbean",
        "com.ruoyi.lis.mapperbean",
        "com.ruoyi.code6g.sqlUtil.mapper",
        "com.ruoyi.demo6g.mapperbean"
})
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);

        String s="";
        s+="＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝ \n";
        s+="　　　 ◢██◣                                             \n";
        s+="　　　 █⊙⊙█   --- 祝贺你，牛哥！拼爹爹区块链-Client端-启动成功......\n";
        s+="　　　◤◥◤◥\n";
        s+="　　◢▔▂▂▂◣\n";
        s+="　　◤︼　　　◥\n";
        s+="　　 　╲▁▁╱\n";
        s+="　　　  ╯╜╙╰　　　\n";
        s+="＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝\n";
        System.out.println(s);











    }
}
package com.mbp;

import com.baomidou.mybatisplus.core.toolkit.AES;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RandomKeyTest {


    @Test
    public void getRandomKeyTest() {
        // 生成 16 位随机 AES 密钥
        String randomKey = AES.generateRandomKey();
        System.out.println("随机秘密为：" + randomKey);
        //随机秘密为：14f4e64b1dd4ac59
    }

    @Test
    public void getUrlWithRandomKeyTest() {

        String randomKey = "14f4e64b1dd4ac59";
        String url = "jdbc:p6spy:mysql://localhost:3306/mybatisplus?useUnicode=true&characterEncodeing=utf8&serverTimezone=UTC&useSSL=false";
        String username = "root";
        String password = "lx@lx19870613";
        // 随机密钥加密
        String result1 = AES.encrypt(url, randomKey);
        String result2 = AES.encrypt(username, randomKey);
        String result3 = AES.encrypt(password, randomKey);
        System.out.println(result1);
        // NL0Sn1GbFjlvnDlf9lzHVFlUPNU4YtTzmJzMo4d5qqB4NpM7SAZK1fZaoqYeTYiiOIToWGRM7mLbDNAVJ3wj5gHpPfJpKDQKWSIFz/+k/w653m9KmkMDqfgfJhbEmjoARyo3Qht9WRRGW0J2VdGEcVDfZiJI1vsNbJKd5HfvFi4=

        System.out.println(result2);
        // woCbZKU0d9O/wrUurxwCAg==

        System.out.println(result3);
        // RefYc84U2C4jkgsijz9D+A==
    }
}

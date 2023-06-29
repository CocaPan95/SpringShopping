package com.coca.shoppingmbg;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用于生产MBG的代码
 * Created by macro on 2018/4/26.
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        FastAutoGenerator.create("jdbc:mysql://10.1.8.34:3306/sms?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false","root","123456")
                .globalConfig(builder -> {
                    builder.author("coca")
                            .fileOverride()
                            .outputDir("C:\\Users\\25304\\IdeaProjects\\SpringShopping\\shopping-mbg\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("com.coca")
                            .moduleName("shoppingorderservice");

                })
                .execute();
    }
}

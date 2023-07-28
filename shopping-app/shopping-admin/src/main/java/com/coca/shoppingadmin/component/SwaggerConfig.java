package com.coca.shoppingadmin.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
//    配置Swagger的Docket的bean实例
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//        RequestHandlerSelectors配置扫描接口的方式
                .apis(RequestHandlerSelectors.any())
//                path过滤什么路径
                .paths(PathSelectors.any())
                .build();
    }
}

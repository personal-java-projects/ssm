package com.personal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Function
 * @Description:
 * @Date: 2020/11/11 18:34
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
/*@ComponentScan(basePackages = "net.wlgzs.animals.controller")*/
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.wlgzs.animals.controller")) // 注意修改此处的包名
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 任意，请稍微规范点
                .title("1接口列表 v1.1.0")
                // 任意，请稍微规范点
                .description("接口测试")
                // 将“url”换成自己的ip:port
                .termsOfServiceUrl("http://localhost:8888/animals/swagger-ui.html")
                // 无所谓（这里是作者的别称）
                .contact("function")
                .version("1.1.0")
                .build();
    }
}

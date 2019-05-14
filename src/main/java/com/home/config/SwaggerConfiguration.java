package com.home.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置项
 *
 * @description
 * @author wanghaobin
 * @date 2017年6月20日
 * @since 1.7
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration  {
	@Value("${swagger.basepackage}")
	private String basePackage;
	@Value("${swagger.service.developer}")
	private String creatName;
	@Value("${swagger.service.name}")
	private String serviceName;
	@Value("${swagger.service.description}")
	private String description;




	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(this.basePackage))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(this.serviceName+" Restful APIs")
				.description(this.description)
				.version("1.0").build();
	}
}

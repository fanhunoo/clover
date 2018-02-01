package com.fanhunoo.clover.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ComponentScan(basePackages = "com.fanhunoo.clover",
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION , value = EnableWebMvc.class)})
public class RootConfig {
}

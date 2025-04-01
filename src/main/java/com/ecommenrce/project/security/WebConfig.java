package com.ecommenrce.project.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${frontend.url}")
    String frontEndUrl;
    @Override
    public void addCorsMappings(CorsRegistry registry) {

        System.out.println("🌍 frontend.url property: " + frontEndUrl);

       registry.addMapping("/**")
               .allowedOrigins(frontEndUrl)
               .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
               .allowedHeaders("*")
               .exposedHeaders("Authorization", "Content-Type","Access-Control-Allow-Origin")
               .allowCredentials(true);
    }
}

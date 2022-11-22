package com.alsa.menuapp.core.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins("httpm://localhost:5173")
            .allowedHeaders("*")
            .allowCredentials(false)
            .maxAge(-1);
    }
        
}
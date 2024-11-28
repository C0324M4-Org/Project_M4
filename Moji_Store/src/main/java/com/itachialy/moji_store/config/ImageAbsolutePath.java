package com.itachialy.moji_store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageAbsolutePath implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String absolutePath = "file:D:/Project_M4/Project_M4/Moji_Store/images/";

        registry.addResourceHandler("/images/**")
                .addResourceLocations(absolutePath);
    }
}
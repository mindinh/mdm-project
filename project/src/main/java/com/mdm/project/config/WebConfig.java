package com.mdm.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;

    @Value("${upload.avatar}")
    private String uploadAvatar;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**", "/avatars/**")
                .addResourceLocations("file:///" + uploadPath.replace("\\", "/"))
                .addResourceLocations("file:///" + uploadAvatar.replace("\\", "/"));
    }
}

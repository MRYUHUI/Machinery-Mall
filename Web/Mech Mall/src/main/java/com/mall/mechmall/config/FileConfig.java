package com.mall.mechmall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:
 * @Date: 2024-07-01-14:42
 * @Description:
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    //    商品图片地址
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/img/goods/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
                        + System.getProperty("file.separator") + "main"
                        + System.getProperty("file.separator") + "resources"
                        + System.getProperty("file.separator") + "static"
                        + System.getProperty("file.separator") + "img"
                        + System.getProperty("file.separator") + "goods"
                        + System.getProperty("file.separator")
                );
    }
}

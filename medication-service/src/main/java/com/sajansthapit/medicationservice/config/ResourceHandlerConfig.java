package com.sajansthapit.medicationservice.config;

import com.sajansthapit.medicationservice.constants.MedicationConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Configuration
public class ResourceHandlerConfig implements WebMvcConfigurer {
    @Value("${user-defined.root.folder}")
    private String rootFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String filePath = "file:" + MedicationConstants.HOME_FOLDER + File.separator + rootFolder + File.separator;
        registry.addResourceHandler("/" + rootFolder + "/**")
                .addResourceLocations(filePath)
                .setCacheControl(CacheControl.maxAge(2, TimeUnit.DAYS).cachePublic());
    }
}

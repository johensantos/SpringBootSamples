package com.bolsadeideas.springboot.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;


//ESTA CLASE NO INFLUYE EN NADA
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/C:/Temp/uploads/"); RUTA ESTATICA FUERA DEL PROYECTO


        String resourcePath = Paths.get("uploads").toAbsolutePath().toUri().toString();

       // log.info(resourcePath);

       // registry.addResourceHandler("/uploads/**").addResourceLocations(resourcePath);


    }*/
}

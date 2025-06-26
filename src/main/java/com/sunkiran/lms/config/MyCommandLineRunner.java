package com.sunkiran.lms.config;

import com.sunkiran.lms.service.PropertyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCommandLineRunner implements org.springframework.boot.CommandLineRunner{

    @Autowired
    private PropertyExample propertyExample;
    @Autowired
    private AppConfigProperties appConfigProperties;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(appConfigProperties.getName());
        System.out.println(appConfigProperties.getServers());
        System.out.println(appConfigProperties.getLaunchDate());
        System.out.println(appConfigProperties.getTimeoutMs());
        System.out.println(appConfigProperties.getSettings().getTheme());
        System.out.println(appConfigProperties.getSettings().getLanguage());
        
        
        propertyExample.printProperties();
    }
}
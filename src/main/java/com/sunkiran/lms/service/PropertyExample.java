package com.sunkiran.lms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class PropertyExample {
    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.active}")
    private boolean isActive;

    @Value("${app.max-user}")
    private int maxUsers;

    @Value("${app.timeout-ms}")
    private long timeoutMs;

    @Value("${app.price}")
    private double price;

    // Handlling default null
    @Value("${app.message:#{null}}")
    private String message;

    // We can also give default value
    @Value("${app.counter:1}")
    private int counter;

    @Value("${app.servers}")
    private String[] servers;

    @Value("#{'${app.roles}'.split(',')}")
    private List<String> roles;


    // TODO : Fix this : Not able to resolve the value.
//    @Value("#{${'theme':'${app.settings.theme}', 'language':'${app.settings.language}'}}")
//    private Map<String, String> settings;



    public void printProperties() {
        System.out.println("App Name: " + appName);
        System.out.println("App Version: " + appVersion);
        System.out.println("App Active: " + isActive);
        System.out.println("Max Users: " + maxUsers);
        System.out.println("Timeout MS: " + timeoutMs);
        System.out.println("Price: " + price);

        System.out.println("Servers: " + Arrays.toString(servers) );
        System.out.println("Roles: " + roles);
       // System.out.println("Settings: " + settings);

    }
}

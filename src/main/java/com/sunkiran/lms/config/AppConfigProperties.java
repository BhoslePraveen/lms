package com.sunkiran.lms.config;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "app")
@Profile("!test")
public class AppConfigProperties {
    @NotNull
    private String name;
    @NotNull
    private String version;
    @NotNull
    private boolean active;
    @Min(1)
    private int maxUser;
    private long timeoutMs;
    private double price;
    private String launchDate;
    @Size(min=1)
    private List<String> servers;
    private Settings settings = new Settings();

    @Data
    public static class Settings {
        private String theme;
        private String language;
    }
}

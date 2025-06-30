package com.sunkiran.lms.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Server");

        Server uatServer = new Server();
        uatServer.setUrl("http://uat.lms.com");
        uatServer.setDescription("UAT Server");

        return new OpenAPI()
                .info(new Info()
                        .title("SunKiran-LMS APIs")
                        .description("LMS API Documentation")
                        .version("v1.0")
                        .contact(new Contact().name("Praveen Bhosle").email("Praveen.bhosle5@gmail.com"))
                ).servers(List.of(localServer, uatServer));
    }

}

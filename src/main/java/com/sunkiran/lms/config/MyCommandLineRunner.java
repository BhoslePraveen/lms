package com.sunkiran.lms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunkiran.lms.model.User;
import com.sunkiran.lms.service.PropertyExample;
import com.sunkiran.lms.service.RestTemplatePracticeService;
import com.sunkiran.lms.service.WebClientPracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class MyCommandLineRunner implements org.springframework.boot.CommandLineRunner{

    @Autowired
    private PropertyExample propertyExample;
    @Autowired
    private AppConfigProperties appConfigProperties;
    @Autowired
    private ObjectMapper objectMapper ;
    @Autowired
    private RestTemplatePracticeService restTemplatePracticeService;
    @Autowired
    private WebClientPracticeService webClientPracticeService;

//    @Override
//    public void run(String... args) throws Exception {
//         //Object To a JSON
//        User user = new User();
//        user.setName("Sunkiran");
//        user.setAge(25);
//        user.setJoinDate(LocalDate.of(2025,6,27));
//        user.setPassword("<PASSWORD>");
//
//        //System.out.println("This is actual Object : "+user);
//
//        //String userJson = objectMapper.writeValueAsString(user);
//        //System.out.println("This is JSON : "+userJson);
//
//        String json = """
//               {"full_name":"Sunkiran","age":25,"joinDate":"2025-06-27","password":"<PASSWORD>","roles":"ACTIVE"}
//                """;
//
//        User userFromJson = objectMapper.readValue(json, User.class);
//        System.out.println("This is Object from JSON : "+userFromJson);
//
//    }
    @Override
    public void run(String... args) throws Exception {

        webClientPracticeService.getPostM1();


    }
}
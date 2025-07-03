package com.sunkiran.lms.controller;

import com.sunkiran.lms.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @PostMapping("/register")
//    public ResponseEntity<Void> postUser(@Valid @RequestBody UserDto userDto) {
//        return ResponseEntity.ok().build();
//    }
//}

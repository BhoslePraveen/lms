package com.sunkiran.lms.dto;

import lombok.Data;

@Data
public class Post {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}

package com.sunkiran.lms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    @JsonProperty("full_name")
    private String name;
    private int age;
    private LocalDate joinDate;
    @JsonIgnore
    private String password;
}

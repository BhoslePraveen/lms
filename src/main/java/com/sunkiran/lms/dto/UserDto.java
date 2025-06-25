package com.sunkiran.lms.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class UserDto {
    @NotBlank(message = "Name Cannot be Null or Blank")
    @Size(min = 3, max = 25, message = "Name Should be between 3 and 25 characters")
    private String name;

    @Email(message = "Email is not valid")
    @NotBlank(message = "Email Cannot be Null or Blank")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be 10 digits")
    @NotBlank(message = "Phone Cannot be Null or Blank")
    private String phone;

    @Min(value = 18, message = "Age should be greater than 18")
    private Integer age;

    @NotNull(message = "Gender Cannot be Null")
    private String gender;

    @Future(message = "Join Date Should be in the future")
    private LocalDate joinDate;

    @Size(min=1, message = "At least one role is required")
    @NotEmpty(message = "Roles Cannot be Null or Blank")
    private List<String> roles;
}

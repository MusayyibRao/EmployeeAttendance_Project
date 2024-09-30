package com.PSL.management.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {


    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;

}

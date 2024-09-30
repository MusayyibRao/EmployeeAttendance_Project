package com.PSL.management.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterDto {


    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String createdBy;
    private String modifyBy;
    private Date createdDate;
    private Date modifyDate;
    private String role;


}

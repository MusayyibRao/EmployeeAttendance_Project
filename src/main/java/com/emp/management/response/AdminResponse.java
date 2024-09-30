package com.emp.management.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminResponse {

    private int statusCode;
    private String message;
    private String token;
    private String expirationTime;
    private RegisterDto adminData;
    private List<RegisterDto> adminDataList;


}

package com.PSL.management.common;

import com.PSL.management.dataModel.AdminEntity;
import com.PSL.management.response.RegisterDto;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdminCommon {


    public static RegisterDto convertRegisterToRegisterDto(AdminEntity adminEntity) {

        RegisterDto registerDto = new RegisterDto();
        if (adminEntity != null) {
            registerDto.setId(adminEntity.getId());
            registerDto.setFirstName(adminEntity.getFirstname());
            registerDto.setLastName(adminEntity.getLastname());
            registerDto.setEmail(adminEntity.getEmail());
            registerDto.setPassword(adminEntity.getPassword());
            registerDto.setCreatedDate(adminEntity.getCreatedDate());
            registerDto.setModifyDate(adminEntity.getModifyDate());
            registerDto.setRole(adminEntity.getRole());
            registerDto.setCreatedBy(adminEntity.getCreatedBy());
            registerDto.setModifyBy(adminEntity.getModifyBy());
        }
        return registerDto;
    }

    public static AdminEntity convertRegisterDtoToRegister(RegisterDto registerDto) {

        AdminEntity adminEntity = new AdminEntity();

        if (registerDto != null) {
            adminEntity.setId(registerDto.getId());
            adminEntity.setFirstname(registerDto.getFirstName());
            adminEntity.setLastname(registerDto.getLastName());
            adminEntity.setRole(registerDto.getRole());
            adminEntity.setEmail(registerDto.getEmail());
            adminEntity.setCreatedBy(registerDto.getCreatedBy());
            adminEntity.setCreatedDate(registerDto.getCreatedDate());
            adminEntity.setModifyDate(new Date());
            adminEntity.setModifyBy(registerDto.getModifyBy());
        }
        return adminEntity;
    }

    public static List<RegisterDto> convertRegisterToRegisterDtoList(List<AdminEntity> adminEntityList){
        return adminEntityList.stream().map(AdminCommon::convertRegisterToRegisterDto).collect(Collectors.toList());
    }
}

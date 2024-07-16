package com.aluraforohub.forohub.persistence.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDto {
    private String username;
    private String password;
    private List<RoleDto> roles;
}

package com.aluraforohub.forohub.persistence.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDto {
    private Long id;
    private List<PermissionDto> permissions;
}

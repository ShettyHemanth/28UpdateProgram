package com.hemanth.Roles.mapper;

import com.hemanth.Roles.dto.RolesDto;
import com.hemanth.Roles.entity.Roles;

public class MapperClass
{

    public static Roles rolesDtoToRole(RolesDto rolesDto)
    {
        Roles roles=new Roles();
        roles.setRoleName(rolesDto.getRoleName());
        roles.setRoleState(rolesDto.getRoleState());
        roles.setOrgName(rolesDto.getOrgName());
        roles.setRoleId(rolesDto.getRoleId());
        roles.setCreatedDate(rolesDto.getCreatedDate());

        return roles;
    }

    public static RolesDto rolesToRoleDto(Roles roles)
    {
        RolesDto rolesDto=new RolesDto();
        rolesDto.setRoleName(roles.getRoleName());
        rolesDto.setOrgName(roles.getOrgName());
        rolesDto.setRoleState(roles.getRoleState());
        rolesDto.setRoleId(roles.getRoleId());
        rolesDto.setCreatedDate(roles.getCreatedDate());

        return rolesDto;
    }

}

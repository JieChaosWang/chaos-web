package com.my.test.mapper;

import com.my.test.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    int addRole(Role role);

    Role queryRole(Role role);

    int updateRole(Role role);

    List<Role> queryRoleList(Role role);
}

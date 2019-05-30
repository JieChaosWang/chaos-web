package com.my.test.mapper;

import com.my.test.pojo.AdminRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminRoleMapper {

    int addAdminRole(AdminRole adminRole);

    int updateAdminRole(AdminRole adminRole);

    List<AdminRole> queryAdminRoleList(AdminRole adminRole);
}

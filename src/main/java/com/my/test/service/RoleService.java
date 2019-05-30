package com.my.test.service;

import com.my.test.pojo.Role;

import java.util.List;

/**
 * Created by wang on 2018/11/27.
 */
public interface RoleService {

    Role queryRole(Role role);

    void addRole(Role role) throws Exception;

    List<Role> queryRoleList(Role role);

}

package com.my.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.my.test.mapper.AdminMapper;
import com.my.test.mapper.AdminRoleMapper;
import com.my.test.pojo.Admin;
import com.my.test.pojo.AdminRole;
import com.my.test.pojo.Role;
import com.my.test.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by wang on 2018/11/26.
 */
@Service
public class AdminServiceImpl implements AdminService {
    protected static Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;


    @Override
    public Admin queryAdmin(Admin admin) {
        Admin adminInfo = adminMapper.queryAdmin(admin);

        return adminInfo;
    }

    @Override
    public void addAdmin(Admin admin) {

        admin.setIsLocked(false);

        adminMapper.addAdmin(admin);

        Admin adminForId = adminMapper.queryAdminInfo(admin);

        logger.info("添加时，查询到的adminForId>>>>>>>>>>>>>>>>>" + JSON.toJSONString(adminForId));

        for (String thisRoleId : admin.getRoleIds()) {

            AdminRole adminRole = new AdminRole();
            adminRole.setAdmins_id(adminForId.getAdminId());
            adminRole.setRoles_id(Long.parseLong(thisRoleId));

            adminRoleMapper.addAdminRole(adminRole);
        }
    }

    @Override
    public void updateAdmin(Admin admin) {
        adminMapper.updateAdmin(admin);
    }

    @Override
    public List<Admin> queryAdminList(Admin admin) {
        return adminMapper.queryAdminList(admin);
    }
}

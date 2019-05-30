package com.my.test.service.impl;

import com.my.test.controller.BaseController;
import com.my.test.mapper.RoleMapper;
import com.my.test.mapper.RoleMapper;
import com.my.test.pojo.Role;
import com.my.test.pojo.Role;
import com.my.test.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wang on 2018/11/27.
 */
@Service
public class RoleServiceImpl implements RoleService {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public Role queryRole(Role role) {

        role = roleMapper.queryRole(role);

        return role;
    }

    @Override
    public void addRole(Role role) throws Exception{

        try {

            roleMapper.addRole(role);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("新增统计记录失败", e);
        }

    }

    @Override
    public List<Role> queryRoleList(Role role){

        List<Role> list = list = roleMapper.queryRoleList(role);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            throw new Exception("新增统计记录失败", e);
//        }
        return list;
    }
}

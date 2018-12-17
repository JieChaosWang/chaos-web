package com.my.test.service.impl;

import com.github.pagehelper.Page;
import com.my.test.mapper.AdminMapper;
import com.my.test.pojo.Admin;
import com.my.test.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

/**
 * Created by wang on 2018/11/26.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;


    public Admin findByUsername(String username){
        return adminMapper.findByUsername(username);
    }

    public  void  update(Admin admin){
        adminMapper.update(admin);
    }

    public Page<Admin> findAll(Pageable pageable) {
        return adminMapper.findAll(pageable);
    }
}

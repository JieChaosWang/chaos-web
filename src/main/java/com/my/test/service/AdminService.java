package com.my.test.service;

import com.github.pagehelper.Page;
import com.my.test.pojo.Admin;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by wang on 2018/11/26.
 */
public interface AdminService {

    void addAdmin(Admin admin);

    Admin queryAdmin(Admin admin);

    void updateAdmin(Admin admin);

    List<Admin> queryAdminList(Admin admin);
}

package com.my.test.mapper;

import com.my.test.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wang on 2018/11/26.
 */
@Mapper
public interface AdminMapper {

    Admin queryAdminInfo(Admin admin);

    Admin queryAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void addAdmin(Admin admin);

    List<Admin> queryAdminList(Admin admin);
}

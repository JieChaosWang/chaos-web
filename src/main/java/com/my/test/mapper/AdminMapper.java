package com.my.test.mapper;

import com.github.pagehelper.Page;
import com.my.test.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.awt.print.Pageable;

/**
 * Created by wang on 2018/11/26.
 */
@Mapper
public interface AdminMapper {

   Admin findByUsername(String username);

   void  update(Admin admin);

   Page<Admin> findAll(Pageable pageable);




}

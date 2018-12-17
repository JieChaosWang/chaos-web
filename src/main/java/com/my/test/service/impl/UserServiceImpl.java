package com.my.test.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.test.mapper.AdminMapper;
import com.my.test.mapper.UserMapper;
import com.my.test.pojo.Admin;
import com.my.test.pojo.User;
import com.my.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname UserServiceImpl <br>
 * @description TODO <br>
 * @created 2018/11/5 11:00 <br>
 * ********************************************************.<br>
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryUserInfo(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public PageInfo<User> queryUserList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        List<User> userList = userMapper.selectAllUser();
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        return pageInfo;
    }

}

package com.my.test.service;

import com.github.pagehelper.PageInfo;
import com.my.test.pojo.User;

/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname UserService <br>
 * @description TODO <br>
 * @created 2018/11/5 11:00 <br>
 * ********************************************************.<br>
 */
public interface UserService {

    User queryUserInfo(Integer userId);

    PageInfo<User> queryUserList(Integer pageNum, Integer pageSize);
}

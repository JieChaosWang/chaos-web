package com.my.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.User;
import com.my.test.service.UserService;
import com.my.test.service.impl.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname UserController <br>
 * @description TODO <br>
 * @created 2018/11/5 11:02 <br>
 * ********************************************************.<br>
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/")
    public @ResponseBody
    Object queryAccountInfo(HttpServletRequest request, Pagination pager) {
        try {
//            User user = userService.queryUserInfo(1);
//            System.out.println(JSONObject.toJSON(user));
            Integer pageSize = pager.getPageSize();
            Integer pageNum = pager.getCurrentPage();
            PageInfo<User> pageInfo = userService.queryUserList(pageNum, pageSize);
            return JSONObject.toJSON(pageInfo);
        } catch (Exception e) {
            throw e;
        }
    }
}

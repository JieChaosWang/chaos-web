package com.my.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Role;
import com.my.test.pojo.Role;
import com.my.test.service.RoleService;
import com.my.test.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;

/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname RoleController <br>
 * @description TODO <br>
 * @created 2018/11/5 11:02 <br>
 * ********************************************************.<br>
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/addRole")
    public @ResponseBody Object addRole(Role role) {
        try {

            roleService.addRole(role);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "role/list";
    }

    @RequestMapping(value = "/queryRoleList", method = RequestMethod.GET)
    public String queryRoleList(Role role, PageInfo pageInfo, Model model) {


            PageHelper.startPage(pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum(), 10);
            List<Role> list = roleService.queryRoleList(role);

            PageInfo rolePage = new PageInfo(list, 5);
            model.addAttribute("page", rolePage);


        return "role/list";
    }
}

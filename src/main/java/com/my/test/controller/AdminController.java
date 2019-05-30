package com.my.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Admin;
import com.my.test.pojo.Role;
import com.my.test.service.AdminService;
import com.my.test.service.RoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by wang on 2018/11/27.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Resource
    private AdminService adminService;
    @Resource
    private RoleService roleService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 去添加管理员
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {

        List<Role> roles = roleService.queryRoleList(new Role());

        model.addAttribute("roles", roles);
        return "admin/add";
    }

    /**
     * 添加管理员
     */
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public String addAdmin(Admin admin, RedirectAttributes redirectAttributes) {

        admin.setCreateDate(new Date());

        String encodePassword = passwordEncoder.encode(admin.getPassword());

        admin.setPassword(encodePassword);

        adminService.addAdmin(admin);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:queryAdminList";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/queryAdminList", method = RequestMethod.GET)
    public String queryAdminList(Admin admin, PageInfo pageInfo, Model model) {

        PageHelper.startPage(pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum(), 10);
        List<Admin> list = adminService.queryAdminList(admin);

        PageInfo admins = new PageInfo(list, 5);
        model.addAttribute("page", admins);

        return "admin/list";
    }
}

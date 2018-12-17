package com.my.test.controller;

import com.github.pagehelper.Page;
import com.my.test.service.AdminService;
import com.my.test.service.RoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.awt.print.Pageable;

/**
 * Created by wang on 2018/11/27.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    @Resource
    private AdminService adminService;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, Model model) {
        Page page = adminService.findAll(pageable);
        model.addAttribute("page", page);
        return "admin/list";
    }
}

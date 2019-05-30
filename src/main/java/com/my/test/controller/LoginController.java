package com.my.test.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wang on 2018/11/27.
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login() {
        //获取当前的Subject
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !authentication.getPrincipal().equals("anonymousUser") && authentication.isAuthenticated()) {
            return "redirect:/home";
        } else {
            return "login";
        }
    }
}

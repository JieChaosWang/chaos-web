package com.my.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Authority;
import com.my.test.pojo.AuthorityCategory;
import com.my.test.service.AuthorityCategoryService;
import com.my.test.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname UserController <br>
 * @description TODO <br>
 * @created 2018/11/5 11:02 <br>
 * ********************************************************.<br>
 */
@Controller
@RequestMapping(value = "/authorityCategory")
public class AuthorityCategoryController extends BaseController {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private AuthorityCategoryService authorityCategoryService;

    /**
     * 去添加管理员
     */
    @RequestMapping(value = "/addAuthorityCategoryIndex", method = RequestMethod.GET)
    public String add() {

        return "authorityCategory/add";
    }

    @RequestMapping(value = "/addAuthorityCategory")
    public String addAuthorityCategory(AuthorityCategory authorityCategory) {
        try {
            authorityCategory.setCreateDate(new Date());

            authorityCategoryService.addAuthorityCategory(authorityCategory);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:queryAuthorityCategoryList";
    }

    @RequestMapping(value = "/queryAuthorityCategoryList", method = RequestMethod.GET)
    public String queryAuthorityCategoryList(AuthorityCategory authorityCategory, PageInfo pageInfo, Model model) {


            PageHelper.startPage(pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum(), 10);
            List<AuthorityCategory> list = authorityCategoryService.queryAuthorityCategoryList(authorityCategory);

            PageInfo statistics = new PageInfo(list, 5);
            model.addAttribute("page", statistics);


        return "authorityCategory/list";
    }

    @RequestMapping(value = "/delAuthorityCategory")
    public String delAuthorityCategory(AuthorityCategory authorityCategory) {
        try {



        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:queryAuthorityCategoryList";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delAuthorityCategory(Long[] ids) {
        authorityCategoryService.delAuthorityCategory(ids);

        return SUCCESS_MESSAGE;
    }
}

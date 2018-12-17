
package com.my.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Authority;
import com.my.test.service.AuthorityCategoryService;
import com.my.test.service.AuthorityService;
import com.my.test.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


/**
 * Created by wang on 2018/11/28.
 */
@Controller
@RequestMapping(value = "/authority")
public class AuthorityController extends BaseController {

    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private AuthorityCategoryService authorityCategoryService;

    /**
     * 权限列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(PageInfo pageInfo, Model model) {
//        pageNum=1;pageSize=10;

//        List<Authority> authorities = authorityService.findAll(pageNum , pageSize);
        PageHelper.startPage(pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum(), 1);
        List<Authority> list = authorityService.findAll(pageInfo.getPageNum(), pageInfo.getPageSize());
        PageInfo authorities = new PageInfo(list, 5);
        model.addAttribute("page", authorities);
        return "authority/list";
    }


    /**
     * 权限添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("authorityCategoryTree", authorityCategoryService.findAll());
        return "authority/add";
    }

    /**
     * 权限保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Authority authority, RedirectAttributes redirectAttributes) {
        authorityService.save(authority);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list";
    }

    /**
     * 权限编辑
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("authorityCategoryTree", authorityCategoryService.findAll());
        model.addAttribute("authority", authorityService.getOne(id));
        return "authority/edit";
    }

    /**
     * 权限更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Authority authority, RedirectAttributes redirectAttributes) {
        if (!isValid(authority)) {
            return ERROR_VIEW;
        }
        authorityService.update(authority);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids) {
        Authority authority = authorityService.delete(ids);

        if (authority != null) {
            return Message.error("删除失败，请先修改" + authority.getName() + "相关的角色");
        }
        return SUCCESS_MESSAGE;
    }


}



package com.my.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Authority;
import com.my.test.pojo.AuthorityCategory;
import com.my.test.service.AuthorityCategoryService;
import com.my.test.service.AuthorityService;
import com.my.test.util.BeeUtils;
import com.my.test.util.Constants;
import com.my.test.util.Message;
import com.my.test.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        try {
    //        pageNum=1;pageSize=10;

    //        List<Authority> authorities = authorityService.findAll(pageNum , pageSize);
            PageHelper.startPage(pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum(), 10);
            List<Authority> list = authorityService.queryAllAuthorityList(pageInfo.getPageNum(), pageInfo.getPageSize());
            PageInfo authorities = new PageInfo(list, 5);
            model.addAttribute("page", authorities);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "authority/list";
    }


    /**
     * 权限添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {

        try {

            List<AuthorityCategory> authorityCategories = authorityCategoryService.queryAuthorityCategoryList(new AuthorityCategory());

            model.addAttribute("authorityCategoryTree", authorityCategories);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "authority/add";
    }

    /**
     * 权限保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Authority authority, RedirectAttributes redirectAttributes) {
        try {

            authorityService.addAuthority(authority);
            addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "redirect:list";
    }

    /**
     * 权限编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long authorityId, Model model) {

        try {

            List<AuthorityCategory> authorityCategories = authorityCategoryService.queryAuthorityCategoryList(new AuthorityCategory());

            model.addAttribute("authorityCategoryTree", authorityCategories);

            Authority authority = new Authority();

            authority.setAuthorityId(authorityId);

            authority = authorityService.queryAuthorityInfo(authority);

            model.addAttribute("authority", authority);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "authority/edit";
    }

    /**
     * 权限更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Authority authority, RedirectAttributes redirectAttributes) {
        try {

            BeeUtils.isEmpty("权限主键",authority.getAuthorityId());

            if (!isValid(authority)) {
                return ERROR_VIEW;
            }
            authorityService.updateAuthority(authority);
            addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Message delete(Long[] ids) {
        Message message = new Message();

        try {

            authorityService.deleteAuthority(ids);

            message.setType(Message.Type.success);
        } catch (Exception e) {
            logger.error(e.getMessage());
            message.setType(Message.Type.error);
            message.setContent(e.getMessage());
        }

        return message;
    }


}


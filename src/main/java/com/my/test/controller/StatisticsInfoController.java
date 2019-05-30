package com.my.test.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Admin;
import com.my.test.pojo.StatisticsInfo;
import com.my.test.service.AdminService;
import com.my.test.service.StatisticsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Date;
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
@RequestMapping(value = "/statisticsInfo")
public class StatisticsInfoController extends BaseController {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private StatisticsInfoService statisticsInfoService;
    @Resource
    private AdminService adminService;
    @RequestMapping(value = "/addStatisticsInfoIndex", method = RequestMethod.GET)
    public String add(Model model) {

        List<Admin> list = adminService.queryAdminList(new Admin());

        model.addAttribute("list", list);

        return "statistics_info/add";
    }

    @RequestMapping(value = "/addStatisticsInfo")
    public String addStatisticsInfo(StatisticsInfo statisticsInfo) {
        try {

            statisticsInfo.setCreateTime(new Date(System.currentTimeMillis()));

            statisticsInfoService.addStatisticsInfo(statisticsInfo);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:queryStatisticsInfoList";
    }

    @RequestMapping(value = "/queryStatisticsInfoList", method = RequestMethod.GET)
    public String queryStatisticsInfoList(StatisticsInfo statisticsInfo, PageInfo pageInfo, Model model) {


            PageHelper.startPage(pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum(), 10);
            List<StatisticsInfo> list = statisticsInfoService.queryStatisticsInfoList(statisticsInfo);

            for(StatisticsInfo thisStatisticsInfo : list) {

                Admin admin = new Admin();
                admin.setAdminId(thisStatisticsInfo.getAdminId());

                admin = adminService.queryAdmin(admin);

                thisStatisticsInfo.setAdminName(admin.getName());

            }
            PageInfo statistics = new PageInfo(list, 5);
            model.addAttribute("page", statistics);


        return "statistics_info/list";
    }
}

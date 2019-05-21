package com.my.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Authority;
import com.my.test.pojo.StatisticsInfo;
import com.my.test.pojo.User;
import com.my.test.service.StatisticsInfoService;
import com.my.test.service.UserService;
import com.my.test.service.impl.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
@RestController
@RequestMapping("/statisticsInfo")
public class StatisticsInfoController {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private StatisticsInfoService statisticsInfoService;

    @RequestMapping(value = "/addStatisticsInfo")
    public @ResponseBody Object addStatisticsInfo(StatisticsInfo statisticsInfo) {
        try {

            statisticsInfo.setCreateTime(new Date(System.currentTimeMillis()));

            statisticsInfoService.addStatisticsInfo(statisticsInfo);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "statisticsInfo/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String queryStatisticsInfoList(StatisticsInfo statisticsInfo, PageInfo pageInfo, Model model) {


            PageHelper.startPage(pageInfo.getPageNum() == 0 ? 1 : pageInfo.getPageNum(), 10);
            List<StatisticsInfo> list = statisticsInfoService.queryStatisticsInfoList(statisticsInfo);

            PageInfo statistics = new PageInfo(list, 5);
            model.addAttribute("page", statistics);


        return "statisticsInfo/list";
    }
}

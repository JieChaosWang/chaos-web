package com.my.test.service.impl;

import com.my.test.controller.BaseController;
import com.my.test.mapper.StatisticsInfoMapper;
import com.my.test.pojo.StatisticsInfo;
import com.my.test.service.StatisticsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname UserServiceImpl <br>
 * @description TODO <br>
 * @created 2018/11/5 11:00 <br>
 * ********************************************************.<br>
 */
@Service
public class StatisticsInfoServiceImpl implements StatisticsInfoService {

    protected static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private StatisticsInfoMapper statisticsInfoMapper;


    @Override
    public StatisticsInfo queryStatisticsInfo(StatisticsInfo statisticsInfo) {

        statisticsInfo = statisticsInfoMapper.queryStatisticsInfo(statisticsInfo);

        return statisticsInfo;
    }

    @Override
    public void addStatisticsInfo(StatisticsInfo statisticsInfo) throws Exception{

        try {

            statisticsInfoMapper.addStatisticsInfo(statisticsInfo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception("新增统计记录失败", e);
        }

    }


}

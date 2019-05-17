package com.my.test.service;

import com.my.test.pojo.StatisticsInfo;

/**
 * ********************************************************.<br>
 *
 * @author wcj <br>
 * @classname StatisticsInfoService <br>
 * @created 2018/11/5 11:00 <br>
 * ********************************************************.<br>
 */
public interface StatisticsInfoService {

    StatisticsInfo queryStatisticsInfo(StatisticsInfo statisticsInfo);

    void addStatisticsInfo(StatisticsInfo statisticsInfo) throws Exception;
}

package com.my.test.mapper;

import com.my.test.pojo.StatisticsInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname UserMapper <br>
 * @description TODO <br>
 * @created 2018/11/5 10:57 <br>
 * ********************************************************.<br>
 */
@Mapper
public interface StatisticsInfoMapper {

    int addStatisticsInfo(StatisticsInfo statisticsInfo);

    StatisticsInfo queryStatisticsInfo(StatisticsInfo statisticsInfo);

    int updateStatisticsInfo(StatisticsInfo statisticsInfo);

}

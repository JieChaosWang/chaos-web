package com.my.test.mapper;

import com.my.test.pojo.Area;
import com.my.test.pojo.City;
import com.my.test.pojo.Province;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author lk
 */
@Mapper
public interface ProvinceMapper {

    List<Province> queryProvinceListPage();

    List<City> queryCityListPage();

    List<Area> queryAreaListPage();

    List<City> queryCityListById(String provinceid);

    List<Area> queryAreaListById(String cityid);

    String getCityNameById(Integer cityId);

    String getAreaNameById(Integer areaId);

    String getProvinceNameById(Integer provinceId);
}
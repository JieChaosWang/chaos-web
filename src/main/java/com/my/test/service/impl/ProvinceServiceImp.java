package com.my.test.service.impl;

import com.my.test.mapper.ProvinceMapper;
import com.my.test.pojo.Area;
import com.my.test.pojo.City;
import com.my.test.pojo.Province;
import com.my.test.pojo.QueryProvincesAndCitysResponse;
import com.my.test.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProvinceServiceImp implements ProvinceService {

	@Autowired
    ProvinceMapper provinceMapper;
	
	/**
     * 获取所有的省市区匹配蜂采
     */
    @Override
    public QueryProvincesAndCitysResponse queryAllProvincesAndCitys() throws Exception {
        QueryProvincesAndCitysResponse response = new QueryProvincesAndCitysResponse();
        try {
            List<Province> provinceList = provinceMapper.queryProvinceListPage();
            List<City> cityList = provinceMapper.queryCityListPage();
            List<Area> areaList = provinceMapper.queryAreaListPage();
            if (provinceList != null) {
                response.setProvinceList(provinceList);
            }
            if (provinceList != null) {
                response.setCityList(cityList);
            }
            if (provinceList != null) {
                response.setAreaList(areaList);
            }
        } catch (Exception e) {
            throw new Exception("查询省市地区信息失败", e);
        }
        return response;
    }

    /**
     * 获取所有的省匹配蜂贝
     */
    @Override
    public QueryProvincesAndCitysResponse queryProvinceList() throws Exception {
        QueryProvincesAndCitysResponse response = new QueryProvincesAndCitysResponse();
        try {
            List<Province> provinceList = provinceMapper.queryProvinceListPage();
            if (provinceList != null) {
                response.setProvinceList(provinceList);
            }
        } catch (Exception e) {
            throw new Exception("查询省信息失败", e);
        }
        return response;
    }

    /**
     * 获取所有的city匹配蜂贝
     */
    @Override
    public QueryProvincesAndCitysResponse queryCityByProvinceCode(String provinceId) throws Exception {
        QueryProvincesAndCitysResponse response = new QueryProvincesAndCitysResponse();
        try {
            List<City> cityList = provinceMapper.queryCityListById(provinceId);
            if (cityList != null) {
                response.setCityList(cityList);
            }
        } catch (Exception e) {
            throw new Exception("查询市信息失败", e);
        }
        return response;
    }

    /**
     * 获取所有的area匹配蜂贝
     */
    @Override
    public QueryProvincesAndCitysResponse queryAreaByCityId(String cityid) throws Exception {
        QueryProvincesAndCitysResponse response = new QueryProvincesAndCitysResponse();
        try {
            List<Area> areaList = provinceMapper.queryAreaListById(cityid);
            if (areaList != null) {
                response.setAreaList(areaList);
            }
        } catch (Exception e) {
            throw new Exception("查询地区信息失败", e);
        }
        return response;
    }

    @Override
    public String queryCityNameById(Integer cityid) throws Exception {
        String cityName;
        try {
            cityName = provinceMapper.getCityNameById(cityid);
        } catch (Exception e) {
            throw new Exception("查询市信息失败", e);
        }
        return cityName;
    }

    @Override
    public String queryAreaNameById(Integer areaid) throws Exception {
        String areaName;
        try {
            areaName = provinceMapper.getAreaNameById(areaid);
        } catch (Exception e) {
            throw new Exception("查询地区信息失败", e);
        }
        return areaName;
    }

    @Override
    public String queryProvinceNameById(Integer provinceid) throws Exception {
        String provinceName;
        try {
            provinceName = provinceMapper.getProvinceNameById(provinceid);
        } catch (Exception e) {
            throw new Exception("查询省信息失败", e);
        }
        return provinceName;
    }

    @Override
    public QueryProvincesAndCitysResponse queryAllCitys() throws Exception {
        QueryProvincesAndCitysResponse response = new QueryProvincesAndCitysResponse();

        try {
            List<City> cityList = provinceMapper.queryCityListPage();
            List<City> citys = new ArrayList<City>();
            for (City cityEntity : cityList) {
                City city = new City();
                city.setCityCode(cityEntity.getCityCode());
                city.setCityId(cityEntity.getCityId());
                city.setCityName(cityEntity.getCityName());
                city.setProvinceCode(cityEntity.getProvinceCode());
                citys.add(city);
            }
            response.setCityList(citys);
        } catch (Exception e) {
            throw new Exception("查询城市列表失败", e);
        }
        return response;
    }

    @Override
    public QueryProvincesAndCitysResponse queryAllProvinceCityList() throws Exception {
        QueryProvincesAndCitysResponse response = new QueryProvincesAndCitysResponse();
        try {
            List<Province> eprovinceList = provinceMapper.queryProvinceListPage();
            if (eprovinceList != null) {
                List<Province> provinceList = new ArrayList<Province>();
                for (Province thisProvince : eprovinceList) {
                    List<City> cityList = provinceMapper.queryCityListById(String.valueOf(thisProvince.getProvinceId()));
                    if (cityList != null) {
                        thisProvince.setCityList(cityList);
                    }
                    provinceList.add(thisProvince);
                }
                response.setProvinceList(provinceList);
            }
        } catch (Exception e) {
            throw new Exception("查询省市信息失败", e);
        }
        return response;
    }

}

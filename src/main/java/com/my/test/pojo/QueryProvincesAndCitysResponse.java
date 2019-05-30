package com.my.test.pojo;

import java.util.ArrayList;
import java.util.List;

public class QueryProvincesAndCitysResponse {

    private List<Province> provinceList;
    private List<City> cityList;
    private List<Area> areaList;
    private List<Town> townList;


    public List<Town> getTownList() {
        if (townList == null) {
            townList = new ArrayList<Town>();
        }
        return townList;
    }

    public void setTownList(List<Town> townList) {
        this.townList = townList;
    }


    public List<Province> getProvinceList() {
        if (provinceList == null) {
            provinceList = new ArrayList<Province>();
        }
        return provinceList;
    }

    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

    public List<City> getCityList() {
        if (cityList == null) {
            cityList = new ArrayList<City>();
        }
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<Area> getAreaList() {
        if (areaList == null) {
            areaList = new ArrayList<Area>();
        }
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }
}

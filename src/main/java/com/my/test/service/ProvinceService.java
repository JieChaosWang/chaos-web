/**
 * 
 */
package com.my.test.service;

import com.my.test.pojo.QueryProvincesAndCitysResponse;

/**
 * @author lk
 */
public interface ProvinceService {

	/**
	 * 查询所有省份和城市信息
	 * @author zjk
	 * 2017年8月22日
	 * @return
	 * @throws Exception
	 */
	QueryProvincesAndCitysResponse queryAllProvincesAndCitys() throws Exception;
	
	/**
	 * 查询省份信息
	 * @author zjk
	 * 2017年8月22日
	 * @return
	 * @throws Exception
	 */
	public QueryProvincesAndCitysResponse queryProvinceList() throws Exception ;

	/**
	 * 查询城市信息
	 * @author zjk
	 * 2017年8月22日
	 * @param provinceCode
	 * @return
	 * @throws Exception
	 */
	public QueryProvincesAndCitysResponse queryCityByProvinceCode(String provinceCode) throws Exception ;

	/**
	 * 查询区县信息
	 * @author zjk
	 * 2017年8月22日
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	public QueryProvincesAndCitysResponse queryAreaByCityId(String cityId) throws Exception ;
	
	/**
	 * 查询城市名称
	 * @author zjk
	 * 2017年8月22日
	 * @param cityId
	 * @return
	 * @throws Exception
	 */
	public String queryCityNameById(Integer cityId) throws Exception;
	
	/**
	 * 查询区县名称
	 * @author zjk
	 * 2017年8月22日
	 * @param areaId
	 * @return
	 * @throws Exception
	 */
	public String queryAreaNameById(Integer areaId) throws Exception;
	
	/**
	 * 查询省份名称
	 * @author zjk
	 * 2017年8月22日
	 * @param provinceId
	 * @return
	 * @throws Exception
	 */
	public String queryProvinceNameById(Integer provinceId) throws Exception;

    /**
     * 查询所有城市信息
     * @author zjk
     * 2017年8月22日
     * @return
     * @throws Exception
     */
    QueryProvincesAndCitysResponse queryAllCitys() throws Exception;

    /**
     * 查询省列表及每个省下属的市列表集合（树形结构）
     * @author zhangli
     * @date 2016年12月16日
     * @return
     * @throws Exception
     */
    QueryProvincesAndCitysResponse queryAllProvinceCityList() throws Exception;
	
}

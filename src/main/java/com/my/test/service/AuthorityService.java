package com.my.test.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Authority;

import java.util.List;

/**
 * Created by wang on 2018/11/28.
 */
public interface AuthorityService {

    List<Authority> queryAuthorityListByRoleId(Long id) throws Exception;

    List<Authority> queryAllAuthorityList(Integer pageNum, Integer pageSize) throws Exception;

    void addAuthority(Authority authority) throws Exception;

    Authority queryAuthority(Authority authority) throws Exception;

    Authority queryAuthorityInfo(Authority authority) throws Exception;

    void updateAuthority(Authority authority) throws Exception;

    void deleteAuthority(Long[] ids) throws Exception;
}

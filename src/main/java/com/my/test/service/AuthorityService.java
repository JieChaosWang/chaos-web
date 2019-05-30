package com.my.test.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Authority;

import java.util.List;

/**
 * Created by wang on 2018/11/28.
 */
public interface AuthorityService {

    List<Authority> queryAuthorityListByRoleId(Long id);

    List<Authority> queryAllAuthorityList(Integer pageNum, Integer pageSize);

    void addAuthority(Authority authority);

    Authority queryAuthority(Authority authority);

    void updateAuthority(Authority authority);

    void deleteAuthority(Long[] ids);
}

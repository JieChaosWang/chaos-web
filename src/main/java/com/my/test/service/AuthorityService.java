package com.my.test.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Authority;

import java.util.List;

/**
 * Created by wang on 2018/11/28.
 */
public interface AuthorityService {

    List<Authority> findByRoleId(Long id);

    List<Authority> findAll(Integer pageNum, Integer pageSize);

    void save(Authority authority);

    Authority getOne(Long id);

    void update(Authority authority);

    Authority  delete(Long[] ids);
}

package com.my.test.service.impl;

import com.github.pagehelper.Page;
import com.my.test.mapper.AuthorityCategoryMapper;
import com.my.test.pojo.AuthorityCategory;
import com.my.test.service.AuthorityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wang on 2018/11/30.
 */
@Service
public class AuthorityCategoryServiceImpl implements AuthorityCategoryService {

    @Autowired
    private AuthorityCategoryMapper authorityCategoryMapper;
    @Override
    @Transactional
    public List<AuthorityCategory> queryAuthorityCategoryList(AuthorityCategory authorityCategory){

        List<AuthorityCategory> list = list = authorityCategoryMapper.queryAuthorityCategoryList(authorityCategory);
        return list;
    }

    /**
     * 添加权限分类
     * @param authorityCategory
     */
    @Override
    public void addAuthorityCategory(AuthorityCategory authorityCategory) {
        authorityCategoryMapper.addAuthorityCategory(authorityCategory);
    }
    /**
     * 查询权限分类
     * @param authorityCategory
     */
    @Transactional(readOnly = true)
    public AuthorityCategory queryAuthorityCategory(AuthorityCategory authorityCategory) {
        return authorityCategoryMapper.queryAuthorityCategory(authorityCategory);
    }

    /**
     * 删除权限分类
     * @param authorityCategory
     */
    @Override
    public void delAuthorityCategory(Long[] authorityCategoryIds) {

        for(Long id:authorityCategoryIds){
            authorityCategoryMapper.delAuthorityCategory(id);
        }

    }
}

package com.my.test.service.impl;

import com.github.pagehelper.Page;
import com.my.test.mapper.AuthorityCategoryMapper;
import com.my.test.mapper.AuthorityMapper;
import com.my.test.pojo.Authority;
import com.my.test.pojo.AuthorityCategory;
import com.my.test.service.AuthorityCategoryService;
import com.my.test.util.BeeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wang on 2018/11/30.
 */
@Service
public class AuthorityCategoryServiceImpl implements AuthorityCategoryService {

    protected static Logger logger = LoggerFactory.getLogger(AuthorityCategoryServiceImpl.class);

    @Autowired
    private AuthorityCategoryMapper authorityCategoryMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    @Transactional
    public List<AuthorityCategory> queryAuthorityCategoryList(AuthorityCategory authorityCategory) throws Exception {
        List<AuthorityCategory> list = null;
        try {
            list = authorityCategoryMapper.queryAuthorityCategoryList(authorityCategory);

        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }

        return list;
    }

    /**
     * 添加权限分类
     * @param authorityCategory
     */
    @Override
    public void addAuthorityCategory(AuthorityCategory authorityCategory) throws Exception {
        try {
            authorityCategoryMapper.addAuthorityCategory(authorityCategory);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
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
    public void delAuthorityCategory(Long[] authorityCategoryIds) throws Exception {
        try {
            for(Long id:authorityCategoryIds){
                Authority authority = new Authority();
                authority.setAuthorityCategoryId(id);

                List<Authority> authorities = authorityMapper.queryAuthorityList(authority);

                if (null != authorities && authorities.size() > 0) {
                    throw new Exception("当前权限分类下，存在权限，请先处理。");
                }

                authorityCategoryMapper.delAuthorityCategory(id);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    /**
     * 删除权限分类
     * @param authorityCategory
     */
    @Override
    public void updateAuthorityCategory(AuthorityCategory authorityCategory) throws Exception {
        try {
            BeeUtils.isEmpty("权限分类主键",authorityCategory.getAuthorityCategoryId());

            authorityCategoryMapper.updateAuthorityCategory(authorityCategory);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}

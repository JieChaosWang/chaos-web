package com.my.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.my.test.mapper.AuthorityMapper;
import com.my.test.pojo.Authority;
import com.my.test.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wang on 2018/11/28.
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public List<Authority> findByRoleId(Long id) {
        return authorityMapper.findByRoleId(id);
    }

    @Override
    public List<Authority> findAll(Integer pageNum, Integer pageSize) {
        return authorityMapper.findAll(pageNum,pageSize);
    }

    /**
     * 权限保存
     * @param authority
     */
    @Override
    public void save(Authority authority) {

        try {
            authority.setCreateDate(new Date());
            authority.setModifyDate(new Date());
            authorityMapper.save(authority);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 权限查询
     * @param id 权限id
     */
    @Transactional(readOnly = true)
    public Authority getOne(Long id) {
        return authorityMapper.getOne(id);
    }

    /**
     * 权限更新
     * @param authority 权限
     */
    @Transactional
    public void update(Authority authority) {
        try{
            authority.setModifyDate(new Date());
            authorityMapper.update(authority);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 删除权限
     * @param ids 权限id数组
     */
    @Transactional
    public Authority delete(Long... ids){
        for(Long id:ids){
            Authority authority = authorityMapper.getOne(id);
            if (authority != null) {
                return authority;
            }
            authorityMapper.delete(id);
        }
        return null;
    }
}

package com.my.test.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.my.test.mapper.AuthorityMapper;
import com.my.test.mapper.RoleAuthorityMapper;
import com.my.test.pojo.Authority;
import com.my.test.pojo.RoleAuthority;
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

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public List<Authority> queryAuthorityListByRoleId(Long id) {
        return authorityMapper.queryAuthorityListByRoleId(id);
    }

    @Override
    public List<Authority> queryAllAuthorityList(Integer pageNum, Integer pageSize) {
        return authorityMapper.queryAllAuthorityList(pageNum,pageSize);
    }

    /**
     * 权限保存
     * @param authority
     */
    @Override
    public void addAuthority(Authority authority) {

        try {
            authority.setCreateDate(new Date());
            authority.setModifyDate(new Date());
            authorityMapper.addAuthority(authority);

            Authority authorityForId = authorityMapper.queryAuthorityInfo(authority);

            RoleAuthority roleAuthority = new RoleAuthority();
            roleAuthority.setRoles_id(1l);
            roleAuthority.setAuthorities_id(authorityForId.getAuthorityCategoryId());

            roleAuthorityMapper.addRoleAuthority(roleAuthority);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * 权限查询
     * @param id 权限id
     */
    @Override
    @Transactional(readOnly = true)
    public Authority queryAuthority(Authority authority) {

        authority = authorityMapper.queryAuthority(authority);

        return authority;
    }

    /**
     * 权限更新
     * @param authority 权限
     */
    @Transactional
    public void updateAuthority(Authority authority) {
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
    @Override
    @Transactional
    public void deleteAuthority(Long... AuthorityIds){
        for(Long id:AuthorityIds){
            Authority authorityForQuery  = new Authority();
            authorityForQuery.setAuthorityId(id);

            Authority authority = authorityMapper.queryAuthority(authorityForQuery);

            authorityMapper.delete(id);
        }
    }
}

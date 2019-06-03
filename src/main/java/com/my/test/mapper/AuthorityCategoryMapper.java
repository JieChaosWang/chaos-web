package com.my.test.mapper;

import com.github.pagehelper.Page;
import com.my.test.pojo.AuthorityCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wang on 2018/11/30.
 */
@Mapper
public interface AuthorityCategoryMapper {

    List<AuthorityCategory> queryAuthorityCategoryList(AuthorityCategory authorityCategory);

    void addAuthorityCategory(AuthorityCategory authorityCategory);

    void delAuthorityCategory(Long authorityCategoryId);

    void updateAuthorityCategory(AuthorityCategory authorityCategory);

    AuthorityCategory queryAuthorityCategory(AuthorityCategory authorityCategory);

}

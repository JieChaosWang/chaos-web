package com.my.test.mapper;

import com.github.pagehelper.Page;
import com.my.test.pojo.AuthorityCategory;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by wang on 2018/11/30.
 */
@Mapper
public interface AuthorityCategoryMapper {

    Page<AuthorityCategory> findAll();

    AuthorityCategory getOne(Long id);

}

package com.my.test.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.my.test.pojo.Authority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wang on 2018/11/28.
 */
@Mapper
public interface AuthorityMapper {

    List<Authority> findByRoleId(Long id);

    List<Authority> findAll(Integer pageNum, Integer pageSize);

    void  save(Authority authority);

    Authority getOne(Long id);

    void update(Authority authority);

    void  delete(Long id);
}

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

    List<Authority> queryAuthorityListByRoleId(Long id);

    List<Authority> queryAllAuthorityList(Integer pageNum, Integer pageSize);

    void addAuthority(Authority authority);

    Authority queryAuthorityInfo(Authority authority);

    Authority queryAuthority(Authority authority);

    void update(Authority authority);

    void delete(Long id);
}

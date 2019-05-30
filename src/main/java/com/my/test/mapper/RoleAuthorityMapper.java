package com.my.test.mapper;

import com.my.test.pojo.RoleAuthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleAuthorityMapper {

    int addRoleAuthority(RoleAuthority roleAuthority);

    int updateRoleAuthority(RoleAuthority roleAuthority);

    List<RoleAuthority> queryRoleAuthorityList(RoleAuthority roleAuthority);
}

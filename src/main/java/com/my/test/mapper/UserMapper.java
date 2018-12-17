package com.my.test.mapper;

import com.my.test.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ********************************************************.<br>
 *
 * @author ldw <br>
 * @classname UserMapper <br>
 * @description TODO <br>
 * @created 2018/11/5 10:57 <br>
 * ********************************************************.<br>
 */
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //这个方式我自己加的
    List<User> selectAllUser();
}

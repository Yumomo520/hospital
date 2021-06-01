package com.goodmap.hospital.mapper;

import com.goodmap.hospital.pojo.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from users where username = #{username}")
    Users selectByName(@Param(value = "username") String username);
}

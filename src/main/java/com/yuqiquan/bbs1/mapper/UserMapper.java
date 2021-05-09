package com.yuqiquan.bbs1.mapper;

import com.yuqiquan.bbs1.modle.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,bio,avatar_url)  values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatar_url})")
    void insert(User user);
    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);
    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
}


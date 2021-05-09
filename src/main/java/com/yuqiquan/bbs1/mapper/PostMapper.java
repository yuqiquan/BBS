package com.yuqiquan.bbs1.mapper;

import com.yuqiquan.bbs1.modle.Postion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {
    @Insert("insert into post(title,description,gmt_create,gmt_modified,creator,tag) values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    public void create(Postion postion);

    @Select("select * from post")
    List<Postion> list();
}

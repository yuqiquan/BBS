package com.yuqiquan.bbs1.service;

import com.yuqiquan.bbs1.datomod.PostDto;
import com.yuqiquan.bbs1.mapper.PostMapper;
import com.yuqiquan.bbs1.mapper.UserMapper;
import com.yuqiquan.bbs1.modle.Postion;
import com.yuqiquan.bbs1.modle.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    public List<PostDto> list() {
        List<Postion> postionList = postMapper.list();
        List<PostDto> postDtoList = new ArrayList<>();
        for (Postion postion : postionList) {
             User user = userMapper.findById(postion.getCreator());
            PostDto postDto = new PostDto();
            //Spring boots有的快捷功能：将第一个参数类里的值付给第二个参数类里的值
            BeanUtils.copyProperties(postion,postDto);
            postDto.setUser(user);
            postDtoList.add(postDto);
        }
        return postDtoList;
    }
}

package com.yuqiquan.bbs1.controller;

import com.yuqiquan.bbs1.datomod.PostDto;
import com.yuqiquan.bbs1.mapper.PostMapper;
import com.yuqiquan.bbs1.mapper.UserMapper;
import com.yuqiquan.bbs1.modle.Postion;
import com.yuqiquan.bbs1.modle.User;
import com.yuqiquan.bbs1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length !=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        List<PostDto> postDtoList = postService.list() ;
        model.addAttribute("postDtoList",postDtoList);
        return "index";
    }
}

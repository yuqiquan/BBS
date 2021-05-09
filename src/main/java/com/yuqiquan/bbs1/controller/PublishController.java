package com.yuqiquan.bbs1.controller;

import com.yuqiquan.bbs1.mapper.PostMapper;
import com.yuqiquan.bbs1.mapper.UserMapper;
import com.yuqiquan.bbs1.modle.Postion;
import com.yuqiquan.bbs1.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("tag") String tag,
                            HttpServletRequest request,
                            Model model){


        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);


        if(title == null || title == ""){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if(description == null || description == ""){
            model.addAttribute("error","内容不能为空！");
            return "publish";
        }
        if(tag == null || tag == ""){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }
        User user = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length !=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Postion postion = new Postion();
        postion.setTitle(title);
        postion.setDescription(description);
        postion.setTag(tag);
       //这边注意
        postion.setCreator(user.getId());
        postion.setGmt_create(System.currentTimeMillis());
        postion.setGmt_modified(postion.getGmt_create());

        postMapper.create(postion);

        return "redirect:/";
    }
}


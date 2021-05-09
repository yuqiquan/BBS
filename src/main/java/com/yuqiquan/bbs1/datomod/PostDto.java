package com.yuqiquan.bbs1.datomod;

import com.yuqiquan.bbs1.modle.User;
import lombok.Data;

@Data
public class PostDto {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmt_create;
    private Long gmt_modified;
    private Integer view_count;
    private Integer like_count;
    private Integer comment_count;
    private Integer creator;
    private User user;
}

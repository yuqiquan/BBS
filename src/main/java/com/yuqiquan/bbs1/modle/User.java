package com.yuqiquan.bbs1.modle;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String bio;
    private String avatar_url;

}

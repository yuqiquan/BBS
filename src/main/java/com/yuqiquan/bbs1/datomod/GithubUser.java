package com.yuqiquan.bbs1.datomod;

import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private long id;
    private String bio;
    private String avatar_url;

}

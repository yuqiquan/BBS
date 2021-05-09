package com.yuqiquan.bbs1.provider;

import com.alibaba.fastjson.JSON;
import com.yuqiquan.bbs1.datomod.AccessTokenDto;
import com.yuqiquan.bbs1.datomod.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
            try (Response response = client.newCall(request).execute()) {
                String string = response.body().string();
                String[] split = string.split("&");
                String tokenstr = split[0];
                String token = tokenstr.split("=")[1];
                System.out.println(token);
                return token;
            }
          catch (IOException e) {
                e.printStackTrace();
            }
        return null;
        }
     public GithubUser getUser(String accesstoken){
         OkHttpClient client = new OkHttpClient();
         Request request = new Request.Builder()
                 .url("https://api.github.com/user")
                 .header("Authorization","token "+accesstoken)
                 .build();
         try {
             Response response = client.newCall(request).execute();
             String string = response.body().string();
             GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
             return githubUser;
         }
         catch (Exception e){
             e.printStackTrace();
         }
         return null;


     }

}

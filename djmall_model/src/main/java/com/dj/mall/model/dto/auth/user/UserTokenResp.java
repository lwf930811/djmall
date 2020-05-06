package com.dj.mall.model.dto.auth.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserTokenResp implements Serializable {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * token
     */
    private String token;

    /**
     * 昵称
     */
    private String nickName;

}

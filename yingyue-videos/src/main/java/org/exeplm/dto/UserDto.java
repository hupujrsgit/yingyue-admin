package org.exeplm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = -47783455114881757L;

    private Integer id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 头像链接
     */
    private String avatar;
    /**
     * 简介
     */
    private String intro;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 是否绑定手机号
     */
    @JsonProperty("phone_linked")
    private Integer phoneLinked;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 是否绑定微信
     */
    @JsonProperty("wechat_linked")
    private Integer wechatLinked;
}

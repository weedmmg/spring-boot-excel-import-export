package com.home.entity;

import lombok.*;

import java.util.Date;
import javax.persistence.*;
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cxf_user")
public class HomeUser {
    /**
     * ID
     */
    @Id
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机
     */
    @Column(name = "mobile_phone")
    private String mobilePhone;

    /**
     * 电话
     */
    @Column(name = "tel_phone")
    private String telPhone;

    /**
     * 邮件
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 类型
     */
    private String type;

    /**
     * 状态
     */
    private String status;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "crt_time")
    private Date crtTime;

    /**
     * 创建用户id
     */
    @Column(name = "crt_user")
    private String crtUser;

    /**
     * 创建用户姓名
     */
    @Column(name = "crt_name")
    private String crtName;

    /**
     * 创建用户IP
     */
    @Column(name = "crt_host")
    private String crtHost;

    /**
     * 更新时间
     */
    @Column(name = "upd_time")
    private Date updTime;

    /**
     * 更新用户id
     */
    @Column(name = "upd_user")
    private String updUser;

    /**
     * 更新用户姓名
     */
    @Column(name = "upd_name")
    private String updName;

    /**
     * 更新用户IP
     */
    @Column(name = "upd_host")
    private String updHost;

    @Column(name = "del_flg")
    private Integer delFlg;

    private String wxopenid;

    /**
     * 头像
     */
    @Column(name = "img_url")
    private String imgUrl;


}
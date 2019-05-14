package com.home.entity;

import com.home.enums.BoardEnums;
import lombok.*;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cxf_message_board")
public class MessageBoard {
    @Id
    private Long id;

    /**
     * 电话
     */
    @Column(name = "tel_phone")
    private String telphone;

    /**
     * 状态
     */
    private String status;

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

    /**
     * 咨询留言
     */
    private String message;

    /**
     * 回复
     */
    private String reply;

    /**
     * 状态描述
     */
    @Transient
    private String statusDesc;

    public void setStatus(String status){
        this.status=status;
        this.statusDesc= BoardEnums.getIdType(status).getDesc();
    }
}
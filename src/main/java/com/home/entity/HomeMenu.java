package com.home.entity;

import lombok.*;

import java.util.Date;
import javax.persistence.*;
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "base_menu")
public class HomeMenu {
    private Long id;

    private String code;

    private String title;

    @Column(name = "parent_id")
    private Long parentId;

    private String href;

    private String icon;

    private String type;

    @Column(name = "order_num")
    private Integer orderNum;






}
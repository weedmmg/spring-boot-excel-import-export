package com.home.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/11 14:07
 * @description：messageVo
 * @modified By：
 * @version: $
 */
@Data
@ApiModel("咨询提交VO")
public class MessageBoardVo {
    @ApiModelProperty(value="手机号",example="18658466326",required=true)
    private String telphone;
    @ApiModelProperty(value="留言",example="很好",required=true)
    private String message;
}

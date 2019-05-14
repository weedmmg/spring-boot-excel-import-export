package com.home.rest;

import com.home.base.msg.ObjectRestResponse;
import com.home.base.rest.BaseRestController;
import com.home.biz.HomeUserBiz;
import com.home.entity.HomeUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/3 14:48
 * @description：用户rest
 * @modified By：
 * @version: 1.0$
 */
@Api(tags = {"官网 - 用户（登陆） 接口"})
@RestController
@RequestMapping("user")
public class HomeUserRestController extends BaseRestController<HomeUserBiz, HomeUser> {

    @ApiOperation(value = "用户名登陆 ", notes = "用户名登陆 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/login")
    public ObjectRestResponse token(String username, String password) {
        return ObjectRestResponse.success("登陆成功").result(baseBiz.login(username,password));
    }

    @ApiOperation(value = "修改密码", notes = "通过旧密码修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "password", value = "密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "newPassword2", value = "新密码2", required = true, paramType = "query", dataType = "String")
    })
    @PostMapping("/pwd")
    public ObjectRestResponse pwd(String password, String newPassword,String newPassword2) {
        baseBiz.pwd(this.getCurrentId(),password,newPassword,newPassword2);
        return ObjectRestResponse.success("修改成功");
    }
}

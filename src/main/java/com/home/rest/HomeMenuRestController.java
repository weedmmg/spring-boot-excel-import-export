package com.home.rest;

import com.home.base.rest.BaseRestController;
import com.home.base.vo.MenuTree;
import com.home.biz.HomeMenuBiz;
import com.home.entity.HomeMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/3 14:03
 * @description：菜单rest
 * @modified By：
 * @version: 1.0$
 */
@Api(tags = {"官网 - 菜单 接口"})
@RestController
@RequestMapping("menu")
public class HomeMenuRestController extends BaseRestController<HomeMenuBiz, HomeMenu> {
    @ApiOperation(value = "查询菜单 ", notes = "查询菜单 ")
    @GetMapping("/initMenu")
    public List<MenuTree> menu() {
        return baseBiz.queryMenuTree(this.getCurrentUsertype());
    }
}

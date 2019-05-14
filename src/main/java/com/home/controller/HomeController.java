package com.home.controller;

import com.home.base.rest.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * 首页
 *
 * @author chenxf
 * @date 2019-01-03 12:33
 **/
@Controller
@RequestMapping("")
public class HomeController extends BaseController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String rootIndex(Map<String,Object> map){
        return "admin/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(Map<String,Object> map){
        return "admin/login";
    }

    @RequestMapping(value = "home",method = RequestMethod.GET)
    public String home(Map<String,Object> map){
        return "admin/home";
    }

    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(Map<String,Object> map){
        map.put("user",getCurrentName());
        return "admin/index";
    }

    @RequestMapping(value = "reset_pwd",method = RequestMethod.GET)
    public String pwd(Map<String,Object> map){
        return "admin/pwd";
    }
}

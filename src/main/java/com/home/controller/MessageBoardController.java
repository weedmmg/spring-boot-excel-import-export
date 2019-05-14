package com.home.controller;

import com.home.base.rest.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/9 16:27
 * @description：留言板
 * @modified By：
 * @version: $
 */
@Controller
@RequestMapping("boardv")
public class MessageBoardController extends BaseController {

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Map<String,Object> map){
        return "board/list";
    }

    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(Map<String,Object> map){
        return "board/edit";
    }
}

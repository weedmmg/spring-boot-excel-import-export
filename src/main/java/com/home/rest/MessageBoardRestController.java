package com.home.rest;

import com.github.pagehelper.PageInfo;
import com.home.base.msg.ObjectRestResponse;
import com.home.base.msg.TableResultResponse;
import com.home.base.rest.BaseRestController;
import com.home.biz.MessageBoardBiz;
import com.home.entity.MessageBoard;
import com.home.entity.vo.MessageBoardVo;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/8 9:45
 * @description：留言板
 * @modified By：
 * @version: 1.0$
 */
@Api(tags = {"官网 - 建议咨询 接口"})
@RestController
@RequestMapping("board")
public class MessageBoardRestController extends BaseRestController<MessageBoardBiz, MessageBoard> {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @ApiOperation(value = "提交意见咨询", notes = "提交意见咨询")
    @PostMapping(value = "/submit",consumes = MediaType.ALL_VALUE)
    public ObjectRestResponse submit( @RequestBody MessageBoardVo vo){
            //@ApiParam(name="telphone",value="手机号",required=true)String telphone, @ApiParam(name="message",value="留言",required=true) String message) {
        baseBiz.submit(vo.getTelphone(), vo.getMessage());
        return ObjectRestResponse.success("保存成功");
    }

    @ApiOperation(value = "处理建议咨询 ", notes = "处理建议咨询 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, paramType = "query", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "处理状态", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "reply", value = "处理意见", required = true, paramType = "query", dataType = "String")
    })
    @PutMapping("/process")
    public ObjectRestResponse process(Long id, String status,String reply) {
        baseBiz.process(id,status,reply,this.getCurrentId());
        return ObjectRestResponse.success("保存成功");
    }

    @ApiOperation(value = "查询咨询列表", notes = "查询咨询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "记录数 默认10", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "offset", value = "页码 默认 1", required = false, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "orderStr", value = "排序 默认desc", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "telphone", value = "范围 0商家 1村长", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "name", value = "类型名称", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", required = false, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = false, paramType = "query", dataType = "String"),
    })
    @GetMapping("/list")
    public TableResultResponse<MessageBoard> list(int limit, int offset, String orderStr, String beginTime, String endTime, String telphone, String status) {

        List<MessageBoard> items = baseBiz.list(limit, offset, orderStr, beginTime, endTime, telphone, status);
        PageInfo<MessageBoard> pageInfo = new PageInfo<>(items);
        return new TableResultResponse<MessageBoard>(Integer.parseInt(String.valueOf(pageInfo.getTotal())),
                offset, limit,
                items);
    }
}

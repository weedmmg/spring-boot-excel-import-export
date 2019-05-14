package com.home.biz;

import com.github.pagehelper.PageHelper;
import com.home.base.biz.BaseBiz;
import com.home.base.exceptions.ServiceException;
import com.home.base.util.DateUtil;
import com.home.entity.MessageBoard;
import com.home.enums.BoardEnums;
import com.home.mapper.MessageBoardMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/8 9:37
 * @description：留言板
 * @modified By：
 * @version: 1.0$
 */
@Service
public class MessageBoardBiz extends BaseBiz<MessageBoardMapper, MessageBoard> {

    /**
     * 保存建议咨询
     * @param telphone 手机号
     * @param message 留言
     */
    public void submit(String telphone, String message){
        if(StringUtils.isBlank(telphone)){
            throw new ServiceException("手机号不能为空");
        }
        if(StringUtils.isBlank(message)){
            throw new ServiceException("咨询内容不能为空");
        }
        insertSelective(MessageBoard.builder().telphone(telphone).message(message).build());
    }

    /**
     * 处理建议咨询
     * @param status  状态
     * @param reply   回复/备注
     */
    public void process(Long id,String status, String reply,String userId){
        if(id==null){
            throw new ServiceException("ID不能为空");
        }
        if(StringUtils.isBlank(status)|| BoardEnums.UNTREATED.getKey().equals(status)){
            throw new ServiceException("处理状态不能为未处理");
        }
        if(StringUtils.isBlank(reply)){
            throw new ServiceException("处理内容不能为空");
        }
        updateSelectiveById(MessageBoard.builder().id(id).status(status).reply(reply).updUser(userId).build());
    }

    public List<MessageBoard> list(int limit, int offset, String orderStr, String beginTime, String endTime, String telphone,String status){
        Example example = new Example(MessageBoard.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(telphone)) {
            criteria.andLike("telphone",telphone);
        }
        if (StringUtils.isNotBlank(status)) {
            criteria.andEqualTo("status",status);
        }

        if (StringUtils.isNotBlank(beginTime)) {
            criteria.andGreaterThanOrEqualTo("crtTime", DateUtil.getDate(beginTime));
        }

        if (StringUtils.isNotBlank(endTime)) {
            criteria.andLessThanOrEqualTo("crtTime",  DateUtil.getDate(endTime) );
        }
        example.setOrderByClause("crt_time "+ orderStr);
        PageHelper.startPage(offset, limit);
        return selectByExample(example);
    }
}

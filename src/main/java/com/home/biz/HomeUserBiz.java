package com.home.biz;

import com.home.base.biz.BaseBiz;
import com.home.base.exceptions.ServiceException;
import com.home.base.msg.ObjectRestResponse;
import com.home.base.secruity.JwtTokenUtil;
import com.home.base.secruity.JwtUser;
import com.home.base.secruity.JwtUserFactory;
import com.home.base.util.EncryptUtil;
import com.home.entity.HomeUser;
import com.home.mapper.HomeMenuMapper;
import com.home.mapper.HomeUserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author ：chenxf
 * @date ：Created in 2019/4/3 14:48
 * @description：用户业务Biz
 * @modified By：
 * @version: 1.0$
 */
@Service
public class HomeUserBiz extends BaseBiz<HomeUserMapper, HomeUser> {
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    public String login(String username, String password){
        HomeUser queryEntity = new HomeUser();
        queryEntity.setUsername(username);

        HomeUser user = selectOne(queryEntity);
        chechUser(user,password);
        return setCurrentUserName(user);
    }

    public String setCurrentUserName(HomeUser user){
        JwtUser userDetails= JwtUserFactory.create(user);

        String token= jwtTokenUtil.generateToken(userDetails);
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), userDetails.getAuthorities()); // 进行安全认证
        upToken.setDetails(token);
        SecurityContextHolder.getContext().setAuthentication(upToken);
        return token;
    }


    /**
     * 根据旧密码 修改新密码
     * @param userId 用户ID
     * @param password 密码
     * @param newPassword 新密码
     * @param newPassword2 重复密码
     */
    public void pwd(String userId,String password, String newPassword, String newPassword2) {
        if (StringUtils.isBlank(password)) {
            throw  new ServiceException("密码不能为空");
        }

        if (StringUtils.isBlank(newPassword)) {
            throw  new ServiceException("新密码不能为空");
        }

        if (StringUtils.isBlank(newPassword2)) {
            throw  new ServiceException("重复新密码不能为空");
        }

        if (!newPassword.equals(newPassword2)) {
            throw  new ServiceException("重复新密码与新密码不相同");
        }
        if (StringUtils.isBlank(userId)) {
            throw  new ServiceException("当前用户不存在");
        }

        chechUser(selectById(userId),password);


        updateSelectiveById(HomeUser.builder().id(userId).password(EncryptUtil.encrypt(newPassword)).build());

    }

    /**
     * 检验用户及密码
     * @param user
     * @param password
     */
    public void chechUser( HomeUser user ,String password){
        if (user == null) {
            throw  new ServiceException("用户不存在");
        }
        if (!EncryptUtil.decrypt(user.getPassword()).equals(password)) {
            throw  new ServiceException("密码不正确");
        }
    }
}

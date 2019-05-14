package com.home.base.rest;

import com.home.base.exceptions.DataAccessException;
import com.home.base.secruity.JwtTokenUtil;
import com.home.base.secruity.JwtUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author chenxf
 * @date 4/11/2018
 * @description
 */
public class BaseController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String getCurrentName(){
        String name= jwtTokenUtil.getNameFromToken(getToken());
        if(StringUtils.isBlank(name)){
            throw new DataAccessException("token无效");
        }
        return name;
    }

    private String getToken(){
        SecurityContext sc = SecurityContextHolder.getContext();
        Object principal = sc.getAuthentication().getDetails();
        return principal.toString();
    }



}

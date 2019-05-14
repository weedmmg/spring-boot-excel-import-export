package com.home.base.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;

import com.home.base.biz.BaseBiz;
import com.home.base.exceptions.DataAccessException;
import com.home.base.msg.ObjectRestResponse;
import com.home.base.secruity.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-15 8:48
 */
public class BaseRestController<Biz extends BaseBiz,Entity> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Biz baseBiz;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public static final Logger logger = LoggerFactory.getLogger(BaseRestController.class);

    protected Map<String, Object> resultMap = new HashMap<String, Object>();

    public BaseRestController title(  String title) {
        request.setAttribute("title", title);
        return this;
    }

    public BaseRestController keywords(  String keywords) {
        request.setAttribute("keywords", keywords);
        return this;
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public ObjectRestResponse<Entity> add(Entity entity){
        baseBiz.insertSelective(entity);
        return new ObjectRestResponse<Entity>().rel(true);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<Entity> get(@PathVariable String id){
        return new ObjectRestResponse<Entity>().rel(true).result(baseBiz.selectById(id));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Entity> update(Entity entity){
        baseBiz.updateSelectiveById(entity);
        return new ObjectRestResponse<Entity>().rel(true);
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Entity> remove(@PathVariable Long id){
        baseBiz.deleteById(id);
        return new ObjectRestResponse<Entity>().rel(true);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> list(){
        return baseBiz.selectListAll();
    }

    public String getCurrentUsertype(){

       String usertype= jwtTokenUtil.getUsertypeFromToken(getToken());

        if(StringUtils.isBlank(usertype)){
            throw new DataAccessException("token无效");
        }
        return usertype;
    }

    public String getCurrentId(){
        String userid= jwtTokenUtil.getUseridFromToken(getToken());
        if(StringUtils.isBlank(userid)){
            throw new DataAccessException("token无效");
        }
        return userid;
    }

    private String getToken(){
        SecurityContext sc = SecurityContextHolder.getContext();
        Object principal = sc.getAuthentication().getDetails();
        return principal.toString();
    }

}

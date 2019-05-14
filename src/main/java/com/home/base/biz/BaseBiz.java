package com.home.base.biz;


import com.home.base.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by zhw
 * Date: 17/1/13
 * Time: 15:13
 * Version 1.0.0
 */
public abstract class BaseBiz<M extends Mapper<T>, T> {
    @Autowired
    protected M mapper;

    public void setMapper(M mapper){
        this.mapper = mapper;
    }

    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }


    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }


    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }

    public List<T> selectListAll() {
        return mapper.selectAll();
    }


    public void insert(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);mapper.insert(entity);
    }

    public void insertSelective(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insertSelective(entity);
    }

    public void delete(T entity) {
        mapper.delete(entity);
    }

    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void updateById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKey(entity);
    }

    public void updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKeySelective(entity);

    }
    public  List<T> selectByExample(Object example){
        return mapper.selectByExample(example);
    }
    public int selectCountByExample(Object example){
        return mapper.selectCountByExample(example);
    }

}

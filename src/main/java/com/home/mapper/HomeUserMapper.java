package com.home.mapper;

import com.home.entity.HomeUser;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface HomeUserMapper extends Mapper<HomeUser>, MySqlMapper<HomeUser> {
}
package com.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.system.entity.SystemUser;

public interface UserMapper extends BaseMapper<SystemUser> {

    //findByName方法用于通过用户名查找用户信息
    SystemUser findByName(String username);
}
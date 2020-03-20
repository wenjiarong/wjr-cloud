package com.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.common.entity.system.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    //findUserPermissions方法用于通过用户名查找用户权限集合
    List<Menu> findUserPermissions(String username);
}
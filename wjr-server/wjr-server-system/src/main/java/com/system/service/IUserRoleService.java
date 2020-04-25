package com.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.system.entity.UserRole;

public interface IUserRoleService extends IService<UserRole> {

	void deleteUserRolesByRoleId(String[] roleIds);

	void deleteUserRolesByUserId(String[] userIds);
}
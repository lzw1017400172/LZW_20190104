package com.lzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzw.model.SysMenu;

public interface SysAuthorizeMapper {

	List<String> queryPermissionByUserId(@Param("userId") Long userId);

	List<String> queryAllPermission();

    List<String> queryRoleByUserId(@Param("userId") Long userId);

}

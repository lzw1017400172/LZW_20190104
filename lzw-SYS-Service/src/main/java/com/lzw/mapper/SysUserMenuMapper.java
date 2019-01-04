package com.lzw.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.lzw.core.base.BaseMapper;
import com.lzw.model.SysUserMenu;

public interface SysUserMenuMapper extends BaseMapper<SysUserMenu> {

	List<Map<String, Object>> queryPermissions(@Param("userId") Long userId);
	List<String> queryPermission(@Param("userId") Long id);
	
	int delSysUserMenuByUserId(Long userId);
	int addSysUserMenu(Map<String,Object> map);

	
}
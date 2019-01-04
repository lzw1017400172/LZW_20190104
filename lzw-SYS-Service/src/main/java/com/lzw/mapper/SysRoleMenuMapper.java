package com.lzw.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.lzw.core.base.BaseMapper;
import com.lzw.model.SysRoleMenu;

public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
	List<Long> queryMenuIdsByRoleId(@Param("roleId") Long roleId);

	List<Map<String, Object>> queryPermissions(@Param("roleId") Long roleId);

	List<String> queryPermission(@Param("roleId") Long id);

	void deleteRoleId(@Param("roleId") Long roleId);

	
	
	
	
	void insertAll(List<SysRoleMenu> trueList);
}

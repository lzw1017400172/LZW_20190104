package com.lzw.service;

import com.lzw.core.base.BaseService;
import com.lzw.mapper.SysUserMenuMapper;
import com.lzw.model.SysUserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SysUser服务实现类
 * 
 * @author ShenHuaJie
 * @version 2016-08-27 22:39:42
 */
@Service
public class SysUserMenuService extends BaseService<SysUserMenu> {
	@Autowired
	private SysCacheService sysCacheService;
	@Autowired
	protected SysUserMenuMapper sysUserMenuMapper;

	@Transactional
	public int addSysUserMenu(Map<String , Object> map)
	{
		Long userId = (Long)map.get("selectUserId");
		@SuppressWarnings("unchecked")
		List<SysUserMenu> list = (List<SysUserMenu>)map.get("sysUserMenuList");
		sysUserMenuMapper.delSysUserMenuByUserId(userId);
		int sum =0;
		for (SysUserMenu sysUserMenu : list) 
		{
			int i = sysUserMenuMapper.insert(sysUserMenu);
			sum = sum + i;
		}
		Map<String, String> mapw = new  HashMap<>();
		mapw.put("key", "Permission");
		sysCacheService.flush(mapw);
		return sum;
	}
}

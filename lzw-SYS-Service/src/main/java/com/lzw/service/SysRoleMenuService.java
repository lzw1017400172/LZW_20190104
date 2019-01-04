package com.lzw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lzw.core.Constants;
import com.lzw.core.base.BaseService;
import com.lzw.mapper.SysRoleMenuMapper;
import com.lzw.model.SysRoleMenu;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@Service
public class SysRoleMenuService extends BaseService<SysRoleMenu> {
	@Autowired
	private SysCacheService sysCacheService;
	@Transactional
    @CacheEvict(value = {Constants.CACHE_NAMESPACE + "sysPermission"}, allEntries = true)
	public void updateAllBeforeDelete(List<SysRoleMenu> list){
		if(list!=null&&list.size()>0){
			((SysRoleMenuMapper)mapper).deleteRoleId(list.get(0).getRoleId());
			updateAll(list);
		}
		Map<String, String> map = new  HashMap<>();
		map.put("key", "Permission");
		sysCacheService.flush(map);
	}
	
	public List<Long> queryIdList(Map<String,Object> params){
		return mapper.selectIdPage(params);
	}
}

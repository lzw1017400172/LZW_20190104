package com.lzw.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.lzw.core.base.BaseService;
import com.lzw.model.SysEvent;
import com.lzw.model.SysUserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@CacheConfig(cacheNames = "sysEvent")
public class SysEventService extends BaseService<SysEvent> {
	@Autowired
	private SysUserInfService sysUserInfService;

	public Page<SysEvent> query(Map<String, Object> params) {
		Page<SysEvent> page = super.query(params);
		for (SysEvent sysEvent : page.getRecords()) {
			Long createBy = sysEvent.getCreateBy();
			if (createBy != null) {
				SysUserInf sysUser = sysUserInfService.queryById(createBy);
				if (sysUser != null) {
					sysEvent.setUserName(sysUser.getUserName());
				} else {
					sysEvent.setUserName(createBy.toString());
				}
			}
		}
		return page;
	}
}

package com.lzw.service;

import com.lzw.core.base.BaseService;
import com.lzw.model.SysRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

/**
 * @author ShenHuaJie
 * @version 2016年5月31日 上午11:01:33
 */
@Service
@CacheConfig(cacheNames = "sysRole")
public class SysRoleService extends BaseService<SysRole> {
}

package com.lzw.service;

import com.lzw.core.base.BaseService;
import com.lzw.core.util.CacheUtil;
import com.lzw.core.util.InstanceUtil;
import com.lzw.mapper.SysUserInfMapper;
import com.lzw.mapper.SysUserLoginMapper;
import com.lzw.model.SysUserInf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author liuxd
 * @since 2019-01-04
 */
@Service
public class SysUserInfService extends BaseService<SysUserInf> {

    @Autowired
    private SysUserLoginMapper sysUserLoginMapper;

    @Autowired
    private SysUserInfMapper sysUserInfMapper;

    public void init() {
        List<Long> list = sysUserLoginMapper.selectIdPage(InstanceUtil.newHashMap());
        for (Long id : list) {
            CacheUtil.getCache().set(getCacheKey(id), sysUserLoginMapper.selectById(id));
            CacheUtil.getCache().set(getCacheKey(id), sysUserInfMapper.selectById(id));
        }
    }
}

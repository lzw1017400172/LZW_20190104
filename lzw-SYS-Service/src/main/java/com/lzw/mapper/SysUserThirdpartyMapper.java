package com.lzw.mapper;

import org.apache.ibatis.annotations.Param;
import com.lzw.core.base.BaseMapper;
import com.lzw.model.SysUserThirdparty;

public interface SysUserThirdpartyMapper extends BaseMapper<SysUserThirdparty> {
	Long queryUserIdByThirdParty(@Param("provider") String provider, @Param("openId") String openId);
}
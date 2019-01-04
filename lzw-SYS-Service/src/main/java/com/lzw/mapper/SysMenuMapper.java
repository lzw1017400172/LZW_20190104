package com.lzw.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.lzw.core.base.BaseMapper;
import com.lzw.model.SysMenu;

public interface SysMenuMapper extends BaseMapper<SysMenu> {
	public List<Long> selectIdPage(@Param("cm") Map<String, Object> params);
}
package com.lzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lzw.core.base.BaseMapper;
import com.lzw.model.SysSession;

public interface SysSessionMapper extends BaseMapper<SysSession> {

    Long queryBySessionId(String sessionId);

    List<String> querySessionIdByAccount(@Param("cm") SysSession sysSession);

}
package com.lzw.service;

import com.lzw.core.base.BaseService;
import com.lzw.core.util.CacheUtil;
import com.lzw.core.util.InstanceUtil;
import com.lzw.mapper.SysSessionMapper;
import com.lzw.model.SysSession;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
@Service
@CacheConfig(cacheNames = "sysSession")
public class SysSessionService extends BaseService<SysSession> {

	@CachePut
	@Transactional
	public SysSession update(SysSession record) {
		if (record.getId() == null) {
			Long id = ((SysSessionMapper) mapper).queryBySessionId(record.getSessionId());
			if (id != null) {
				mapper.updateById(record);
			} else {
				mapper.insert(record);
			}
		} else {
			mapper.updateById(record);
		}
		return record;
	}

	// 系统触发,由系统自动管理缓存
	public void deleteBySessionId(final SysSession sysSession) {
		Map<String,Object> delMap = new HashMap<>();
		delMap.put("session_id", sysSession.getSessionId());
		mapper.deleteByMap(delMap);
	}
	
	public List<String> querySessionIdByAccount(SysSession sysSession) {
		return ((SysSessionMapper) mapper).querySessionIdByAccount(sysSession);
	}

	// 系统触发,由系统自动清除那些 缓存中过期之后，sys_session没有清除的数据
	public void cleanExpiredSessions() {
		Map<String, Object> columnMap = InstanceUtil.newHashMap();
		List<SysSession> sessions = queryList(columnMap);
		for (SysSession sysSession : sessions) {
			logger.info("检查SESSION : {}", sysSession.getSessionId());
			if (!CacheUtil.getCache().exists(sysSession.getSessionId())) {
				logger.info("移除SESSION : {}", sysSession.getSessionId());
				delete(sysSession.getId());
			}
		}
	}
}
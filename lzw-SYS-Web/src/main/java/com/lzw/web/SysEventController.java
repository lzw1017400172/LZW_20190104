package com.lzw.web;

import java.util.Map;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lzw.core.base.AbstractController;
import com.lzw.provider.ISysProvider;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统日志控制类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:13:31
 */
@RestController
@Api(value = "系统日志", description = "系统日志")
@RequestMapping(value = "event")
public class SysEventController extends AbstractController<ISysProvider> {
	public String getService() {
		return "sysEventService";
	}

	@ApiOperation(value = "查询日志")
	// @RequiresPermissions("sys.base.event.read")
	@PostMapping(value = "/read/list")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.query(modelMap, param);
	}
}

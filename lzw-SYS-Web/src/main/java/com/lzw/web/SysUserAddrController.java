package com.lzw.web;

import com.lzw.core.base.AbstractController;
import com.lzw.model.SysUserAddr;
import com.lzw.provider.ISysProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 字典管理
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:14:34
 */
@RestController
@Api(value = "用户地址", description = "用户地址")
@RequestMapping(value = "/user_addr")
public class SysUserAddrController extends AbstractController<ISysProvider> {

	public String getService() {
		return "sysUserAddrService";
	}

	@ApiOperation(value = "查询用户地址")
//	@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "/read/page")
	public Object query(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.query(modelMap, param);
	}

	@ApiOperation(value = "查询用户地址")
	//@RequiresPermissions("sys.base.dic.read")
	@PutMapping(value = "/read/list")
	public Object queryList(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.queryList(modelMap, param);
	}

	@PostMapping
	@ApiOperation(value = "修改用户地址")
//	@RequiresPermissions("sys.base.dic.update")
	public Object update(ModelMap modelMap, @RequestBody SysUserAddr param) {
		return super.update(modelMap, param);
	}

	@DeleteMapping
	@ApiOperation(value = "删除用户地址")
	//@RequiresPermissions("sys.base.dic.delete")
	public Object delete(ModelMap modelMap, @RequestBody SysUserAddr param) {
		return super.delete(modelMap, param);
	}
}

package com.lzw.web;

import com.lzw.core.base.AbstractController;
import com.lzw.core.base.Parameter;
import com.lzw.core.support.Assert;
import com.lzw.core.support.HttpCode;
import com.lzw.core.util.SecurityUtil;
import com.lzw.core.util.UploadUtil;
import com.lzw.model.SysUserLogin;
import com.lzw.provider.ISysProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 用户管理控制器
 * 
 * @author hzy
 */
@RestController
@Api(value = "用户管理")
@RequestMapping(value = "/user")
public class SysUserController extends AbstractController<ISysProvider> {
	
	public String getService() {
		return "sysUserLoginService";
	}

	// 分页查询用户
	@ApiOperation(value = "查询用户")
	// @RequiresPermissions("sys.base.user.read")
	@PostMapping(value = "/read/page")
	public Object query(ModelMap modelMap,@RequestBody Map<String, Object> param) {
        return super.query(modelMap,param);
	}

	// 无分页查询用户
	@ApiOperation(value = "查询用户")
	// @RequiresPermissions("sys.base.user.read")
	@PostMapping(value = "/read/list")
	public Object queryList(ModelMap modelMap, @RequestBody Map<String, Object> param) {
		return super.queryList(modelMap, param);
	}

	// 当前用户
//	@ApiOperation(value = "当前用户信息")
//	@GetMapping(value = "/read/current")
//	public Object current(ModelMap modelMap) {
//		SysUser param = new SysUser();
//		param.setId(getCurrUser());
//		return super.get(modelMap, param);
//	}

//	@ApiOperation(value = "修改个人信息")
//	@PostMapping(value = "/update/person")
//	public Object updatePerson(ModelMap modelMap, @RequestBody SysUser param) {
//		param.setId(getCurrUser());
//		param.setPassword(null);
//		Assert.isNotBlank(param.getAccount(), "ACCOUNT");
//		Assert.length(param.getAccount(), 3, 15, "ACCOUNT");
//		return super.update(modelMap, param);
//	}
//
//	@ApiOperation(value = "修改用户头像")
//	@PostMapping(value = "/update/avatar")
//	public Object updateAvatar(HttpServletRequest request, ModelMap modelMap) {
//		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
//		MultipartFile file = req.getFile("file");
//		List<String> fileNames = UploadUtil.uploadImageData(request);
//		if (fileNames.size() > 0) {
//			SysUser param = new SysUser();
//			param.setId(getCurrUser());
//			for (int i = 0; i < fileNames.size(); i++) {
//				String filePath = UploadUtil.getUploadDir(request)
//						+ fileNames.get(i);
//				String avatar = UploadUtil.remove2DFS("sysUser",
//						"U" + param.getId(), filePath).getRemotePath();
//				param.setAvatar(avatar);
//			}
//			modelMap.put("data", param);
//			return super.update(modelMap, param);
//		} else {
//			modelMap.put("msg", "请选择要上传的文件！");
//			return setModelMap(modelMap, HttpCode.BAD_REQUEST);
//		}
//	}
//
//	/**
//	 * 修改密码
//	 *
//	 */
//	@RequestMapping("/update/password")
//	public Object updateUserPwd(ModelMap modelMap,
//			@RequestBody Map<String, Object> param) {
//		// int userId,String oldPwd,String newPwd
//		Assert.isNotBlank((String) param.get("oldPassword"), "OLDPASSWORD");
//		Assert.isNotBlank((String) param.get("password"), "password");
//		Long userId = getCurrUser();
//		Parameter updatepParameter = new Parameter(getService(), "queryById")
//				.setId(userId);
//		SysUser model = (SysUser) provider.execute(updatepParameter).getModel();
//		if (SecurityUtil.validatePassword((String) param.get("oldPassword"),
//				model.getPassword())) {
//			model.setPassword(SecurityUtil.entryptPassword((String) param
//					.get("password")));
//			Parameter passParameter = new Parameter(getService(), "update")
//					.setModel(model);
//			provider.execute(passParameter);
//			return setModelMap(modelMap, HttpCode.OK);
//		} else {
//			modelMap.put("msg", "原始密码输入错误");
//			return setModelMap(modelMap, HttpCode.INTERNAL_SATICEFY_ERROR);
//		}
//
//	}
//
//	/**
//	 * 重置密码
//	 *
//	 * @param modelMap
//	 * @param param
//	 * @return
//	 */
//	@PostMapping("/update/reset_userpwd")
//	// @RequiresPermissions("sys.base.user.delete")
//	public Object resetUserPwd(ModelMap modelMap, @RequestBody SysUser param) {
//		Assert.notNull(param.getId(), "ID");
//		param.setPassword(StringUtils.isNotBlank(param.getPassword()) ? SecurityUtil.entryptPassword(param.getPassword()) :SecurityUtil.entryptPassword("123456"));
//		return super.update(modelMap, param);
//	}

	
	// 获取菜单
//	@ApiOperation(value = "获取当前登录人菜单")
//	@PostMapping(value = "/get_menu")
//	public Object getMenu(ModelMap modelMap, HttpServletRequest request,@RequestBody Map<String,Object> param) {
//		String type = "WEB";
//		if(param.get("type") != null){
//			type = param.get("type").toString();
//		}
//		Long userId = getCurrUser();
//		Long tenantId = getCurrTenant();
//		int userType = WebUtil.getCurrentUserType(request);
//
//		//获取当前租户在有效期内的modelIds
//		List<Long> modelIds = ModelMenuUtils.getTenantModelUndueCache(tenantId,provider);
//		Parameter modelParam = new Parameter("sysModelService","getList").setList(modelIds);
//		List<SysModel> modelList = (List<SysModel>) provider.execute(modelParam).getList();
//
//		List<Long> modelIdsByType = new ArrayList<>();
//		for(SysModel model:modelList){
//			if(model != null && type.equals(model.getType())){
//				modelIdsByType.add(model.getId());
//			}
//		}
//
//		List<SysModelMenu> treeMenuList = new ArrayList<>();
//		if(modelIdsByType.size() > 0){
//			// 判断是否是管理员
//			if (Constants.ADMINISTRATOR == userType) {
//				//查询所有 menu
//				Parameter menuParam = new Parameter("sysModelMenuService","queryListByModelIds").setList(modelIdsByType);
//				treeMenuList = (List<SysModelMenu>) provider.execute(menuParam).getList();
//
//				if(treeMenuList.size() > 0){
//					treeMenuList = ConstantSystem.treeMenuList(treeMenuList, 0l);
//				}
//			} else {
//				//获取普通用户的菜单
//				Parameter menuParam = new Parameter("sysAuthorizeService","queryUserMenus").setId(userId);
//				List<SysModelMenu> menuList = (List<SysModelMenu>) provider.execute(menuParam).getList();
//
//				for(SysModelMenu menu : menuList){
//					if(menu != null && modelIdsByType.contains(menu.getModelId())){
//						treeMenuList.add(menu);
//					}
//				}
//				if(treeMenuList.size() > 0){
//					treeMenuList = ConstantSystem.treeMenuList(treeMenuList,0l);
//				}
//			}
//		}
//		return setSuccessModelMap(modelMap,treeMenuList);
//	}

//	@ApiOperation(value = "获取当前登录人EncryptAccount")
//	@PostMapping(value = "/get_encrypt_account")
//	public Object getAccountMd5(ModelMap modelMap) {
//		Long userId = getCurrUser();
//		Parameter parameter = new Parameter(getService(), "queryById")
//				.setId(userId);
//		logger.info("{} execute queryById start...", parameter.getNo());
//		SysUser result = (SysUser) provider.execute(parameter).getModel();
//		logger.info("{} execute queryById end.", parameter.getNo());
//
//		String account = result.getAccount();
//		String encrypt32Md5 = SecurityUtil.encrypt32Md5(account);
//		return setSuccessModelMap(modelMap, encrypt32Md5);
//	}
	
//	@ApiOperation(value = "手机短信--找回密码，验证短信，返回 租户列表")
//	@PostMapping("/anon/retrieve_password/validate")
//	public Object retrievePassword(ModelMap modelMap, @RequestBody SysUser sysUser,HttpServletRequest request) {
//		if (StringUtils.isBlank(sysUser.getPhone())) {
//			modelMap.put("msg", "未填写手机号！");
//			return setModelMap(modelMap, HttpCode.INTERNAL_SATICEFY_ERROR);
//		}
//
//		if (StringUtils.isBlank(sysUser.getRemark())) {
//			modelMap.put("msg", "未填写短信验证码！");
//			return setModelMap(modelMap, HttpCode.INTERNAL_SATICEFY_ERROR);
//		}
//
//		// 手机号格式验证
//		String regex = "((^(13|15|17|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
//		Pattern pattern = Pattern.compile(regex);
//		Matcher matcher = pattern.matcher(sysUser.getPhone());
//		if (!matcher.matches()) {
//			modelMap.put("msg", "请填入正确的手机号！");
//			return setModelMap(modelMap, HttpCode.INTERNAL_SATICEFY_ERROR);
//		}
//
//		String key = Constants.RETRIEVE_CODE + ":" + sysUser.getPhone();
//		Boolean flag = CheckCodeUtil.validateMessageCheckCode(sysUser.getRemark(),key);
//		if(flag == null){
//			modelMap.put("msg", "短信验证码已经过期，请重新获取！");
//			return setModelMap(modelMap, HttpCode.GONE);
//		}else{
//			if(flag){
//				//将此人手机号保存到session，选择租户重置密码提交的时候，账号必须是这个手机号。
//				WebUtil.setSession(Constants.RETRIEVE_CODE,sysUser.getPhone());
//				//返回这个user对应的所有unit
//				Map<String, Object> params = new HashMap<String, Object>();
//				params.put("enable", 1);
//				params.put("account", sysUser.getPhone());
//				Parameter parameter = new Parameter("sysUserService", "queryList").setMap(params);
//				logger.info("{} execute sysUserService.queryList  start...", parameter.getNo());
//				List<SysUser> list = (List<SysUser>) provider.execute(parameter).getList();
//				logger.info("{} execute sysUserService.queryList end.", parameter.getNo());
//				if(list != null && list.size() > 0){
//					List<Long> tenantIds = new ArrayList<>();
//					for (SysUser object : list) {
//						if(object != null){
//							tenantIds.add(object.getTenantId());
//						}
//					}
//					Parameter sysUnitParameter = new Parameter("sysUnitService", "getList").setList(tenantIds);
//					List<SysUnit> sysUnitList = (List<SysUnit>) provider.execute(sysUnitParameter).getList();
//					List<UserBelongedToUnit> returnMap = new ArrayList<>();
//					for(SysUnit unit:sysUnitList){
//						if(null != unit){
//							UserBelongedToUnit ubtu = new UserBelongedToUnit(unit.getUnitName(),unit.getUnitCode());
//							for (SysUser object : list) {
//								if(unit.getId().longValue() == object.getTenantId().longValue()){
//									ubtu.setUserId(object.getId());
//									break;
//								}
//							}
//							returnMap.add(ubtu);
//						}
//					}
//					modelMap.put("msg", "验证通过！");
//					return setSuccessModelMap(modelMap, returnMap);
//				}else{
//					modelMap.put("msg", "没有对应账号!!");
//					return setModelMap(modelMap, HttpCode.GONE);
//				}
//			}else{
//				modelMap.put("msg", "和短信验证码不一致，请重新输入！");
//				return setModelMap(modelMap, HttpCode.CONFLICT);
//			}
//		}
//	}
//
//	@ApiOperation(value = "手机短信找回密码，已经验证通过，然后重置密码")
//	@PostMapping("/anon/retrieve_password/reset")
//	public Object resetPassword(ModelMap modelMap, @RequestBody SysUser sysUser,HttpServletRequest request) {
//		Assert.notNull(sysUser.getId(), "id");
//		/**	密码6-20位，数字+字母组合			^[A-Za-z0-9]+$	*/
//		Assert.pattern(sysUser.getPassword(),"^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,20}$",true,"密码需为6-20位字母和数字组合");
//		String key = Constants.RETRIEVE_CODE;
//
//		//获取当前浏览器————登录人的账号
//		HttpSession session = request.getSession();
//		String phone = null;
//		if(null != session){
//			Object account = session.getAttribute(key);
//			if(null != account){
//				phone = account.toString();
//			}
//		}
//
//		if(phone == null){
//			modelMap.put("msg", "短信验证码已失效，请返回上一步重新获取验证码。");
//			return setModelMap(modelMap, HttpCode.GONE);
//		} else {
//			//验证
//			Parameter parameter = new Parameter("sysUserService", "queryById").setId(sysUser.getId());
//			parameter = provider.execute(parameter);
//			SysUser user = parameter == null ? null : (SysUser)parameter.getModel();
//			if(null == user){
//				modelMap.put("msg", "没有对应账号!!");
//				return setModelMap(modelMap, HttpCode.GONE);
//			}
//			if(!user.getAccount().equals(phone)){
//				modelMap.put("msg", "您选择的账号和刚刚验证通过的账号不匹配。");
//				return setModelMap(modelMap, HttpCode.CONFLICT);
//			}
//			//重置密码
//			SysUser userReset = new SysUser();
//			userReset.setId(user.getId());
//			userReset.setPassword(SecurityUtil.entryptPassword(sysUser.getPassword()));
//			Parameter parameterUp = new Parameter("sysUserService", "update").setModel(userReset);
//			provider.execute(parameterUp);
//			return setSuccessModelMap(modelMap);
//		}
//	}






}


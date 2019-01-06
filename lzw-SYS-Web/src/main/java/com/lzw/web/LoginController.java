package com.lzw.web;

import com.lzw.constant.LoginConstant;
import com.lzw.core.Constants;
import com.lzw.core.base.AbstractController;
import com.lzw.core.base.Parameter;
import com.lzw.core.config.Resources;
import com.lzw.core.shiro.Realm;
import com.lzw.core.support.Assert;
import com.lzw.core.support.HttpCode;
import com.lzw.core.support.cache.shiro.RedisSessionDao;
import com.lzw.core.support.login.LoginHelper;
import com.lzw.core.util.CacheUtil;
import com.lzw.core.util.CheckCodeUtil;
import com.lzw.core.util.InstanceUtil;
import com.lzw.core.util.PropertiesUtil;
import com.lzw.model.Login;
import com.lzw.model.SysSession;
import com.lzw.model.SysUserLogin;
import com.lzw.provider.ISysProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:11:21
 */
@RestController
@Api(value = "登录接口", description = "登录接口")
public class LoginController extends AbstractController<ISysProvider> {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    @Qualifier("sessionDAO")
    private RedisSessionDao redisSessionDao;

    @Autowired
    @Qualifier("realm")
    private Realm realm;


	public String getService() {
		return "sysUserService";
	}
	
	@ApiOperation(value = "用户登录【账号+密码+验证码登录】")
	@PostMapping("/login")
	public Object login(@ApiParam(required = true, value = "登录帐号和密码") @RequestBody Login user, ModelMap modelMap,
			HttpServletRequest request) {
		Assert.isNotBlank(user.getCheckCode(), "checkCode");
		Assert.isNotBlank(user.getAccount(), "ACCOUNT");
		Assert.isNotBlank(user.getPassword(), "PASSWORD");
		//判断是否已经登陆
		if(SecurityUtils.getSubject().isAuthenticated()){
			modelMap.put("msg", "已经登陆，请不要重复登陆!!");
			return setModelMap(modelMap, HttpCode.CONFLICT);
		}
		//验证验证码
		Boolean bln = CheckCodeUtil.validateCheckCode(user.getCheckCode());
		if(bln == null){
			modelMap.put("msg", "验证码已经失效，请刷新验证码!!");
			return setModelMap(modelMap, HttpCode.GONE);
		} else {
			if(bln){
				return accountAndPwdLogin(user,modelMap);
			}else{
				request.setAttribute("msg", "[" + user.getAccount() + "]登录失败.");
				modelMap.put("msg", "验证码错误!!");
				return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
			}
		}
	}

    @ApiOperation(value = "用户登录【账号+密码 登录】")
    @PostMapping("/login/novalidation")
    public Object loginNovalidation(@ApiParam(required = true, value = "登录帐号和密码") @RequestBody Login user, ModelMap modelMap,
                        HttpServletRequest request) {
        Assert.isNotBlank(user.getAccount(), "ACCOUNT");
        Assert.isNotBlank(user.getPassword(), "PASSWORD");
        //判断是否已经登陆
        if(SecurityUtils.getSubject().isAuthenticated()){
            modelMap.put("msg", "已经登陆，请不要重复登陆!!");
            return setModelMap(modelMap, HttpCode.CONFLICT);
        }
        return accountAndPwdLogin(user,modelMap);
    }

	/**
	 * 账号密码登录
	 * @param user
	 * @param modelMap
	 * @return
	 */
	private Object accountAndPwdLogin(Login user,ModelMap modelMap){
		List<SysUserLogin> list = this.getUserByAccount(user.getAccount());
		if(list.size() == 0){
			modelMap.put("msg", Resources.getMessage("登录失败，账号不存在!!"));
			return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
		}
		if(list.get(0).getLogin() == 2){
			modelMap.put("msg", Resources.getMessage("登录失败，账号被禁止登录!!"));
			return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
		}
		if (LoginHelper.login(user.getAccount(), user.getPassword())) {
			return setSuccessModelMap(modelMap);
		}
		modelMap.put("msg", Resources.getMessage("登录失败，账号或者密码错误!!"));
		return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
	}

	/**
	 * 免密码登录
	 * @param user
	 * @param modelMap
	 * @return
	 */
	private Object accountLogin(Login user,ModelMap modelMap){
		List<SysUserLogin> list = this.getUserByAccount(user.getAccount());
		if(list.size() == 0){
			modelMap.put("msg", Resources.getMessage("登录失败，账号不存在!!"));
			return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
		}
		if(list.get(0).getLogin() == 2){
			modelMap.put("msg", Resources.getMessage("登录失败，账号被禁止登录!!"));
			return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
		}
		if (LoginHelper.login(user.getAccount(),list.get(0).getPassword())) {
			return setSuccessModelMap(modelMap);
		}
		modelMap.put("msg", Resources.getMessage("登录失败，账号或者密码错误!!"));
		return setModelMap(modelMap, HttpCode.LOGIN_FAIL);
	}

	/**
	 * 根据account查询账号
	 * @param account
	 */
	private List<SysUserLogin> getUserByAccount(String account){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enable", 1);
		params.put("account", account);
		Parameter parameter = new Parameter("sysUserLoginService", "queryList").setMap(params);
		logger.info("{} execute sysUserLoginService.queryList start...", parameter.getNo());
		List<SysUserLogin> list = (List<SysUserLogin>) provider.execute(parameter).getList();
		logger.info("{} execute sysUserLoginService.queryList end.", parameter.getNo());
		return list;
	}


	@ApiOperation(value = "用户登出")
	@PostMapping("/logout")
	public Object logout(HttpServletRequest request, ModelMap modelMap) {
		String sessionId = SecurityUtils.getSubject().getSession().getId().toString();
		SecurityUtils.getSubject().logout();//会调用sessionDao的delete清楚缓存
		SysSession ss = new SysSession();
		ss.setSessionId(sessionId);
		Parameter parameter = new Parameter("sysSessionService", "deleteBySessionId").setModel(ss);	
		logger.info("{} execute deleteBySessionId start...", parameter.getNo());
		provider.execute(parameter);
		logger.info("{} execute deleteBySessionId end.", parameter.getNo());//清除sys_session数据
		return setSuccessModelMap(modelMap);
	}
	
//	@ApiOperation(value = "用户注册验证手机短信")
//	@PostMapping("/regin/check_code")
//	public Object reginCheckCode(ModelMap modelMap, @RequestBody SysUser sysUser,HttpServletRequest request) {
//		Assert.mobile(sysUser.getPhone());
//		Assert.isNotBlank(sysUser.getRemark(), "REMARK");
//		String key = Constants.REGIN_CODE + ":" + sysUser.getPhone();
//		Boolean flag = CheckCodeUtil.validateMessageCheckCode(sysUser.getRemark(),key);
//		if(flag == null){
//			modelMap.put("msg", "短信验证码已经过期，请重新获取！");
//			return setModelMap(modelMap, HttpCode.GONE);
//		}else{
//			if(flag){
//				//将此人手机号保存到session，再注册的时候去验证，只能注册的账号是这个手机号
//				WebUtil.setSession(Constants.REGIN_CODE,sysUser.getPhone());
//				//查询sys_unit返回成功注册过的租户
//				List<UserBelongedToUnit> returnList = new ArrayList<>();
//
//				Map<String, Object> paramUnit = new HashMap<String, Object>();
//				paramUnit.put("phone", sysUser.getPhone());
//				Parameter parameterUnit = new Parameter("sysUnitService", "queryList").setMap(paramUnit);
//				List<SysUnit> listUnit = (List<SysUnit>) provider.execute(parameterUnit).getList();
//				if(listUnit.size() == 0){
//					return setSuccessModelMap(modelMap,returnList);
//				}
//				paramUnit.clear();
//				paramUnit.put("enable", 1);
//				paramUnit.put("account", sysUser.getPhone());
//				Parameter parameter = new Parameter("sysUserService", "queryList").setMap(paramUnit);
//				logger.info("{} execute sysUserService.queryList  start...", parameter.getNo());
//				List<SysUser> list = (List<SysUser>) provider.execute(parameter).getList();
//				logger.info("{} execute sysUserService.queryList end.", parameter.getNo());
//				for(SysUnit unit:listUnit){
//					UserBelongedToUnit ubtu = new UserBelongedToUnit(unit.getUnitName(),unit.getUnitCode());
//					for (SysUser object : list) {
//						if(unit.getId().longValue() == object.getTenantId().longValue()){
//							ubtu.setUserId(object.getId());
//							break;
//						}
//					}
//					returnList.add(ubtu);
//				}
//				modelMap.put("msg", "验证通过！");
//				return setSuccessModelMap(modelMap,returnList);
//			}else{
//				modelMap.put("msg", "和短信验证码不一致，请重新输入！");
//				return setModelMap(modelMap, HttpCode.CONFLICT);
//			}
//		}
//	}
	
	/**
	 * 
	 * @param modelMap
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "动态密码登录【只要验证码通过，不区分客户端登录】")
	@PostMapping("/login/dynamic/password")
	public Object loginDynamicPassword(ModelMap modelMap, @RequestBody Login user) {
		Assert.mobile(user.getAccount());
		Assert.isNotBlank(user.getCheckCode(), "checkCode");
		String key = new StringBuffer(Constants.WWW_NAMESPACE).append(LoginConstant.DYNAMIC_PASSWORD.toString())
				.append(":").append(user.getAccount()).toString();
		Boolean flag = CheckCodeUtil.validateMessageCheckCode(user.getCheckCode(),key);
		if(flag == null){
			modelMap.put("msg", "短信动态密码已经失效，请返回上一步重新获取密码！");
			return setModelMap(modelMap, HttpCode.GONE);
		}else{
			if(flag){
				/** 登录当前手机号 */
				if(SecurityUtils.getSubject().isAuthenticated()){
					modelMap.put("msg", "已经登陆，请不要重复登陆!!");
					return setModelMap(modelMap, HttpCode.CONFLICT);
				}
				return accountLogin(user,modelMap);
			}else{
				modelMap.put("msg", "和短信密码码不一致，请重新输入！");
				return setModelMap(modelMap, HttpCode.CONFLICT);
			}
		}
	}
	
	// 注册
//	@ApiOperation(value = "用户注册")
//	@PostMapping("/regin")
//	public Object regin(ModelMap modelMap, @RequestBody SysUser sysUser) {
//		Assert.notNull(sysUser.getAccount(), "ACCOUNT");
//		Assert.notNull(sysUser.getPassword(), "PASSWORD");
//		sysUser.setPassword(SecurityUtil.entryptPassword(sysUser.getPassword()));
//		//校验公司
//		Map<String, Object> paramUnit = new HashMap<String, Object>();
//		paramUnit.put("unitName", sysUser.getUnitName());
//		Parameter parameterUnit = new Parameter("sysUnitService", "queryList").setMap(paramUnit);
//		List<SysUnit> listUnit = (List<SysUnit>) provider.execute(parameterUnit).getList();
//		if(listUnit!=null&&listUnit.size()>0){
//			modelMap.put("msg", "注册公司名称已存在！");
//			return setModelMap(modelMap, HttpCode.GONE);
//		}
//		provider.execute(new Parameter("sysUserService", "regin").setModel(sysUser));
//		return setSuccessModelMap(modelMap);
//	}


    /**	此接口必须手机端登陆。 app登陆和web登陆  凭证的区别就是  sessionId不同，	将手机端当前的session拷贝一份，更换成浏览器的sessionId	*/
    @ApiOperation(value = "app扫码登录")
    @PostMapping("/app/qrcode")
    public Object appLoginQrcode(@RequestBody Map<String,Object> param, ModelMap modelMap) {
        Assert.notNull(param.get("sessionId"), "sessionId");
        //获取web将要登陆的session
        Session webSession = getSession(param.get("sessionId").toString());
        if(webSession == null){
            modelMap.put("msg", "二维码已经过期，请重新获取。");
            return setModelMap(modelMap, HttpCode.GONE);
        }
        boolean authenticated = RedisSessionDao.isAuthenticated(webSession);
        if(!authenticated){
            //获取手机登录的session
            SimpleSession appSession =  (SimpleSession)getSession(SecurityUtils.getSubject().getSession().getId().toString());
            SimpleSession newSession = InstanceUtil.to(appSession, SimpleSession.class);
            newSession.setId(webSession.getId());
            newSession.setStartTimestamp(new Date());
            redisSessionDao.update(newSession);
            //挤掉 web端 上一次登陆的
			SysUserLogin user = new SysUserLogin();
            user.setId(getCurrUser());
            realm.saveSession(newSession, user);
        }
        //WebSocket  通知客户端，已经登陆成功,基于redis的订阅发布模式
        CacheUtil.getRedisHelper().sendMessage(PropertiesUtil.getString("ws.topic.channel"), param.get("sessionId").toString());
        return setSuccessModelMap(modelMap);
    }

    /**	获取session	*/
    private Session getSession(String sessionId){
        Session session = null;
        try {
            Object webSessionOb = CacheUtil.getCache().getFinal(sessionId);
            if (webSessionOb != null) {
                session = (Session) CacheUtil.getRedisHelper().deserialize(webSessionOb.toString());
            } else {
                log.debug("获取seesion 为空!,id=[{}]", sessionId);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return session;
    }



	// 没有登录
	@RequestMapping(value = "/unauthorized", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public Object unauthorized(ModelMap modelMap) throws Exception {
		return setModelMap(modelMap, HttpCode.UNAUTHORIZED);
	}
	

	// 没有权限
	@RequestMapping(value = "/forbidden", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public Object forbidden(ModelMap modelMap) {
		return setModelMap(modelMap, HttpCode.FORBIDDEN);
	}
}

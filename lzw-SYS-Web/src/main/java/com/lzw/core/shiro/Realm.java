package com.lzw.core.shiro;

import com.lzw.core.Constants;
import com.lzw.core.base.BaseProvider;
import com.lzw.core.base.Parameter;
import com.lzw.core.support.login.UsernamePasswordTokenExt;
import com.lzw.core.util.CacheUtil;
import com.lzw.core.util.SecurityUtil;
import com.lzw.core.util.WebUtil;
import com.lzw.model.SysSession;
import com.lzw.model.SysUserLogin;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 权限检查类
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:44:45
 */
public class Realm extends AuthorizingRealm {

	private final Logger logger = LogManager.getLogger();

	@Autowired
	@Qualifier("sysProvider")
	protected BaseProvider provider;

	// 权限
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		Long userId = WebUtil.getCurrentUser();
		//if (Constants.ADMINISTRATOR == userType) {
//			/** 添加基于Permission的权限信息 */
//			Parameter permissionParam = new Parameter("sysAuthorizeService", "queryAllPermission");
//			List<String> permissions = (List<String>) provider.execute(permissionParam).getList();
//			for(String permission :permissions){
//				if (StringUtils.isNotBlank(permission)) {
//					info.addStringPermission(permission);
//                    logger.debug("管理员：" + permission);
//                }
//			}
//            /**	添加角色权限 */
//            Parameter roleAllParam = new Parameter("sysRoleService", "queryList").setMap(new HashMap<>());
//            List<SysRole> roleAllList = (List<SysRole>) provider.execute(roleAllParam).getList();
//            for(SysRole role:roleAllList){
//                info.addRole(role.getRoleName());
//            }
//		} else {
//            /** 添加基于Permission的权限信息 */
//            Parameter permissionParam = new Parameter("sysAuthorizeService", "queryPermissionByUserId").setId(userId);
//			List<String> permissions = (List<String>) provider.execute(permissionParam).getList();
//            for(String permission :permissions){
//				if (StringUtils.isNotBlank(permission)) {
//					info.addStringPermission(permission);
//					logger.debug("普通员："+ permission);
//				}
//			}
//            /**	添加角色权限 */
//            Parameter roleByMeParam = new Parameter("sysRoleService", "queryRoleByUserId").setId(userId);
//            List<String> roleByMeList = (List<String>) provider.execute(roleByMeParam).getList();
//            for(String role :roleByMeList){
//                info.addRole(role);
//            }
//		}
		// 添加用户权限
		info.addStringPermission("user");
		logger.debug("权限管理完成。");
		return info;
	}

	// 登录验证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("enable", 1);
		params.put("account", token.getUsername());
		Parameter parameter = new Parameter("sysUserLoginService", "queryList").setMap(params);
		logger.info("{} execute sysUserLoginService.queryList start...", parameter.getNo());
		List<?> list = provider.execute(parameter).getList();
		logger.info("{} execute sysUserLoginService.queryList end.", parameter.getNo());
		if (list.size() == 1) {
			SysUserLogin user = (SysUserLogin) list.get(0);
			if(user.getLogin() == 2){
				logger.warn("此账号已经被禁止登陆。", token.getUsername());
				return null;
			}
			StringBuilder password = new StringBuilder(100);
			for (int i = 0; i < token.getPassword().length; i++) {
				password.append(token.getPassword()[i]);
			}
			if (!SecurityUtil.validatePassword(password.toString(), user.getPassword())) {
				logger.warn("USER [{}] PASSWORD IS WRONG: {}", token.getUsername(), password);
				return null;
			}
			saveData(user);
			AuthenticationInfo authcInfo =
					new SimpleAuthenticationInfo(user.getId(), token.getPassword(),user.getAccount());
			//第一个参数，会作为权限缓存的键  Constants.CACHE_NAMESPACE + "shiro_redis_cache:" + 第一个参数
			//第二个参数 是真实密码,现在使用传递的密码，因为上面已经验证过了，这里让通过。
			//无密码登录，只需要将AuthenticationInfo和AuthenticationToken设置两个相同的密码即可。
			return authcInfo;
		} else {
			logger.warn("No user: {}", token.getUsername());
			return null;
		}
	}
	
	/** 保存数据	*/
	private void saveData(SysUserLogin user) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute(Constants.CURRENT_USER, user.getId());
		saveSession(session,user);
	}
	
	/** 保存session */
	/**
	 * @param session	登陆人的session
	 * @param user		登陆人sysUser
	 */
	public void saveSession(Session session,SysUserLogin user) {
		SysSession record = new SysSession();
		record.setAccount(user.getId());
		Parameter parameter = new Parameter("sysSessionService", "querySessionIdByAccount").setModel(record);
		logger.info("{} execute querySessionIdByAccount start...", parameter.getNo());
		List<?> sessionIds = provider.execute(parameter).getList();
		logger.info("{} execute querySessionIdByAccount end.", parameter.getNo());
		
		String currentSessionId = session.getId().toString();
		if (sessionIds.size() > 0) {
			parameter = new Parameter("sysSessionService", "deleteBySessionId").setModel(record);
			logger.info("{} execute deleteBySessionId start...", parameter.getNo());
			provider.execute(parameter);//删除数据库中已经登陆的用户
			logger.info("{} execute deleteBySessionId end.", parameter.getNo());
			try {
				for (Object sessionId : sessionIds) {
					if (!currentSessionId.equals(sessionId)) {//清除缓存中已经登陆的用户
						CacheUtil.getCache().del(sessionId.toString());
					}
				}
			} catch (Exception e) {
				logger.error(Constants.Exception_Head, e);
			}
		}
		// 保存用户
		record.setSessionId(currentSessionId);
		String host = (String) session.getAttribute("HOST");
		record.setIp(StringUtils.isBlank(host) ? session.getHost() : host);
		record.setStartTime(session.getStartTimestamp());
		parameter = new Parameter("sysSessionService", "update").setModel(record);
		logger.info("{} execute sysSessionService.update start...", parameter.getNo());
		provider.execute(parameter);
		logger.info("{} execute sysSessionService.update end.", parameter.getNo());
	}
	
}

package com.lzw.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lzw.core.base.BaseModel;
import java.io.Serializable;


/**
 * <p>
 * 用户登录表
 * </p>
 *
 * @author liuxd
 * @since 2019-01-04
 */
@TableName("sys_user_login")
@SuppressWarnings("serial")
public class SysUserLogin extends BaseModel {

    /**
     * 登陆帐户
     */
	@TableField("account_")
	private String account;
    /**
     * 密码
     */
	@TableField("password_")
	private String password;
    /**
     * 1 允许登陆，2 禁止登陆
     */
	@TableField("login_")
	private Integer login;


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLogin() {
		return login;
	}

	public void setLogin(Integer login) {
		this.login = login;
	}

}
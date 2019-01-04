package com.lzw.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lzw.core.base.BaseModel;
import java.io.Serializable;


/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author liuxd
 * @since 2019-01-04
 */
@TableName("sys_user_inf")
@SuppressWarnings("serial")
public class SysUserInf extends BaseModel {

    /**
     * 真实名称
     */
	@TableField("user_name")
	private String userName;
    /**
     * 手机号码
     */
	@TableField("phone_number")
	private String phoneNumber;
    /**
     * 头像
     */
	@TableField("icon_")
	private String icon;
    /**
     * 性别：0->未知；1->男；2->女
     */
	@TableField("gender_")
	private Integer gender;
    /**
     * 用户积分
     */
	@TableField("user_point")
	private Integer userPoint;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getUserPoint() {
		return userPoint;
	}

	public void setUserPoint(Integer userPoint) {
		this.userPoint = userPoint;
	}

}
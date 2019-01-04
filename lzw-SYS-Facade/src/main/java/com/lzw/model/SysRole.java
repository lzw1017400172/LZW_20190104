package com.lzw.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lzw.core.base.BaseModel;

@TableName("sys_role")
@SuppressWarnings("serial")
public class SysRole extends BaseModel {
	
	@TableField("role_name")
	private String roleName;

	@TableField("role_type")
	private Integer roleType;

	@TableField("sort_no")
	private Integer sortNo;



	/**
	 * @return the value of sys_role.role_name
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName
	 *            the value for sys_role.role_name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName == null ? null : roleName.trim();
	}

	/**
	 * @return the value of sys_role.role_type
	 */
	public Integer getRoleType() {
		return roleType;
	}

	/**
	 * @param roleType
	 *            the value for sys_role.role_type
	 */
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}
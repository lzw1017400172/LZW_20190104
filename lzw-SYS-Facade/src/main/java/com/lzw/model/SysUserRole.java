package com.lzw.model;

import com.lzw.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_user_role")
@SuppressWarnings("serial")
public class SysUserRole extends BaseModel {

	@TableField("user_id")
	private Long userId;

	@TableField("role_id")
	private Long roleId;

	public SysUserRole() {
	}

	public SysUserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	/**
	 * @return the value of sys_user_role.user_id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the value for sys_user_role.user_id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the value of sys_user_role.role_id
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the value for sys_user_role.role_id
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", userId=").append(userId);
		sb.append(", roleId=").append(roleId);
		sb.append("]");
		return sb.toString();
	}

	/**
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		SysUserRole other = (SysUserRole) that;
		return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
		return result;
	}
}

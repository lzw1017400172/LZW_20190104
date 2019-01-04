package com.lzw.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lzw.core.base.BaseModel;

@TableName("sys_user_menu")
@SuppressWarnings("serial")
public class SysUserMenu extends BaseModel {

	@TableField("user_id")
	private Long userId;

	@TableField("menu_id")
	private Long menuId;

	@TableField("permission_")
	private String permission;

	/**
	 * @return the value of sys_user_menu.user_id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the value for sys_user_menu.user_id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the value of sys_user_menu.menu_id
	 */
	public Long getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            the value for sys_user_menu.menu_id
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the value of sys_user_menu.permission_
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the value for sys_user_menu.permission_
	 */
	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
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
		sb.append(", menuId=").append(menuId);
		sb.append(", permission=").append(permission);
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
		SysUserMenu other = (SysUserMenu) that;
		return (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
				&& (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
				&& (this.getPermission() == null ? other.getPermission() == null
						: this.getPermission().equals(other.getPermission()));
	}

	/**
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
		result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
		result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
		return result;
	}
}
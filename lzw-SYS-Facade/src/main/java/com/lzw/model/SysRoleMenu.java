package com.lzw.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lzw.core.base.BaseModel;

@TableName("sys_role_menu")
@SuppressWarnings("serial")
public class SysRoleMenu extends BaseModel {

	@TableField("role_id")
	private Long roleId;

	@TableField("menu_id")
	private Long menuId;

	@TableField("permission_")
	private String permission;


	/**
	 * @return the value of sys_role_menu.role_id
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId
	 *            the value for sys_role_menu.role_id
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the value of sys_role_menu.menu_id
	 */
	public Long getMenuId() {
		return menuId;
	}

	/**
	 * @param menuId
	 *            the value for sys_role_menu.menu_id
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the value of sys_role_menu.permission_
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the value for sys_role_menu.permission_
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
		sb.append(", roleId=").append(roleId);
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
		SysRoleMenu other = (SysRoleMenu) that;
		return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
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
		result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
		result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
		result = prime * result + ((getPermission() == null) ? 0 : getPermission().hashCode());
		return result;
	}
}

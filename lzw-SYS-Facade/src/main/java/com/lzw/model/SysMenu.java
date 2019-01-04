package com.lzw.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lzw.core.base.BaseModel;

@TableName("sys_menu")
@SuppressWarnings("serial")
public class SysMenu extends BaseModel {

	@TableField("menu_name")
	private String menuName;

	@TableField("menu_type")
	private Integer menuType;

	@TableField("parent_id")
	private Long parentId;

	@TableField("iconcls_")
	private String iconcls;

	@TableField("request_")
	private String request;

	@TableField("expand_")
	private Integer expand;

	@TableField("sort_no")
	private Integer sortNo;

	@TableField("leaf_")
	private Integer leaf;

	@TableField("permission_")
	private String permission;

    @TableField(exist = false)
	private String typeName;

    @TableField(exist = false)
    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
	 * @return the value of sys_menu.menu_name
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * @param menuName
	 *            the value for sys_menu.menu_name
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	/**
	 * @return the value of sys_menu.menu_type
	 */
	public Integer getMenuType() {
		return menuType;
	}

	/**
	 * @param menuType
	 *            the value for sys_menu.menu_type
	 */
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	/**
	 * @return the value of sys_menu.parent_id
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the value for sys_menu.parent_id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the value of sys_menu.iconcls_
	 */
	public String getIconcls() {
		return iconcls;
	}

	/**
	 * @param iconcls
	 *            the value for sys_menu.iconcls_
	 */
	public void setIconcls(String iconcls) {
		this.iconcls = iconcls == null ? null : iconcls.trim();
	}

	/**
	 * @return the value of sys_menu.request_
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request
	 *            the value for sys_menu.request_
	 */
	public void setRequest(String request) {
		this.request = request == null ? null : request.trim();
	}

	/**
	 * @return the value of sys_menu.expand_
	 */
	public Integer getExpand() {
		return expand;
	}

	/**
	 * @param expand
	 *            the value for sys_menu.expand_
	 */
	public void setExpand(Integer expand) {
		this.expand = expand;
	}

	/**
	 * @return the value of sys_menu.sort_no
	 */
	public Integer getSortNo() {
		return sortNo;
	}

	/**
	 * @param sortNo
	 *            the value for sys_menu.sort_no
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return the value of sys_menu.permission_
	 */
	public String getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the value for sys_menu.permission_
	 */
	public void setPermission(String permission) {
		this.permission = permission == null ? null : permission.trim();
	}

    public Integer getLeaf() {
        return leaf;
    }

    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }
}
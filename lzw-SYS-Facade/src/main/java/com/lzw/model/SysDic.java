package com.lzw.model;

import com.lzw.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_dic")
@SuppressWarnings("serial")
public class SysDic extends BaseModel {
	@TableField("type_")
	private String type;
	@TableField("code_")
	private String code;
	private String codeText;
	private Integer sortNo;
	@TableField("editable_")
	private Boolean editable;
    @TableField("parent_type")
    private String parentType;
    @TableField("parent_code")
    private String parentCode;
    
    /**
     * 租户id
     */
	@TableField("tenant_id")
	private Long tenantId;
	
	/**
	 * @return the tenantId
	 */
	public Long getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId
	 *            the tenantId to set
	 */
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the value of sys_dic.code_
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the value for sys_dic.code_
	 */
	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	/**
	 * @return the value of sys_dic.code_text
	 */
	public String getCodeText() {
		return codeText;
	}

	/**
	 * @param codeText
	 *            the value for sys_dic.code_text
	 */
	public void setCodeText(String codeText) {
		this.codeText = codeText == null ? null : codeText.trim();
	}

	/**
	 * @return the value of sys_dic.sort_no
	 */
	public Integer getSortNo() {
		return sortNo;
	}

	/**
	 * @param sortNo
	 *            the value for sys_dic.sort_no
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * @return the value of sys_dic.editable_
	 */
	public Boolean getEditable() {
		return editable;
	}

	/**
	 * @param editable
	 *            the value for sys_dic.editable_
	 */
	public void setEditable(Boolean editable) {
		this.editable = editable;
	}

	public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

}

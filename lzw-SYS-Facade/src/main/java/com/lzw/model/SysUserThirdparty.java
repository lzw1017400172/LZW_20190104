package com.lzw.model;

import com.lzw.core.base.BaseModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("sys_user_thirdparty")
@SuppressWarnings("serial")
public class SysUserThirdparty extends BaseModel {
    private Long userId;

    @TableField("provider_")
    private String provider;

    private String openId;

    /**
     * @return the value of sys_user_thirdparty.user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the value for sys_user_thirdparty.user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the value of sys_user_thirdparty.provider_
     */
    public String getProvider() {
        return provider;
    }

    /**
     * @param provider the value for sys_user_thirdparty.provider_
     */
    public void setProvider(String provider) {
        this.provider = provider == null ? null : provider.trim();
    }

    /**
     * @return the value of sys_user_thirdparty.open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId the value for sys_user_thirdparty.open_id
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
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
        sb.append(", provider=").append(provider);
        sb.append(", openId=").append(openId);
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
        SysUserThirdparty other = (SysUserThirdparty) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getProvider() == null ? other.getProvider() == null : this.getProvider().equals(other.getProvider()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()));
    }

    /**
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getProvider() == null) ? 0 : getProvider().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        return result;
    }
}
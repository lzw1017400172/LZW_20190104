package com.lzw.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.lzw.core.base.BaseModel;
import java.io.Serializable;


/**
 * <p>
 * 用户地址表
 * </p>
 *
 * @author liuxd
 * @since 2019-01-04
 */
@TableName("sys_user_addr")
@SuppressWarnings("serial")
public class SysUserAddr extends BaseModel {

	@TableField("user_id")
	private Long userId;
    /**
     * 收货人名称
     */
	@TableField("name_")
	private String name;
    /**
     * 手机
     */
	@TableField("phone_number")
	private String phoneNumber;
    /**
     * 是否为默认 , 1默认，2不是
     */
	@TableField("default_status")
	private Integer defaultStatus;
    /**
     * 邮政编码
     */
	@TableField("post_code")
	private String postCode;
    /**
     * 省份/直辖市
     */
	@TableField("province_")
	private String province;
    /**
     * 城市
     */
	@TableField("city_")
	private String city;
    /**
     * 区
     */
	@TableField("region_")
	private String region;
    /**
     * 详细地址(街道)
     */
	@TableField("detail_address")
	private String detailAddress;


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(Integer defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

}
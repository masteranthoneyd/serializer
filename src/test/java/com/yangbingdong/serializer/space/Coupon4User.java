/*
 * file comment: Coupon4User.java
 * Copyright(C) All rights reserved. 
 */
package com.yangbingdong.serializer.space;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Coupon4User implements Serializable {
    /**
     * field comment: 用户优惠券id(主键)
     */
    private Long id;

    private Boolean idGreater;

    /**
     * field comment: 用户uid
     */
    private Integer uid;

    /**
     * field comment: 优惠券活动id
     */
    private Long actId;

    /**
     * field comment: 优惠券关联的优惠券配置id_来源于ibalife_busi.coup_config.id
     */
    private Long coupConfId;

    /**
     * field comment: 0 指定用户状态，用户是看不到的 1 未使用 2 冻结 3 已使用 4 已过期
     */
    private Byte status;

    private transient List<Byte> statusList;

    /**
     * field comment: 优惠券使用时间状态
     */
    private String stsTime;

    /**
     * field comment: 优惠券使用时间状态
     */
    private Boolean stsTimeEqual;

    /**
     * field comment: 创建时间
     */
    private Date createTime;

    private Date giveExpiredTime;

    private Boolean clearGiveExpiredTime;

    private Integer giveFlag;

    private static final long serialVersionUID = 1L;

	private String remark;


	public Long getId() {
		return id;
	}

	public Coupon4User setId(Long id) {
		this.id = id;
		return this;
	}

	public Boolean getIdGreater() {
		return idGreater;
	}

	public Coupon4User setIdGreater(Boolean idGreater) {
		this.idGreater = idGreater;
		return this;
	}

	public Integer getUid() {
		return uid;
	}

	public Coupon4User setUid(Integer uid) {
		this.uid = uid;
		return this;
	}

	public Long getActId() {
		return actId;
	}

	public Coupon4User setActId(Long actId) {
		this.actId = actId;
		return this;
	}

	public Long getCoupConfId() {
		return coupConfId;
	}

	public Coupon4User setCoupConfId(Long coupConfId) {
		this.coupConfId = coupConfId;
		return this;
	}

	public Byte getStatus() {
		return status;
	}

	public Coupon4User setStatus(Byte status) {
		this.status = status;
		return this;
	}

	public List<Byte> getStatusList() {
		return statusList;
	}

	public Coupon4User setStatusList(List<Byte> statusList) {
		this.statusList = statusList;
		return this;
	}

	public String getStsTime() {
		return stsTime;
	}

	public Coupon4User setStsTime(String stsTime) {
		this.stsTime = stsTime;
		return this;
	}

	public Boolean getStsTimeEqual() {
		return stsTimeEqual;
	}

	public Coupon4User setStsTimeEqual(Boolean stsTimeEqual) {
		this.stsTimeEqual = stsTimeEqual;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Coupon4User setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getGiveExpiredTime() {
		return giveExpiredTime;
	}

	public Coupon4User setGiveExpiredTime(Date giveExpiredTime) {
		this.giveExpiredTime = giveExpiredTime;
		return this;
	}

	public Boolean getClearGiveExpiredTime() {
		return clearGiveExpiredTime;
	}

	public Coupon4User setClearGiveExpiredTime(Boolean clearGiveExpiredTime) {
		this.clearGiveExpiredTime = clearGiveExpiredTime;
		return this;
	}

	public Integer getGiveFlag() {
		return giveFlag;
	}

	public Coupon4User setGiveFlag(Integer giveFlag) {
		this.giveFlag = giveFlag;
		return this;
	}

	public String getRemark() {
		return remark;
	}

	public Coupon4User setRemark(String remark) {
		this.remark = remark;
		return this;
	}
}

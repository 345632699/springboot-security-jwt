package com.hotel.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PackageOrder implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.out_trade_no
     *
     * @mbggenerated
     */
    private String outTradeNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.package_id
     *
     * @mbggenerated
     */
    private Integer packageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.package_name
     *
     * @mbggenerated
     */
    private String packageName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.open_id
     *
     * @mbggenerated
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.user_id
     *
     * @mbggenerated
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.price
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.real_price
     *
     * @mbggenerated
     */
    private BigDecimal realPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.start_time
     *
     * @mbggenerated
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.end_time
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.union_id
     *
     * @mbggenerated
     */
    private String unionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.user_name
     *
     * @mbggenerated
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.mobile
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.pay_status
     *
     * @mbggenerated
     */
    private Integer payStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.memo
     *
     * @mbggenerated
     */
    private String memo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.created_at
     *
     * @mbggenerated
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column package_order.updated_at
     *
     * @mbggenerated
     */
    private Date updatedAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table package_order
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.id
     *
     * @return the value of package_order.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.id
     *
     * @param id the value for package_order.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.out_trade_no
     *
     * @return the value of package_order.out_trade_no
     *
     * @mbggenerated
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.out_trade_no
     *
     * @param outTradeNo the value for package_order.out_trade_no
     *
     * @mbggenerated
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.package_id
     *
     * @return the value of package_order.package_id
     *
     * @mbggenerated
     */
    public Integer getPackageId() {
        return packageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.package_id
     *
     * @param packageId the value for package_order.package_id
     *
     * @mbggenerated
     */
    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.package_name
     *
     * @return the value of package_order.package_name
     *
     * @mbggenerated
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.package_name
     *
     * @param packageName the value for package_order.package_name
     *
     * @mbggenerated
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.open_id
     *
     * @return the value of package_order.open_id
     *
     * @mbggenerated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.open_id
     *
     * @param openId the value for package_order.open_id
     *
     * @mbggenerated
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.user_id
     *
     * @return the value of package_order.user_id
     *
     * @mbggenerated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.user_id
     *
     * @param userId the value for package_order.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.price
     *
     * @return the value of package_order.price
     *
     * @mbggenerated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.price
     *
     * @param price the value for package_order.price
     *
     * @mbggenerated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.real_price
     *
     * @return the value of package_order.real_price
     *
     * @mbggenerated
     */
    public BigDecimal getRealPrice() {
        return realPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.real_price
     *
     * @param realPrice the value for package_order.real_price
     *
     * @mbggenerated
     */
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.start_time
     *
     * @return the value of package_order.start_time
     *
     * @mbggenerated
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.start_time
     *
     * @param startTime the value for package_order.start_time
     *
     * @mbggenerated
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.end_time
     *
     * @return the value of package_order.end_time
     *
     * @mbggenerated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.end_time
     *
     * @param endTime the value for package_order.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.union_id
     *
     * @return the value of package_order.union_id
     *
     * @mbggenerated
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.union_id
     *
     * @param unionId the value for package_order.union_id
     *
     * @mbggenerated
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.user_name
     *
     * @return the value of package_order.user_name
     *
     * @mbggenerated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.user_name
     *
     * @param userName the value for package_order.user_name
     *
     * @mbggenerated
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.mobile
     *
     * @return the value of package_order.mobile
     *
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.mobile
     *
     * @param mobile the value for package_order.mobile
     *
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.status
     *
     * @return the value of package_order.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.status
     *
     * @param status the value for package_order.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.pay_status
     *
     * @return the value of package_order.pay_status
     *
     * @mbggenerated
     */
    public Integer getPayStatus() {
        return payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.pay_status
     *
     * @param payStatus the value for package_order.pay_status
     *
     * @mbggenerated
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.memo
     *
     * @return the value of package_order.memo
     *
     * @mbggenerated
     */
    public String getMemo() {
        return memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.memo
     *
     * @param memo the value for package_order.memo
     *
     * @mbggenerated
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.created_at
     *
     * @return the value of package_order.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.created_at
     *
     * @param createdAt the value for package_order.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column package_order.updated_at
     *
     * @return the value of package_order.updated_at
     *
     * @mbggenerated
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column package_order.updated_at
     *
     * @param updatedAt the value for package_order.updated_at
     *
     * @mbggenerated
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table package_order
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", outTradeNo=").append(outTradeNo);
        sb.append(", packageId=").append(packageId);
        sb.append(", packageName=").append(packageName);
        sb.append(", openId=").append(openId);
        sb.append(", userId=").append(userId);
        sb.append(", price=").append(price);
        sb.append(", realPrice=").append(realPrice);
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", unionId=").append(unionId);
        sb.append(", userName=").append(userName);
        sb.append(", mobile=").append(mobile);
        sb.append(", status=").append(status);
        sb.append(", payStatus=").append(payStatus);
        sb.append(", memo=").append(memo);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
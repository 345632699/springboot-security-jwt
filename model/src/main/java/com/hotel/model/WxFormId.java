package com.hotel.model;

import java.io.Serializable;
import java.util.Date;

public class WxFormId implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_form_id.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_form_id.open_id
     *
     * @mbggenerated
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_form_id.form_id
     *
     * @mbggenerated
     */
    private String formId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_form_id.is_used
     *
     * @mbggenerated
     */
    private Integer isUsed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_form_id.created_at
     *
     * @mbggenerated
     */
    private Date createdAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_form_id.expired_at
     *
     * @mbggenerated
     */
    private Date expiredAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table wx_form_id
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_form_id.id
     *
     * @return the value of wx_form_id.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_form_id.id
     *
     * @param id the value for wx_form_id.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_form_id.open_id
     *
     * @return the value of wx_form_id.open_id
     *
     * @mbggenerated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_form_id.open_id
     *
     * @param openId the value for wx_form_id.open_id
     *
     * @mbggenerated
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_form_id.form_id
     *
     * @return the value of wx_form_id.form_id
     *
     * @mbggenerated
     */
    public String getFormId() {
        return formId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_form_id.form_id
     *
     * @param formId the value for wx_form_id.form_id
     *
     * @mbggenerated
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_form_id.is_used
     *
     * @return the value of wx_form_id.is_used
     *
     * @mbggenerated
     */
    public Integer getIsUsed() {
        return isUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_form_id.is_used
     *
     * @param isUsed the value for wx_form_id.is_used
     *
     * @mbggenerated
     */
    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_form_id.created_at
     *
     * @return the value of wx_form_id.created_at
     *
     * @mbggenerated
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_form_id.created_at
     *
     * @param createdAt the value for wx_form_id.created_at
     *
     * @mbggenerated
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_form_id.expired_at
     *
     * @return the value of wx_form_id.expired_at
     *
     * @mbggenerated
     */
    public Date getExpiredAt() {
        return expiredAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_form_id.expired_at
     *
     * @param expiredAt the value for wx_form_id.expired_at
     *
     * @mbggenerated
     */
    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_form_id
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
        sb.append(", openId=").append(openId);
        sb.append(", formId=").append(formId);
        sb.append(", isUsed=").append(isUsed);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", expiredAt=").append(expiredAt);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
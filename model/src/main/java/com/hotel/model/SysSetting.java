package com.hotel.model;

import java.io.Serializable;

public class SysSetting implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_setting.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_setting.company_name
     *
     * @mbggenerated
     */
    private String companyName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_setting.logo
     *
     * @mbggenerated
     */
    private String logo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_setting.banner
     *
     * @mbggenerated
     */
    private String banner;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_setting.tel
     *
     * @mbggenerated
     */
    private String tel;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_setting.notify_mobile
     *
     * @mbggenerated
     */
    private String notifyMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_setting
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_setting.id
     *
     * @return the value of sys_setting.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_setting.id
     *
     * @param id the value for sys_setting.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_setting.company_name
     *
     * @return the value of sys_setting.company_name
     *
     * @mbggenerated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_setting.company_name
     *
     * @param companyName the value for sys_setting.company_name
     *
     * @mbggenerated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_setting.logo
     *
     * @return the value of sys_setting.logo
     *
     * @mbggenerated
     */
    public String getLogo() {
        return logo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_setting.logo
     *
     * @param logo the value for sys_setting.logo
     *
     * @mbggenerated
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_setting.banner
     *
     * @return the value of sys_setting.banner
     *
     * @mbggenerated
     */
    public String getBanner() {
        return banner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_setting.banner
     *
     * @param banner the value for sys_setting.banner
     *
     * @mbggenerated
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_setting.tel
     *
     * @return the value of sys_setting.tel
     *
     * @mbggenerated
     */
    public String getTel() {
        return tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_setting.tel
     *
     * @param tel the value for sys_setting.tel
     *
     * @mbggenerated
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_setting.notify_mobile
     *
     * @return the value of sys_setting.notify_mobile
     *
     * @mbggenerated
     */
    public String getNotifyMobile() {
        return notifyMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_setting.notify_mobile
     *
     * @param notifyMobile the value for sys_setting.notify_mobile
     *
     * @mbggenerated
     */
    public void setNotifyMobile(String notifyMobile) {
        this.notifyMobile = notifyMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_setting
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
        sb.append(", companyName=").append(companyName);
        sb.append(", logo=").append(logo);
        sb.append(", banner=").append(banner);
        sb.append(", tel=").append(tel);
        sb.append(", notifyMobile=").append(notifyMobile);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.youda.model;

import javax.persistence.*;

/**
 * @Author Chencongye
 * @Date 2017/12/7 19:45
 * @Version 1.0.0
 * @Instructions 定义国家代码的实体类
 */

@Entity
@Table(name = "tb_countrycode",catalog = "db_ydgame")
public class CountryCode {

    /*声明国家代码主键Id*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "countryCodeId")
    private long countryCodeId;

    /*声明国家代码*/
    @Column(name = "countryCode")
    private String countryCode;

    /*声明国家名称*/
    @Column(name = "countryName")
    private String countryName;

    /*声明国家时区*/
    @Column(name = "timeZone")
    private String timeZone;

    /**
     * 实现国家代码主键的Id的get方法
     * @return
     */
    public long getCountryCodeId() {
        return countryCodeId;
    }

    /**
     * 实现国家代码主键的set方法
     * @param countryCodeId
     */
    public void setCountryCodeId(long countryCodeId) {
        this.countryCodeId = countryCodeId;
    }

    /**
     * 实现国建代码的get方法
     * @return
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 实现国家代码的set方法
     * @param countryCode
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 实现国家名称的get方法
     * @return
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 实现国家名称的set方法
     * @param
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 实现国家时区的get方法
     * @return
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * 实现国家时区的set方法
     * @param timeZone
     */
    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * 实现默认的无参构造函数
     */
    public CountryCode() {
    }

    /**
     * 实现带有参数的构造函数
     * @param countryCodeId
     * @param countryCode
     * @param countryName
     * @param timeZone
     */
    public CountryCode(long countryCodeId, String countryCode, String countryName, String timeZone) {
        this.countryCodeId = countryCodeId;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.timeZone = timeZone;
    }
}

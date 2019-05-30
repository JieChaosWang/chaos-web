package com.my.test.pojo;

import java.sql.Date;
import java.util.List;

/**
 * 统计
 * @author wcj
 */
public class StatisticsInfo {
    // 统计主键
    private int statisticsSeq;
    //管理员id
    private Long adminId;
    //管理员名称
    private String adminName;
    // 出单数
    private int volumeNum;
    // 接线数
    private int connectionNum;
    // 微信添加数
    private int wechatAddNum;
    // 有效电话数
    private int effectiveCallNum;
    // 微信添加总数
    private int wechatAddAllNum;
    // 当日签收数
    private int todaySignedNum;
    // 创建时间
    private Date createTime;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public int getStatisticsSeq() {
        return statisticsSeq;
    }

    public void setStatisticsSeq(int statisticsSeq) {
        this.statisticsSeq = statisticsSeq;
    }

    public int getVolumeNum() {
        return volumeNum;
    }

    public void setVolumeNum(int volumeNum) {
        this.volumeNum = volumeNum;
    }

    public int getConnectionNum() {
        return connectionNum;
    }

    public void setConnectionNum(int connectionNum) {
        this.connectionNum = connectionNum;
    }

    public int getWechatAddNum() {
        return wechatAddNum;
    }

    public void setWechatAddNum(int wechatAddNum) {
        this.wechatAddNum = wechatAddNum;
    }

    public int getEffectiveCallNum() {
        return effectiveCallNum;
    }

    public void setEffectiveCallNum(int effectiveCallNum) {
        this.effectiveCallNum = effectiveCallNum;
    }

    public int getWechatAddAllNum() {
        return wechatAddAllNum;
    }

    public void setWechatAddAllNum(int wechatAddAllNum) {
        this.wechatAddAllNum = wechatAddAllNum;
    }

    public int getTodaySignedNum() {
        return todaySignedNum;
    }

    public void setTodaySignedNum(int todaySignedNum) {
        this.todaySignedNum = todaySignedNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

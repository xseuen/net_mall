package com.team45.net_mall.common.domain;

import java.util.Date;

public class Wallet {
    private Integer id;

    private Integer memberId;

    private String menberNickname;

    private Double balance;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMenberNickname() {
        return menberNickname;
    }

    public void setMenberNickname(String menberNickname) {
        this.menberNickname = menberNickname == null ? null : menberNickname.trim();
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
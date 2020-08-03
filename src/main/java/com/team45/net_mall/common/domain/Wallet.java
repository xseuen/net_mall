package com.team45.net_mall.common.domain;

public class Wallet {
    private Integer id;

    private Integer memberId;

    private String menberNickname;

    private Double balance;

    private Long level;

    private String updateTime;

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

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}
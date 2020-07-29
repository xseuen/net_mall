package com.team45.net_mall.common.domain;

import com.team45.net_mall.common.domain.extend.CategoryExtend;

public class Category extends CategoryExtend {
    private String cid;

    private String cname;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid == null ? null : cid.trim();
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}
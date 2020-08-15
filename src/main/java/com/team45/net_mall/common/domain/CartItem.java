package com.team45.net_mall.common.domain;

public class CartItem {
    private Integer id;

    private Integer cartId;

    private Integer proId;

    private String proName;

    private String proPic;

    private Double proPrice;

    private Integer num;

    private Long total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic == null ? null : proPic.trim();
    }

    public Double getProPrice() {
        return proPrice;
    }

    public void setProPrice(Double proPrice) {
        this.proPrice = proPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
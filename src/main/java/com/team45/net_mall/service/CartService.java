package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Cart;
import com.team45.net_mall.common.domain.CartItem;
import com.team45.net_mall.common.domain.Member;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CartService {

    public List queryCartData(Member member);

    public int delCart(int id);

    public int addCart(int productId, HttpSession session);

    public int editCart(CartItem cartItem);

}

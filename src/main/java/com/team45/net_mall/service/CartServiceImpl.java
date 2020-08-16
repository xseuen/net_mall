package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.*;
import com.team45.net_mall.mapper.CartItemMapper;
import com.team45.net_mall.mapper.CartMapper;
import com.team45.net_mall.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {



    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductMapper productMapper;

    //时间格式化
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String createTime = sdf.format(d);

    /**
     * 购物车列表
     * @param member
     * @return
     */
    @Override
    public List queryCartData(Member member) {
        CartExample example = new CartExample();
        //查询会员的购物车记录
        example.createCriteria().andMemberIdEqualTo(member.getId());
        List<Cart> list = cartMapper.selectByExample(example);
        if(list.isEmpty()){
            return null;
        }
        CartItemExample cartItemExample = new CartItemExample();
        cartItemExample.createCriteria().andCartIdEqualTo(list.get(0).getId());
        List<CartItem> list1 =cartItemMapper.selectByExample(cartItemExample);
        return list1;
    }

    @Override
    public int delCart(int id) {
        return cartItemMapper.deleteByPrimaryKey(id);
    }

    /**
     * 购物车操作
     * @param productId
     * @param session
     * @return
     */
    @Override
    public int addCart(int productId, HttpSession session) {
        int result = 0;
        Cart cart = new Cart();
        //查询用户是否有未结算的购物车
        Member member = (Member) session.getAttribute("loginUser");
        CartExample cartExample = new CartExample();
        cartExample.createCriteria().andMemberIdEqualTo(member.getId());
        List<Cart> carts = cartMapper.selectByExample(cartExample);
        //没有就新建购物车
        if(carts.isEmpty()){
            cart.setMemberId(member.getId());
            cart.setMemberNickname(member.getNickName());
            cart.setCreateTime(createTime);
            cartMapper.insert(cart);
            addCart(productId,session);
        }else {
            //先查询购物车是否有此商品
            CartItem cartItem = new CartItem();
            CartItemExample itemExample= new CartItemExample();
            itemExample.createCriteria().andProIdEqualTo(productId).andCartIdEqualTo(carts.get(0).getId());
            List<CartItem>list = cartItemMapper.selectByExample(itemExample);
            //有就更新数量,无则新增
            if(list!= null & list.size()>0){
                cartItem = list.get(0);
                cartItem.setNum(cartItem.getNum()+1);//数量+1
                cartItem.setTotal(cartItem.getTotal().longValue()+cartItem.getProPrice().longValue());
                result = cartItemMapper.updateByPrimaryKeySelective(cartItem);
            }else{
                Product product = productMapper.selectByPrimaryKey(productId);
                cartItem.setCartId(carts.get(0).getId());
                cartItem.setProId(productId);
                cartItem.setProName(product.getName());
                cartItem.setProPrice(product.getPrice());
                cartItem.setProPic(product.getPic());
                cartItem.setNum(1);
                cartItem.setTotal(product.getPrice().longValue());
                result = cartItemMapper.insert(cartItem);
            }
        }

        return  result;
    }

    @Override
    public int editCart(CartItem cartItem) {
        CartItem cartItem1 = cartItemMapper.selectByPrimaryKey(cartItem.getId());
        cartItem.setTotal(cartItem1.getProPrice().longValue()*cartItem.getNum());
        return cartItemMapper.updateByPrimaryKeySelective(cartItem);
    }
}

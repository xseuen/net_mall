package com.team45.net_mall.service;

import com.team45.net_mall.common.domain.Cart;
import com.team45.net_mall.common.domain.CartExample;
import com.team45.net_mall.common.domain.Member;
import com.team45.net_mall.common.domain.Product;
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
    private ProductMapper productMapper;

    @Override
    public List queryCartData(Member member) {
        CartExample example = new CartExample();
        //查询会员的购物车记录
        example.createCriteria().andMemberIdEqualTo(member.getId()).andDeleteStatusNotEqualTo(1);
        List list = cartMapper.selectByExampleWithBLOBs(example);
        return list;
    }

    @Override
    public int delCart(int id) {
        Cart cart = cartMapper.selectByPrimaryKey(id);
        cart.setDeleteStatus(1);//设置状态为已删除
        return cartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public int addCart(int productId, HttpSession session) {
        int result = 0;
        Cart cart = new Cart();
        //先查询购物车是否有此商品
        CartExample example = new CartExample();
        example.createCriteria().andProductIdEqualTo(productId);
        List<Cart>list = cartMapper.selectByExample(example);
        //有就更新数量,无则新增
        if(list!= null & list.size()>0){
            cart = list.get(0);
            cart.setCount(cart.getCount()+1);//数量+1
            result = cartMapper.updateByPrimaryKeySelective(cart);
        }else{
            Product product = productMapper.selectByPrimaryKey(productId);
            Member member = (Member) session.getAttribute("loginUser");


            cart.setMemberId(member.getId());
            cart.setMemberNickname(member.getNickName());
            cart.setProductId(productId);
            cart.setProductName(product.getName());
            cart.setProductPrice(product.getPrice());
            cart.setProductPic(product.getPic());
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createTime = sdf.format(d);
            cart.setCreateTime(createTime);

            result =  cartMapper.insertSelective(cart);
        }
        return  result;
    }

    @Override
    public int editCart(Cart cart) {
        return cartMapper.updateByPrimaryKeySelective(cart);
    }
}

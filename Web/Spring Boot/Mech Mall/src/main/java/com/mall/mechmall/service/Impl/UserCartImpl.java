package com.mall.mechmall.service.Impl;

import com.mall.mechmall.domain.Cart;
import com.mall.mechmall.mapper.ProductMapper;
import com.mall.mechmall.mapper.UserCartMapper;
import com.mall.mechmall.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCartImpl implements UserCartService {
    @Autowired
    private UserCartMapper userCartMapper;

    @Autowired
    public UserCartImpl(UserCartMapper userCartMapper) {
        this.userCartMapper = userCartMapper;
    }

    @Override
    public int countcarts(){
        return userCartMapper.countcarts();
    }

    @Override
    public boolean clearCart(){
        return userCartMapper.clearCart();
    }

    @Override
    public boolean deleteCartItem(int id){
        return userCartMapper.deleteCartItem(id);
    }

    @Override
    public List<Cart> allCartItem(int page, int size){
        return userCartMapper.allCartItem((page - 1) * size, size);
    }

    @Override
    public boolean insertCart(Cart cart){
        return userCartMapper.insertCart(cart)>0;
    }

    @Override
    public Cart selectdCartItemById(int id){
        return userCartMapper.selectdCartItemById(id);
    }

    @Override
    public boolean updateCartItem(int id, int quantity){
        return userCartMapper.updateCartItem(id, quantity)>0;
    }
}

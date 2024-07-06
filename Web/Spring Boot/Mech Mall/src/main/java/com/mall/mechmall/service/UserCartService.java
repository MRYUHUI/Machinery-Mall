package com.mall.mechmall.service;

import com.mall.mechmall.domain.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCartService {
    int countcarts();
    boolean clearCart();
    boolean deleteCartItem(int id);
    List<Cart> allCartItem(int page, int size);
    boolean insertCart(Cart cart);
    Cart selectdCartItemById(int id);
    boolean updateCartItem(int id, int quantity);
}

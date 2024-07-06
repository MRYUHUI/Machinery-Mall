package com.mall.mechmall.mapper;

import com.mall.mechmall.domain.Cart;
import com.mall.mechmall.domain.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserCartMapper {
    @Select("SELECT COUNT(*) FROM cart")
    public int countcarts();

    @Delete("DELETE FROM cart")
    public boolean clearCart();

    @Delete("DELETE FROM cart WHERE id = #{id}")
    public boolean deleteCartItem(@Param("id") int id);

    @Select("SELECT * FROM cart LIMIT #{offset}, #{size}")
    public List<Cart> allCartItem(@Param("offset") int offset, @Param("size") int size);

    @Insert("INSERT INTO cart (user_id, product_id, quantity, created, updated) " +
            "VALUES (#{userId}, #{productId}, #{quantity}, NOW(), NOW())")
    public int insertCart(Cart cart);

    @Select("SELECT * FROM cart WHERE id = #{id}")
    public Cart selectdCartItemById(@Param("id") int id);

    @Select("SELECT * FROM cart WHERE id = #{id}")
    public Cart selectdCartItemByName(@Param("id") int id);

    @Update("UPDATE cart SET quantity = #{quantity} WHERE id = #{id}")
    public int updateCartItem(@Param("id") int id, @Param("quantity") int quantity);
}

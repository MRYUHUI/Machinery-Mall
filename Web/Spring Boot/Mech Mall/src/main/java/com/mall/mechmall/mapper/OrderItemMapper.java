package com.mall.mechmall.mapper;

import com.mall.mechmall.domain.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    @Select("SELECT * FROM order_items WHERE uid = #{uid}")
    public List<OrderItem> findAllOrderItemsByUid(@Param("uid") int uid);

    @Select("SELECT COUNT(*) FROM order_items WHERE uid = #{uid}")
    public int countOrderItemsByUid(@Param("uid") int uid);

    @Select("SELECT * FROM order_items WHERE order_id = #{orderId}")
    public List<OrderItem> findOrderItemByOrderId(@Param("orderId") Integer orderId);

    @Insert("INSERT INTO order_items(uid, order_id, goods_id, goods_name, icon_url, price, quantity, total_price, created, updated) " +
            "VALUES (#{uid}, #{orderId}, #{goodsId}, #{goodsName}, #{iconUrl}, #{price}, #{quantity}, #{totalPrice}, NOW(), NOW())")
    public int insertOrderItem(OrderItem orderItem);

    @Delete("DELETE FROM order_items WHERE id = #{id}")
    public int deleteOrderItemById(@Param("id") Integer id);

    @Update({
            "<script>",
            "UPDATE order_items",
            "<set>",
            "<if test='uid != null'>uid = #{uid},</if>",
            "<if test='order_id != null'>order_id = #{orderId},</if>",
            "<if test='goods_id != null'>goods_id = #{goodsId},</if>",
            "<if test='goods_name != null'>goods_name = #{goodsName},</if>",
            "<if test='icon_url != null'>icon_url = #{iconUrl},</if>",
            "<if test='price != null'>price = #{price},</if>",
            "<if test='quantity != null'>quantity = #{quantity},</if>",
            "<if test='total_price != null'>total_price = #{totalPrice},</if>",
            "<if test='updated != null'>updated = #{updated},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    public int updateOrderItem(OrderItem orderItem);

}

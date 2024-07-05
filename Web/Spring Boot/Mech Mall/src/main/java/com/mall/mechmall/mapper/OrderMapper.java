package com.mall.mechmall.mapper;

import com.mall.mechmall.domain.Order;
import com.mall.mechmall.domain.User;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

        import com.mall.mechmall.domain.User;
        import org.apache.ibatis.annotations.*;

        import java.util.List;
        import java.util.Map;

/**
 * @Author: yuhui
 * @Date: 2024-06-25-21:52
 * @Description: 用户持久层
 */
@Mapper
public interface OrderMapper{
    @Select("SELECT * FROM orders WHERE order_no = #{order_no}")
    public Order findOrderByOrderNo(@Param("order_no") String order_no);

    @Select("SELECT COUNT(*) FROM orders WHERE order_no = #{order_no}")
    public int checkOrderByOrderNo(@Param("order_no") String order_no);

    @Select("SELECT COUNT(*) FROM orders WHERE uid = #{uid}")
    public int checkOrderByUid(@Param("uid") Integer uid);
    //thh增加
    @Select("SELECT * FROM orders WHERE uid = #{uid}")
    public List<Order> getOrderByUserId(@Param("uid") Integer uid);

    @Select("SELECT COUNT(*) FROM orders WHERE addr_id = #{addr_id}")
    public int checkOrderByAddrId(@Param("addr_id") Integer addr_id);

    @Insert("INSERT INTO orders (order_no, uid, addr_id, amount, type, freight, status, created, updated) " +
            "VALUES (#{orderNo}, #{uid}, #{addrId}, #{amount}, #{type}, #{freight}, #{status}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertOrder(Order order);

    @Select("SELECT COUNT(*) FROM orders WHERE order_no = #{order_no} AND amount = #{amount}")
    public int checkOrderAmount(@Param("order_no") String order_no, @Param("amount") BigDecimal amount);

    @Update("UPDATE orders SET type = #{type} WHERE order_no = #{order_no}")
    public int updateOrderType(@Param("order_no") String order_no, @Param("type") Integer type);

    @Select("SELECT feight FROM orders WHERE order_no = #{order_no}")
    public Integer findOrderFeight(@Param("order_no") String order_no);

    @Update("UPDATE orders SET status = #{status} WHERE order_no = #{order_no}")
    public int updateOrderStatus(@Param("order_no") String order_no, @Param("status") Integer status);
    //kk
    @Select("SELECT * FROM orders LIMIT #{offset}, #{size}")
    public List<Order> findAllOrders(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM orders")
    public int countOrders();

    @Delete("DELETE FROM orders WHERE id = #{orderId}")
     public  int deleteOrder(@Param("orderId") Integer orderId);


    @Select("SELECT * FROM orders WHERE id = #{id}")
    public Order findOrderById(@Param("id") Integer id);

    @Select("SELECT * FROM orders WHERE uid = #{uid}")
    public Order findOrderByUid(@Param("uid") Integer uid);

    @Select("SELECT * FROM orders WHERE order_no LIKE CONCAT('%', #{keyword}, '%') OR amount LIKE CONCAT('%', #{keyword}, '%') LIMIT #{offset}, #{size}")
    List<Order> searchOrders(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM orders WHERE order_no LIKE CONCAT('%', #{keyword}, '%') OR amount LIKE CONCAT('%', #{keyword}, '%')")
    int countOrdersByKeyword(@Param("keyword") String keyword);


    Boolean updateOrderStatusByOrderNo(String order_no, Integer status);

    Integer checkOrderStatus(String order_no);

    BigDecimal findOrderAmount(String order_no);
}

package com.mall.mechmall.mapper;

import com.mall.mechmall.domain.Product;
import com.mall.mechmall.domain.ShippingAddress;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: thh
 * @Date: 2024/7/3
 * @Description:
 */
@Mapper
public interface ShippingAddressMapper {
    @Select("SELECT * FROM shipping_address WHERE user_id = #{userId}")
    public List<ShippingAddress> getAddressesByUserId(Integer userId);
    @Select("SELECT COUNT(*) FROM shipping_address WHERE user_id = #{userId}")
    public int countAddress(Integer userId);

    @Insert("INSERT INTO shipping_address (user_id, name, phone, mobile, province, city, district, addr, zip,create_time) " +
            "VALUES (#{userId}, #{name}, #{phone}, #{mobile}, #{province}, #{city}, #{district}, #{addr}, #{zip}, Now())")
    public int addShippingAddress(ShippingAddress shippingAddress);
    @Update({
            "<script>",
            "UPDATE shipping_address",
            "<set>",
            "<if test='name != null'>name = #{name},</if>",
            "<if test='phone != null'>phone = #{phone},</if>",
            "<if test='mobile != null'>mobile = #{mobile},</if>",
            "<if test='province != null'>province = #{province},</if>",
            "<if test='city != null'>city = #{city},</if>",
            "<if test='district != null'>district = #{district},</if>",
            "<if test='addr != null'>addr = #{addr},</if>",
            "<if test='zip != null'>zip = #{zip},</if>",
            "<if test='dfault != null'>dfault = #{dfault},</if>",
            "<if test='isDel != null'>isDel = #{isDel},</if>",
            "<if test='update_time != null'>update_time = #{updateTime},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    public int updateShippingAddress(ShippingAddress shippingAddress);

    @Delete("DELETE FROM shipping_address WHERE id = #{id}")
    public int deleteProduct(@Param("id" ) Integer id);

    @Update("UPDATE shipping_address SET dfault = 1 WHERE id = #{id}")
    public int updateDfault(@Param("id" ) Integer id);


    @Update("UPDATE shipping_address SET dfault = 0 WHERE user_id = #{userId} and dfault=1")
    public int resetDfault(@Param("userId" ) Integer userId);

}

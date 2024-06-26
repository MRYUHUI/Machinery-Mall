package com.mall.mechmall.mapper;

import com.mall.mechmall.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: thh
 * @Date: 2024/6/28
 * @Description:
 */
@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM products  LIMIT #{offset}, #{size}")
    public List<Product> findAllProduct(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM products")
    public int countProduct();

    @Select("SELECT * FROM products WHERE id = #{id}")
    public List<Product> findProductById(@Param("id") Integer id);

    @Select("SELECT id, name, price, status, product_id, parts_id, icon_url, is_hot FROM products WHERE id LIKE CONCAT('%', #{keyword}, '%') OR name LIKE CONCAT('%', #{keyword}, '%') LIMIT #{offset}, #{size}")
    List<Product> searchProduct(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM products WHERE id LIKE CONCAT('%', #{keyword}, '%') OR name LIKE CONCAT('%', #{keyword}, '%')")
    int countProductByKeyword(String keyword);

    @Insert(" INSERT INTO products(name, product_id, parts_id, sub_images, detail, spec_param, price, stock, created) " +
            " VALUES (#{name}, #{productId}, #{partsId}, #{subImages}, #{detail}, #{specParam}, #{price}, #{stock}, NOW() )")
    public int insertProduct(Product products);

    @Insert("INSERT INTO products(icon_url)" +
            " VALUES (#{iconUrl})")
    public Boolean insertIconUrl(String iconUql);

    @Select("SELECT COUNT(*) FROM products WHERE name = #{name}")
    public int checkProductByName(@Param("name") String name);

    @Delete("DELETE FROM products WHERE id = #{id}")
    public int deleteProduct(@Param("id" ) Integer id);

    @Update({
            "<script>",
            "UPDATE products",
            "<set>",
            "<if test='name != null'>name = #{name},</if>",
            "<if test='productId != null'>product_id = #{productId},</if>",
            "<if test='partsId != null'>parts_id = #{partsId},</if>",
            "<if test='iconUrl != null'>icon_uri = #{iconUrl},</if>",
            "<if test='subImages != null'>sub_images = #{subImages},</if>",
            "<if test='detail != null'>detail = #{detail},</if>",
            "<if test='specParam != null'>spec_param = #{specParam},</if>",
            "<if test='price != null'>price = #{price},</if>",
            "<if test='status != null'>status = #{status},</if>",
            "<if test='isHot != null'>is_hot = #{isHot},</if>",
            "<if test='updated != null'>updated = #{updated},</if>",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    public int updateProduct(Product product);

}

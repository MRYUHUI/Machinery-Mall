package com.mall.mechmall.mapper;

import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.domain.User;
import org.apache.ibatis.annotations.*;
import java.util.List;
/**
 * @Author:
 * @Date: 2024-06-25-21:52
 * @Description: 商品类型管理模块用户持久层
 */
@Mapper
public interface ProductCategoryMapper {

    @Insert("INSERT INTO product_category (parent_id, name, status, created, updated) " +
            "VALUES (#{parentId}, #{name}, #{status}, #{created}, #{updated})")
    public int insertProductCategory(ProductCategory productCategory);

    @Update({
            "<script>",
            "UPDATE product_category",
            "<set>",
            "<if test='parentId != null'>parent_id = #{parentId},</if>",
            "<if test='name != null'>name = #{name},</if>",
            "<if test='sortOrder != null'>sort_order = #{sortOrder},</if>",
            "<if test='status != null'>status = #{status},</if>",
            "<if test='level != null'>level = #{level},</if>",
            "updated = NOW(),",
            "</set>",
            "WHERE id = #{id}",
            "</script>"
    })
    public int updateProductCategory(ProductCategory productCategory);

    @Select("SELECT * FROM product_category WHERE parent_id = #{parentId}")
    public List<ProductCategory> findProductCategoriesByParentId(@Param("parentId") int parentId);

    @Select("SELECT * FROM product_category WHERE id = #{id}")
    public ProductCategory findProductCategoryById(@Param("id") int id);

    @Delete("DELETE FROM product_category WHERE id = #{id}")
    public boolean deleteProductCategory(@Param("id") int id);

    @Select("SELECT id FROM product_category WHERE parent_id = #{parentId}")
    public List<Integer> findProductCategoryChildrenIds(@Param("parentId") int parentId);

    @Select("SELECT * FROM product_category WHERE parent_id = #{parentId}")
    public List<ProductCategory> findProductCategoryChildrens(@Param("parentId") int parentId);

    @Delete("DELETE FROM product_category WHERE parent_id = #{parentId}")
    public boolean deleteProductCategoryChildren(@Param("parentId") int parentId);

    @Select("SELECT * FROM product_category WHERE parent_id = #{parentId} AND name = #{name}")
    public ProductCategory findProductCategoryByParentIdAndName(@Param("parentId") int parentId, @Param("name") String name);

    @Select("SELECT * FROM product_category LIMIT #{offset}, #{size}")
    public List<ProductCategory> findAllProductCategorysPage(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM product_category")
    public List<ProductCategory> findAllProductCategorys();

    @Select("SELECT * FROM product_category WHERE status = 1 LIMIT #{offset}, #{size}")
    public List<ProductCategory> findAllValidityProductCategorys(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM product_category WHERE status = 0 LIMIT #{offset}, #{size}")
    public List<ProductCategory> findAllInvalidityProductCategorys(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM product_category WHERE parent_id = 0 LIMIT #{offset}, #{size}")
    public List<ProductCategory> findAllHighestProductCategorys(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM product_category")
    public int countProductCategorys();

    @Select("SELECT COUNT(*) FROM product_category WHERE status = 1")
    public int countValidityProductCategorys();

    @Select("SELECT COUNT(*) FROM product_category WHERE status = 0")
    public int countInvalidityProductCategorys();

    @Select("SELECT COUNT(*) FROM product_category WHERE parent_id = #{parentId}")
    public int countProductCategoryChildrens(@Param("parentId") int parentId);

    @Select("SELECT * FROM product_category WHERE id LIKE CONCAT('%', #{keyword}, '%') OR name LIKE CONCAT('%', #{keyword}, '%') LIMIT #{offset}, #{size}")
    public List<ProductCategory> searchProductCategorys(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM product_category WHERE id LIKE CONCAT('%', #{keyword}, '%') OR name LIKE CONCAT('%', #{keyword}, '%')")
    public int countProductCategoryByKeyword(String keyword);

}

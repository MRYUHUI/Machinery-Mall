package com.mall.mechmall.mapper;

import com.mall.mechmall.domain.ProductCategory;
import org.apache.ibatis.annotations.*;
import java.util.List;
/**
 * @Author:
 * @Date: 2024-06-25-21:52
 * @Description: 商品类型管理模块用户持久层
 */
@Mapper
public interface ProductCategoryMapper {

    @Insert("INSERT INTO product_category (parent_id, name, sort_order, status, level, created, updated) " +
            "VALUES (#{parentId}, #{name}, #{sortOrder}, #{status}, #{level}, NOW(), NOW())")
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

    @Update("UPDATE product_category SET sort_order = #{sortOrder} WHERE id = #{id}")
    public int updateChildSortOrder(@Param("id") int id, @Param("sortOrder") int sortOrder);

    @Select("SELECT * FROM product_category WHERE parent_id = #{id}")
    public List<ProductCategory> findProductCategoriesByParentId(@Param("id") int id);

    @Select("SELECT * FROM product_category WHERE id = #{id}")
    public ProductCategory findProductCategoryById(@Param("id") int id);

    @Select("SELECT * FROM product_category WHERE name = #{name}")
    public ProductCategory findProductCategoryByName(@Param("name") String name);

    @Delete("DELETE FROM product_category WHERE id = #{id}")
    public boolean deleteProductCategory(@Param("id") int id);

    @Select("SELECT id FROM product_category WHERE parent_id = #{parentId}")
    public List<Integer> findProductCategoryChildrenIds(@Param("parentId") int parentId);

    @Select("SELECT * FROM product_category WHERE parent_id = #{parentId} LIMIT #{offset}, #{size}")
    public List<ProductCategory> findProductCategoryChildrens(@Param("parentId") int parentId,@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT * FROM product_category")
    public List<ProductCategory> findAllProductCategorys();

    @Select("SELECT * FROM product_category WHERE parent_id = 0 LIMIT #{offset}, #{size}")
    public List<ProductCategory> findAllHighestProductCategorys(@Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM product_category")
    public int countProductCategorys();

    @Select("SELECT COUNT(*) FROM product_category WHERE parent_id = #{parentId}")
    public int countProductCategoryChildrens(@Param("parentId") int parentId);

    @Select("SELECT * FROM product_category WHERE id LIKE CONCAT('%', #{keyword}, '%') OR name LIKE CONCAT('%', #{keyword}, '%') LIMIT #{offset}, #{size}")
    public List<ProductCategory> searchProductCategorys(@Param("keyword") String keyword, @Param("offset") int offset, @Param("size") int size);

    @Select("SELECT COUNT(*) FROM product_category WHERE id LIKE CONCAT('%', #{keyword}, '%') OR name LIKE CONCAT('%', #{keyword}, '%')")
    public int countProductCategoryByKeyword(String keyword);

    // 2024.7.4
    @Select("SELECT * FROM product_category WHERE parent_id = 0")
    public List<ProductCategory> findAllHighestProductCategories();






    /*以下是前台部分*/
    @Select({
            "WITH RECURSIVE category_ids AS (",
            "    SELECT id FROM product_category WHERE parent_id = #{parentId}",
            "    UNION ALL",
            "    SELECT pc.id FROM product_category pc",
            "    JOIN category_ids ct ON pc.parent_id = ct.id",
            ")",
            "SELECT id, parent_id, name, sort_order, status, level, created, updated",
            "FROM product_category",
            "WHERE id IN (SELECT id FROM category_ids)"
    })
    public List<ProductCategory> getProductCategories(@Param("parentId") Integer parentId);

}

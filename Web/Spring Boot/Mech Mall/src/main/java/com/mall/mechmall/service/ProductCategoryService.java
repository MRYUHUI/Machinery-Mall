package com.mall.mechmall.service;

import com.mall.mechmall.domain.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:商品类型管理模块Service层
 * @Date: 2024-06-25-22:03
 * @Description:
 */
public interface ProductCategoryService {
    boolean updateProductCategory(ProductCategory productCategory, int oldParentId);

    ProductCategory findProductCategoryById(int id);
    // 2024.7.4
    public List<ProductCategory> findProductCategoriesByParentId(int id);

    List<ProductCategory> findProductCategoryByParentId(int id);

    ProductCategory findProductCategoryByName(String name);

    boolean deleteProductCategory(int id, int parentId);

    List<ProductCategory> findProductCategoryChildrens(int parent_id, int page, int size);

    //    boolean deleteProductCategoryAndChildren(int id, int parentId);
    List<ProductCategory> findAllProductCategorys();

    List<ProductCategory> findAllHighestProductCategorys(int page, int size);

    int countProductCategorys();

    int countProductCategoryChildrens(int id);

    List<ProductCategory> searchProductCategorys(String keyword, int page, int size);

    int countProductCategoryByKeyword(String keyword);

    // 2024.7.4
    public List<ProductCategory> findAllHighestProductCategories();

    List<ProductCategory> getProductCategories(Integer parentId);
}

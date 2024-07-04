package com.mall.mechmall.service;

import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:商品类型管理模块Service层
 * @Date: 2024-06-25-22:03
 * @Description:
 */
public interface ProductCategoryService {
    int insertProductCategory(ProductCategory productCategory);
    boolean updateProductCategory(ProductCategory productCategory , int oldParentId);
    List<ProductCategory> findProductCategoriesByParentId(int id);
    ProductCategory findProductCategoryById(int id);
    boolean deleteProductCategory(int id);
    List<Integer> findProductCategoryChildrenIds(int parent_id);
    List<ProductCategory> findProductCategoryChildrens(int parent_id);
    boolean deleteProductCategoryAndChildren(int id);
    boolean deleteProductCategoryChildren(int parent_id);
    ProductCategory findProductCategoryByParentIdAndName(int parentId, String name);
    List<ProductCategory> findAllProductCategorysPage(int page, int size);
    List<ProductCategory> findAllProductCategorys();
    List<ProductCategory> findAllValidityProductCategorys(int page, int size);
    List<ProductCategory> findAllInvalidityProductCategorys(int page, int size);
    List<ProductCategory> findAllHighestProductCategorys(int page, int size);
    int countProductCategorys();
    int countValidityProductCategorys();
    int countInvalidityProductCategorys();
    int countProductCategoryChildrens(int id);
    List<ProductCategory> searchProductCategorys(String keyword, int page, int size);
    int countProductCategoryByKeyword(String keyword);
}

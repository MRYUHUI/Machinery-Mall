package com.mall.mechmall.service;

import com.mall.mechmall.domain.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: thh
 * @Date: 2024/6/28
 * @Description:
 */
public interface ProductService {
    List<Product> findAllProduct(int page, int size);

    int countProduct();

    Product findProductById(Integer id);

    List<Product> searchProduct(String keyword, int page, int size);

    int countProductByKeyword(String keyword);

    Boolean insertProduct(Product product);

    Boolean insertIconUrl(String iconUrl);

    Boolean checkProductByName(String name);

    Boolean deleteProduct(Integer id);

    Boolean updateProduct(Product product);

    List<Product> findAllProductsByStatusAndHot();

    List<Product> findProductsByStatusAndHotWithLimit(int limit);

    public boolean updateIconUrl(int id, String iconUrl);

    /**
     * 查找相应顶级分类的商品
     * @return 商品列表
     */
    public Map<String, List<Product>> findAllProductOrderByHighestCategoryLimit(int limit);

    // 根据 partsId获取商品列表
    public List<Product> findProductsByPartstId(int partsId);

    public List<Product> findProductsByProductIdLimit(int limit, int productId);

    List<Product> findProductsByProductId(int id);
}

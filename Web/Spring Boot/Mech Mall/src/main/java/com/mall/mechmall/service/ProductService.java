package com.mall.mechmall.service;

import com.mall.mechmall.domain.Product;

import java.util.List;

/**
 * @Author: thh
 * @Date: 2024/6/28
 * @Description:
 */
public interface ProductService {
    List<Product> findAllProduct(int page, int size);
    int countProduct();
    List<Product> findProductById(Integer id);
    List<Product> searchProduct(String keyword, int page, int size);
    int countProductByKeyword(String keyword);

    Boolean insertProduct(Product product);

    Boolean insertIconUrl(String iconUrl);

    Boolean checkProductByName(String name);

    Boolean deleteProduct(Integer id);

    Boolean updateProduct(Product product);
}

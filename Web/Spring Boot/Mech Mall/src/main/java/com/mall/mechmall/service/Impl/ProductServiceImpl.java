package com.mall.mechmall.service.Impl;

import com.mall.mechmall.domain.Product;
import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.mapper.ProductCategoryMapper;
import com.mall.mechmall.mapper.ProductMapper;
import com.mall.mechmall.service.ProductCategoryService;
import com.mall.mechmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: thh
 * @Date: 2024/6/28
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    // 2024.7.4
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findAllProduct(int page, int size){
        int offset = (page - 1) * size;
        return productMapper.findAllProduct(offset, size);
    }

    @Override
    public int countProduct(){
        return productMapper.countProduct();
    }

    @Override
    public Product findProductById(Integer id){
        return productMapper.findProductById(id);
    }

    @Override
    public List<Product> searchProduct(String keyword, int page, int size){
        int offset = (page - 1) * size;
        return productMapper.searchProduct(keyword,offset,size);
    }

    @Override
    public int countProductByKeyword(String keyword){
        return productMapper.countProductByKeyword(keyword);
    }

    @Override
    public Boolean insertProduct(Product product){
        return productMapper.insertProduct(product) > 0;
    }
    @Override
    public Boolean insertIconUrl(String iconUrl){
        return productMapper.insertIconUrl(iconUrl);
    }

    @Override
    public Boolean checkProductByName(String name){
        return productMapper.checkProductByName(name)> 0;
    }

    @Override
    public Boolean deleteProduct(Integer productId){
        return productMapper.deleteProduct(productId) > 0;
    }

    @Override
    public Boolean updateProduct(Product product){
        return productMapper.updateProduct(product) > 0;
    }

    @Override
    public List<Product> findAllProductsByStatusAndHot() {
        return productMapper.findAllProductsByStatusAndHot();
    }

    @Override
    public List<Product> findProductsByStatusAndHotWithLimit(int limit) {
        return productMapper.findProductsByStatusAndHotWithLimit(limit);
    }

    @Override
    public boolean updateIconUrl(int id, String iconUrl) {
        return productMapper.updateIconUrl(id, iconUrl) > 0;
    }

    /**
     * 查找相应顶级分类的商品
     * @return 商品列表
     */
    @Override
    public Map<String, List<Product>> findAllProductOrderByHighestCategoryLimit(int limit) {
        // 查找所有所有 level = 0 的分类
        List<ProductCategory> highestCategories = productCategoryService.findAllHighestProductCategories();
//        顶级分类对应的商品
        Map<String, List<Product>> allProductsOrderByCategory = new HashMap<>();
        for(ProductCategory productCategory : highestCategories) {
//            获取分类对应的商品
            List<Product> products = productMapper.findProductsByProductIdLimit(limit, productCategory.getId());
            // 以分类名为 key， 商品列表为 value
            allProductsOrderByCategory.put(productCategory.getName(), products);
        }
        return allProductsOrderByCategory;
    }
    @Override
    public List<Product> findProductsByPartstId(int partsId) {
        return productMapper.findProductsByPartstId(partsId);
    }

    @Override
    public List<Product> findProductsByProductIdLimit(int limit, int productId) {
        return productMapper.findProductsByProductIdLimit(limit, productId);
    }

    @Override
    public List<Product> findProductsByProductId(int id) {
        return productMapper.findProductsByProductId(id);
    }

}

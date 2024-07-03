package com.mall.mechmall.service.Impl;

import com.mall.mechmall.domain.Product;
import com.mall.mechmall.mapper.ProductMapper;
import com.mall.mechmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: thh
 * @Date: 2024/6/28
 * @Description:
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
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

}

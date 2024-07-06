package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.Product;
import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.service.ProductCategoryService;
import com.mall.mechmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.mall.mechmall.utils.Consts.*;

import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Date: 2024-07-02-21:49
 * @Description:
 */
@RestController
@RequestMapping("/commodity/home")
public class HomeCommodityController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取所有热销商品
     * @return JSONObject
     */
    @GetMapping("/hot/all")
    public JSONObject findAllHotCommodity(){
        List<Product> hotProducts = productService.findAllProductsByStatusAndHot();
        JSONObject json = getJson("获取热门商品成功", true);
        json.put(DATA, hotProducts);
        return json;
    }
    @GetMapping("/hot/limit")
    public JSONObject findAllHotCommodityLimit(@RequestParam int limit){
        List<Product> hotProducts = productService.findProductsByStatusAndHotWithLimit(limit);
        System.out.println(hotProducts);
        JSONObject json = getJson("获取热门商品成功", true);
        json.put(DATA, hotProducts);
        return json;
    }

    /**
     * 获取所有 parenId = 0 的分类的商品列表
     * @return 一个键值对，每个 key 对应一个列表
     */
    @GetMapping("/product/highest/category")
    public JSONObject  findAllProductOrderByHighestCategoryLimit(@RequestParam int limit) {
        Map<String, List<Product>> allProductOrderByHighestCategory = productService.findAllProductOrderByHighestCategoryLimit(limit);
        JSONObject json = getJson("获取商品列表成功", true);
        json.put(DATA, allProductOrderByHighestCategory);
        return json;
    }

    /**
     * 根据parent id查找所有子分类
     * @param parentId
     * @return
     */
    @GetMapping("/find/sub/category")
    public JSONObject findCategoriesByParentId(@RequestParam int parentId) {
        List<ProductCategory> subCategories = productCategoryService.findProductCategoriesByParentId(parentId);
        JSONObject json = getJson("获取子分类成功", true);
        json.put(DATA, subCategories);
        return json;
    }

    /**
     * 根据partsId查询商品
     * @param id
     * @return
     */
    @GetMapping("/find/products/third-id")
    public JSONObject findAllProductsByThirdCategory(@RequestParam int id) {
        System.out.println("===========================\n"+ id);
        List<Product> products = productService.findProductsByPartstId(id);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, products);
        return json;
    }

    /**
     * 根据 productId 获取商品列表
     * @return 一个键值对，每个 key 对应一个列表
     */
    @GetMapping("/product/highest/category/single")
    public JSONObject  findProductsByProductId(@RequestParam int id) {
        List<Product> products = productService.findProductsByProductId(id);
        JSONObject json = getJson("获取商品列表成功", true);
        json.put(DATA, products);
        return json;
    }

    /*查询所有分类*/
    @GetMapping("/productCategory/getProductCategories")
    public Object getProductCategories(@RequestParam int parentId){
        List<ProductCategory> productCategories = productCategoryService.getProductCategories(parentId);
        int total = productCategoryService.countProductCategorys();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, productCategories);
        json.put("total", total);
        return json;
    }

    @PostMapping("/update/stock")
    public Object updateStock(@RequestBody Product product){
        if(productService.updateStock(product.getId(), product.getStock()))
            return getJson("更新订单成功", true);
        return getJson("更新订单失败", false);
    }

    @GetMapping("/find/stock")
    public Object findStockById(@RequestParam int id){
        int stockById = productService.findStockById(id);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, stockById);
        return json;
    }

    @GetMapping("/search")
    public JSONObject findSearchCommodity(@RequestParam String keyword){
        List<Product> searchProducts = productService.searchProductCategorys(keyword);
        JSONObject json = getJson("搜索商品成功", true);
        json.put(DATA, searchProducts);
        return json;
    }


}

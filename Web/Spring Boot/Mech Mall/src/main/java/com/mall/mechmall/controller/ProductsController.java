package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.Product;
import com.mall.mechmall.service.ProductService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.mall.mechmall.utils.Consts.DATA;
import static com.mall.mechmall.utils.Consts.getJson;

/**
 * @Author: thh
 * @Date: 2024/6/28
 * @Description:
 */
@RestController
@RequestMapping("/admin/product")
public class ProductsController {
    @Autowired
    private ProductService productService;
    //获取所有商品
    @GetMapping("/allProduct")
    public Object getAllProduct(@RequestParam int page, @RequestParam int size){
        List<Product> allProducts = productService.findAllProduct(page, size);
        JSONObject json = getJson("获取成功", true);
        int total = productService.countProduct();
        json.put(DATA, allProducts);
        json.put("total", total);
        return json ;
    }
    //查找商品
    @GetMapping("/searchProduct")
    public Object searchProduct(@RequestParam String keyword, @RequestParam int page,@RequestParam int size){
        List<Product> searchProduct = productService.searchProduct(keyword, page, size);
        int total = productService.countProductByKeyword(keyword);
        JSONObject json = getJson("搜索成功", true);
        System.out.println("======="+searchProduct);
        json.put(DATA,searchProduct);
        json.put("total",total);
        return json;
    }
    @GetMapping("/getDetail")
    public Object getDetail(@RequestParam Integer id){
        List<Product>  detailProduct = productService.findProductById(id);
        JSONObject json = getJson("获取成功",true);
        System.out.println("======" + detailProduct);
        json.put(DATA,detailProduct);
        return json;
    }
    //添加商品 ang 更新商品
    @PostMapping("/saveProduct")
    public Object saveProduct(@RequestBody Product product) {
        if (productService.checkProductByName(product.getName())) {
           if(productService.updateProduct(product)){
               return getJson("产品更新成功",true);
           }else {
               return getJson("产品更新失败",true);
           }
        }else {
            if (productService.insertProduct(product)) {
                return getJson("添加成功", true);
            } else {
                return getJson("添加失败", false);
            }
        }
    }
    //改变商品状态
    @PostMapping("updateStatus")
    public Object updateStatus(@RequestBody Product product){
        System.out.println(product);
        if(productService.updateProduct(product)){
            return getJson("修改状态成功",true);
        }else {
            return getJson("修改状态失败",false);
        }
    }
    //插入图片
    /*
    @PostMapping("/insertIconUrl")
    public Object insertIconUrl(@RequestParam String iconUrl){
        if(productService.insertIconUrl(iconUrl)){
            return getJson("图片添加成功",true);
        }else {
            return getJson("图片添加失败",false);
        }
    }
     */

    //删除商品
    @GetMapping("/deleteProduct")
    public Object deleteProduct(@RequestParam Integer id){
        if(productService.deleteProduct(id)){
            return getJson("删除成功",true);
        }else {
            return getJson("删除失败",false);
        }
    }

}

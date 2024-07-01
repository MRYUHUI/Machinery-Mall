package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.domain.User;
import com.mall.mechmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.mall.mechmall.utils.Consts.DATA;
import static com.mall.mechmall.utils.Consts.getJson;

/**
 * @Author:
 * @Date: 2024-06-25-22:03
 * @Description:商品类型管理模块Controller层
 */
@RestController
@RequestMapping("/admin/productCategory")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;
    /*查找全部有效数据*/
    @GetMapping("/allProductCategorys")
    public Object findAllProductCategorysPage(@RequestParam int page, @RequestParam int size){
//        System.out.println("<page,size> = <"+page+","+size+">");
        List<ProductCategory> allProductCategorys = null;
        if(page == -1 && size == -1){
            allProductCategorys = productCategoryService.findAllProductCategorys();
        }else {
            allProductCategorys = productCategoryService.findAllProductCategorysPage(page,size);
        }
        int total = productCategoryService.countProductCategorys();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allProductCategorys);
        json.put("total", total);
        return json;
    }

    @GetMapping("/allHighestProductCategorys")
    public Object findAllHighestProductCategorys(@RequestParam int page, @RequestParam int size){
        List<ProductCategory> allProductCategorys = productCategoryService.findAllHighestProductCategorys(page,size);
        int total = productCategoryService.countProductCategorys();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allProductCategorys);
        json.put("total", total);
        return json;
    }

    @PostMapping("/allProductCategoryChildrens")
    public Object findProductCategoryChildrens(@RequestBody Map<String, Integer> params){
//        int ParentId = productCategory.getId();
        Integer ParentId = params.get("ParentId");
        System.out.println("ParentId = "+ParentId);
        List<ProductCategory> allProductCategoryChildrens = productCategoryService.findProductCategoryChildrens(ParentId);
        int total = productCategoryService.countProductCategoryChildrens(ParentId);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allProductCategoryChildrens);
        json.put("total", total);
        return json;
    }

    @GetMapping("/searchProductCategorys")
    public Object searchProductCategorys(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        List<ProductCategory> searchResult = productCategoryService.searchProductCategorys(keyword, page, size);
        int total = productCategoryService.countProductCategoryByKeyword(keyword);
        JSONObject json = getJson("搜索成功", true);
        json.put(DATA, searchResult);
        json.put("total", total);
        return json;
    }

    /*删除分类及其子分类*/
    @GetMapping("/deleteAdminGoodType")
    public Object deleteAdminGoodType(@RequestParam int id) {
        boolean deleted = productCategoryService.deleteProductCategoryAndChildren(id);
        if (deleted) {
            return getJson("分类删除成功", true);
        } else {
            return getJson("分类删除失败", false);
        }
    }

    @PostMapping("/updateProductCategoryInfo")
    public Object updateProductCategory(@RequestBody ProductCategory productCategory , @RequestParam int oldParentId){
        //进行productCategory更新
        boolean updated = productCategoryService.updateProductCategory(productCategory , oldParentId);
        if (updated) {
            // 获取更新后的用户信息
            ProductCategory newUserProductCategory = productCategoryService.findProductCategoryById(productCategory.getId());
            JSONObject json = getJson("产品参数修改成功！", true);
            json.put(DATA, newUserProductCategory);
            return json;
        } else {
            return getJson("产品参数修改失败！", false);
        }
    }

    @GetMapping("/findAllValidityProductCategorys")
    public Object findAllValidityProductCategorys(@RequestParam int page, @RequestParam int size) {
        List<ProductCategory> validityResult = productCategoryService.findAllValidityProductCategorys(page, size);
        int total = productCategoryService.countValidityProductCategorys();
//        System.out.println(validityResult);
        JSONObject json = getJson("搜索成功", true);
        json.put(DATA, validityResult);
        json.put("total", total);
        return json;
    }
}

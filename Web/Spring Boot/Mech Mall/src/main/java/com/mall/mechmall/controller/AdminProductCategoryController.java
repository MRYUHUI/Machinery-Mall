package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.ProductCategory;
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
 * @Description:后台商品类型管理模块Controller层
 */
@RestController
@RequestMapping("/admin/productCategory")
public class AdminProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    /*查询所有分类*/
    @GetMapping("/allProductCategorys")
    public Object findAllProductCategorysPage(){
        List<ProductCategory> allProductCategorys = productCategoryService.findAllProductCategorys();
        int total = productCategoryService.countProductCategorys();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allProductCategorys);
        json.put("total", total);
        return json;
    }
    @GetMapping("/parents")
    public Object getParentCategories(@RequestParam int id) {
        List<ProductCategory> productCategories = productCategoryService.findProductCategoryByParentId(id);
        JSONObject json = getJson("获取成功",true);
        json.put(DATA,productCategories);
        return json;
    }

    /*查询所有顶级节点并分页*/
    @GetMapping("/allHighestProductCategorys")
    public Object findAllHighestProductCategorys(@RequestParam int page, @RequestParam int size){

        List<ProductCategory> allProductCategorys = productCategoryService.findAllHighestProductCategorys(page,size);
        int total = productCategoryService.countProductCategorys();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allProductCategorys);
        json.put("total", total);
        return json;
    }

    /*查询所有非顶级节点*/
    @PostMapping("/allProductCategoryChildrens")
    public Object findProductCategoryChildrens(@RequestBody Map<String, Object> params){
        Integer ParentId = (Integer) params.get("ParentId");
        Integer page = (Integer) params.get("page");
        Integer size = (Integer) params.get("size");
        List<ProductCategory> allProductCategoryChildrens = productCategoryService.findProductCategoryChildrens(ParentId,page,size);
        int total = productCategoryService.countProductCategoryChildrens(ParentId);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allProductCategoryChildrens);
        json.put("total", total);
        return json;
    }

    /*搜索有关键字的分类*/
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
    @PostMapping("/deleteAdminGoodType")
    public Object deleteAdminGoodType(@RequestBody Map<String, Integer> request) {
        int id = request.get("id");
        int pid = productCategoryService.findProductCategoryById(id).getParentId();
        boolean deleted = productCategoryService.deleteProductCategory(id, pid);
        if (deleted) {
            return getJson("分类删除成功", true);
        } else {
            return getJson("分类删除失败", false);
        }
    }

    /*更新分类*/
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

    /*增加分类*/
    @PostMapping("/insertProductCategory")
    public Object insertProductCategory(@RequestBody Map<String, Object> request){

        String name = (String) request.get("name");
        Integer parentId = (Integer) request.get("parentId");
        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(name);
        productCategory.setParentId(parentId);

        int parentLevel = parentId == 0 ? 0 : productCategoryService.findProductCategoryById(parentId).getLevel();
        int parentChildrenCount = productCategoryService.countProductCategoryChildrens(parentId);

        productCategory.setSortOrder(parentChildrenCount+1);
        productCategory.setStatus(false);
        productCategory.setLevel(parentLevel+1);

        //进行productCategory更新
        boolean insertd = productCategoryService.updateProductCategory(productCategory , -1);
        if (insertd) {
            // 获取更新后的用户信息
            ProductCategory newUserProductCategory = productCategoryService.findProductCategoryByName(name);
            JSONObject json = getJson("产品参数修改成功！", true);
            json.put(DATA, newUserProductCategory);
            return json;
        } else {
            return getJson("产品参数修改失败！", false);
        }
    }
}

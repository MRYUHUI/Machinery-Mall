package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.Product;
import com.mall.mechmall.service.ProductService;
import com.mall.mechmall.utils.Consts;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.mall.mechmall.utils.Consts.*;

/**
 * @Author: thh
 * @Date: 2024/6/28
 * @Description:
 */
@RestController
@RequestMapping("/admin/product")
public class AdminProductsController {
    @Autowired
    private ProductService productService;

    //获取所有商品
    @GetMapping("/allProduct")
    public Object getAllProduct(@RequestParam int page, @RequestParam int size) {
        List<Product> allProducts = productService.findAllProduct(page, size);
        System.out.println(allProducts);
        JSONObject json = getJson("获取成功", true);
        int total = productService.countProduct();
        json.put(DATA, allProducts);
        json.put("total", total);
        return json;
    }

    //查找商品
    @GetMapping("/searchProduct")
    public Object searchProduct(@RequestParam String keyword, @RequestParam int page, @RequestParam int size) {
        List<Product> searchProduct = productService.searchProduct(keyword, page, size);
        int total = productService.countProductByKeyword(keyword);
        JSONObject json = getJson("搜索成功", true);
        System.out.println("=======" + searchProduct);
        json.put(DATA, searchProduct);
        json.put("total", total);
        return json;
    }

    @GetMapping("/getDetail")
    public Object getDetail(@RequestParam Integer id) {
        Product detailProduct = productService.findProductById(id);
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, detailProduct);
        return json;
    }

    //添加商品 ang 更新商品
    @PostMapping("/saveProduct")
    public Object saveProduct(@RequestBody Product product) {
        if (productService.checkProductByName(product.getName())) {
            if (productService.updateProduct(product)) {
                return getJson("产品更新成功", true);
            } else {
                return getJson("产品更新失败", true);
            }
        } else {
            if (productService.insertProduct(product)) {
                return getJson("添加成功", true);
            } else {
                return getJson("添加失败", false);
            }
        }
    }

    //改变商品状态
    @PostMapping("/updateStatus")
    public Object updateStatus(@RequestBody Product product) {
        System.out.println(product);
        if (productService.updateProduct(product)) {
            return getJson("修改状态成功", true);
        } else {
            return getJson("修改状态失败", false);
        }
    }
    //删除商品
    @GetMapping("/deleteProduct")
    public Object deleteProduct(@RequestParam Integer id) {
        if (productService.deleteProduct(id)) {
            return getJson("删除成功", true);
        } else {
            return getJson("删除失败", false);
        }
    }


    // 上传图片
    @PostMapping("/img/update")
    public Object updateGoodsImg(@RequestParam("file") MultipartFile ImgSrc, @RequestParam("id") int id) {
        if (ImgSrc.isEmpty()) {
            return getJson("文件上传失败", false);
        }

        String picPath = productService.findProductById(id).getIconUrl();
        boolean flag = deleteFile(picPath);
        if (!flag) return getJson("文件上传失败", false);

        // 文件名 = 当前时间（毫秒）+ 原来文件名
        String fileName = System.currentTimeMillis() + ImgSrc.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
                + System.getProperty("file.separator") + "main"
                + System.getProperty("file.separator") + "resources"
                + System.getProperty("file.separator") + "static"
                + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "goods";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdirs();  // 使用 mkdirs() 方法确保创建所有必要的目录
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeImgPath = "/static/img/goods/" + fileName;
        System.out.println("storeImgPath " + storeImgPath);

        try {
            ImgSrc.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            return getJson("文件上传失败", false);
        }

        // 文件上传成功后的操作
        try {
            Product product = new Product();
            product.setId(id);
            product.setIconUrl(storeImgPath);

            System.out.println("Updating product with ID: " + id + " with new image path: " + storeImgPath);
            boolean isStoreImgUrlSuccess = productService.updateProduct(product);

            if (isStoreImgUrlSuccess) {
                return getJson("文件上传成功", true);
            } else {
                return getJson("文件上传失败", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return getJson("文件上传失败", false);
    }


}

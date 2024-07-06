package com.mall.mechmall.controller;

import com.alibaba.fastjson.JSONObject;
import com.mall.mechmall.domain.Cart;
import com.mall.mechmall.domain.Product;
import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.service.ProductService;
import com.mall.mechmall.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.mall.mechmall.utils.Consts.DATA;
import static com.mall.mechmall.utils.Consts.getJson;

@RestController
@RequestMapping("/user/cart")
public class UserCartController {
    @Autowired
    private UserCartService userCartService;

    @Autowired
    private ProductService productService;

    /*查询购物车物品数*/
    @GetMapping("/allCartCount")
    public Object allCartCount(){
        int count = userCartService.countcarts();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, count);
        return json;
    }

    /*清空购物车*/
    @GetMapping("/clearCart")
    public Object clearCart(){
        boolean cleard = userCartService.clearCart();
        if (cleard) {
            return getJson("购物车清空成功", true);
        } else {
            return getJson("购物车清空失败", false);
        }
    }

    /*删除商品*/
    @PostMapping("/deleteCartItem")
    public Object deleteCartItem(@RequestBody Map<String, Integer> request) {
        int id = request.get("id");
        boolean deleted = userCartService.deleteCartItem(id);
        if (deleted) {
            return getJson("商品删除成功", true);
        } else {
            return getJson("商品删除失败", false);
        }
    }

    /*获取购物车物品*/
    @GetMapping("/allCartItem")
    public Object allCartItem(@RequestParam int page, @RequestParam int size){
        List<Cart> allCartItems = userCartService.allCartItem(page,size);
        int total = userCartService.countcarts();
        JSONObject json = getJson("获取成功", true);
        json.put(DATA, allCartItems);
        json.put("total", total);
        return json;
    }

    /*增加商品*/
    @PostMapping("/addCartItem")
    public Object addCartItem(@RequestBody Map<String, Object> request){
        Integer productId = (Integer) request.get("productId");
        Integer quantity = (Integer) request.get("quantity");
        Integer userId = (Integer) request.get("userId");

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(quantity);

        boolean added = userCartService.insertCart(cart);
        if (added) {
            // 获取更新后的用户信息
            JSONObject json = getJson("增加商品成功！", true);
            json.put(DATA, cart);
            return json;
        } else {
            return getJson("增加商品失败！", false);
        }
    }

    /*更新商品*/
    @PostMapping("/updateCartItem")
    public Object updateCartItem(@RequestBody Map<String, Integer> request){
        Integer id = request.get("id");
        Integer quantity = request.get("quantity");

        boolean updated = userCartService.updateCartItem(id,quantity);
        if (updated) {
            // 获取更新后的用户信息
            Cart newCart = userCartService.selectdCartItemById(id);
            JSONObject json = getJson("更新商品成功！", true);
            json.put(DATA, newCart);
            return json;
        } else {
            return getJson("更新商品失败！", false);
        }
    }

    /*更新商品*/
    @GetMapping("/findProductById")
    public Object findProductById(@RequestParam int id){
        System.out.println(id);
        Product product = productService.findProductById(id);
        JSONObject json = getJson("更新商品成功！", true);
        json.put(DATA, product);
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

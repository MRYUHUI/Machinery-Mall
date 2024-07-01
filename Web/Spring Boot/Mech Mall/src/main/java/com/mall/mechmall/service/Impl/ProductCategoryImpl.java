package com.mall.mechmall.service.Impl;

import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.mapper.ProductCategoryMapper;
import com.mall.mechmall.service.ProductCategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:商品类型管理模块
 * @Date: 2024-06-25-21:59
 * @Description:
 */
@Service
public class ProductCategoryImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public int insertProductCategory(ProductCategory productCategory){
        return productCategoryMapper.insertProductCategory(productCategory);
    }

    @Override
    public boolean updateProductCategory(ProductCategory productCategory ,int oldParentId) {
        // 1. 更新当前节点信息
        int newParentid = productCategory.getParentId();
        int parentlevel = 0; // 默认层数为0，即根节点
        ProductCategory parentProductCategory = null;

        // 更新当前节点的层数和排序号
        if (newParentid != 0) {
            parentProductCategory = productCategoryMapper.findProductCategoryById(newParentid);
            parentlevel = parentProductCategory.getLevel();
        }
        productCategory.setLevel(parentlevel + 1);

        if (oldParentId != newParentid) { // 只有在父节点发生变化时才更新更改节点的SortOrder
            int newParentChildCount = productCategoryMapper.countProductCategoryChildrens(newParentid);
            productCategory.setSortOrder(newParentChildCount+1);
        }
        //无论父节点有没有发生变化都要更新更改节点原父节点的所有孩子的SortOrder值
//        List<Integer> oldParentChildIds = productCategoryMapper.findProductCategoryChildrenIds(oldParentId);
//        int newSortOrder = 1;
//        for (Integer childId : oldParentChildIds) {
//            if(childId != productCategory.getId()){
//                ProductCategory child = productCategoryMapper.findProductCategoryById(childId);
//                child.setSortOrder(newSortOrder);
//                newSortOrder++;
//            }
//        }

        // 执行当前节点的更新
        boolean updated = productCategoryMapper.updateProductCategory(productCategory) > 0;

        // 2. 递归更新所有子节点信息
        List<Integer> childIds = productCategoryMapper.findProductCategoryChildrenIds(productCategory.getId());
        for (int childId : childIds) {
            ProductCategory child = productCategoryMapper.findProductCategoryById(childId);
            // 递归调用更新子节点
            boolean childUpdated = updateProductCategory(child , productCategory.getId());
            if (!childUpdated) {
                // 如果子节点更新失败，则当前更新也失败
                return false;
            }
        }

        return updated;
    }


    @Override
    public List<ProductCategory> findProductCategoriesByParentId(int parentId){
        return productCategoryMapper.findProductCategoriesByParentId(parentId);
    }

    @Override
    public ProductCategory findProductCategoryById(int id){
        return productCategoryMapper.findProductCategoryById(id);
    }

    @Override
    public boolean deleteProductCategory(int id){
        return productCategoryMapper.deleteProductCategory(id);
    }

    @Override
    public List<Integer> findProductCategoryChildrenIds(int parent_id){
        return productCategoryMapper.findProductCategoryChildrenIds(parent_id);
    }

    @Override
    public List<ProductCategory> findProductCategoryChildrens(int parent_id){
        return productCategoryMapper.findProductCategoryChildrens(parent_id);
    }

    //递归删除节点及其子节点
    @Override
    public boolean deleteProductCategoryAndChildren(int id){
        List<Integer> childIds = productCategoryMapper.findProductCategoryChildrenIds(id);

        for (int childId : childIds) {
            deleteProductCategoryAndChildren(childId);
        }

        return deleteProductCategory(id);
    }

    @Override
    public boolean deleteProductCategoryChildren(int parent_id){
        return productCategoryMapper.deleteProductCategoryChildren(parent_id);
    }

    @Override
    public ProductCategory findProductCategoryByParentIdAndName(int parentId, String name){
        return productCategoryMapper.findProductCategoryByParentIdAndName(parentId, name);
    }

    @Override
    public List<ProductCategory> findAllProductCategorysPage(int page, int size){
        return productCategoryMapper.findAllProductCategorysPage(page * size, size);
    }

    @Override
    public List<ProductCategory> findAllProductCategorys(){
        return productCategoryMapper.findAllProductCategorys();
    }

    @Override
    public List<ProductCategory> findAllValidityProductCategorys(int page, int size){
        return productCategoryMapper.findAllValidityProductCategorys(page * size, size);
    }

    @Override
    public List<ProductCategory> findAllInvalidityProductCategorys(int page, int size){
        return productCategoryMapper.findAllInvalidityProductCategorys(page * size, size);
    }

    @Override
    public List<ProductCategory> findAllHighestProductCategorys(int page, int size){
        return productCategoryMapper.findAllHighestProductCategorys(page * size, size);
    }

    @Override
    public int countProductCategorys(){
        return productCategoryMapper.countProductCategorys();
    }

    @Override
    public int countValidityProductCategorys(){
        return productCategoryMapper.countValidityProductCategorys();
    }

    @Override
    public int countInvalidityProductCategorys(){
        return productCategoryMapper.countInvalidityProductCategorys();
    }

    @Override
    public int countProductCategoryChildrens(int id){
        return productCategoryMapper.countProductCategoryChildrens(id);
    }

    @Override
    public List<ProductCategory> searchProductCategorys(String keyword, int page, int size){
        return productCategoryMapper.searchProductCategorys(keyword, page * size, size);
    }

    @Override
    public int countProductCategoryByKeyword(String keyword){
        return productCategoryMapper.countProductCategoryByKeyword(keyword);
    }
}

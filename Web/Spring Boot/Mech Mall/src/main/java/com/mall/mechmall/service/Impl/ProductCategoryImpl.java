package com.mall.mechmall.service.Impl;

import com.mall.mechmall.domain.ProductCategory;
import com.mall.mechmall.mapper.ProductCategoryMapper;
import com.mall.mechmall.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:商品类型管理模块
 * @Date: 2024-06-25-21:59
 * @Description:
 */
@Service
public class ProductCategoryImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    /*更新或新增节点*/
    @Override
    @Transactional
    public boolean updateProductCategory(ProductCategory productCategory, int oldParentId) {

        if(oldParentId == -1){
            return productCategoryMapper.insertProductCategory(productCategory) > 0;
        }

        // 1. 更新当前节点信息
        int newParentId = productCategory.getParentId();
        int parentLevel = 0; // 默认层数为0，即根节点
        ProductCategory parentProductCategory = null;

        // 更新当前节点的层数和排序号
        if (newParentId != 0) {
            parentProductCategory = productCategoryMapper.findProductCategoryById(newParentId);
            parentLevel = parentProductCategory.getLevel();
        }
        productCategory.setLevel(parentLevel + 1);

        // 如果父节点发生变化，需要更新排序号
        if (oldParentId != newParentId) {
            // 更新新的父节点下的排序号
            int newParentChildCount = productCategoryMapper.countProductCategoryChildrens(newParentId);
            productCategory.setSortOrder(newParentChildCount + 1);

            // 更新原父节点的所有孩子的SortOrder值
            List<ProductCategory> children = productCategoryMapper.findProductCategoriesByParentId(oldParentId);
            // 滤掉 id 等于 移走节点id 的项
            children = children.stream()
                    .filter(child -> child.getId() != productCategory.getId())
                    .collect(Collectors.toList());
            // 对子节点按照 sortOrder 进行排序
            children.sort(Comparator.comparingInt(ProductCategory::getSortOrder));
            // 更新子节点的 sortOrder
            int sortOrder = 1;
            for (ProductCategory child : children) {
                int result = productCategoryMapper.updateChildSortOrder(child.getId(), sortOrder);
                if (result == 0) {
                    throw new RuntimeException("Failed to update sort order for child with id: " + child.getId());
                }
                sortOrder++;
            }
        }

        // 执行当前节点的更新
        boolean updated = productCategoryMapper.updateProductCategory(productCategory) > 0;
        if (!updated) {
            return false;
        }

        // 2. 递归更新所有子节点信息
        List<Integer> childIds = productCategoryMapper.findProductCategoryChildrenIds(productCategory.getId());
        for (int childId : childIds) {
            ProductCategory child = productCategoryMapper.findProductCategoryById(childId);
            // 更新子节点的状态为当前节点的状态
            child.setStatus(productCategory.getStatus());
            // 递归调用更新子节点
            boolean childUpdated = updateProductCategory(child, productCategory.getId());
            if (!childUpdated) {
                // 如果子节点更新失败，则当前更新也失败
                return false;
            }
        }

        return true;
    }

    /*通过ID查询节点*/
    @Override
    public ProductCategory findProductCategoryById(int id){
        return productCategoryMapper.findProductCategoryById(id);
    }

    // 2024.7.4
    @Override
    public List<ProductCategory> findProductCategoriesByParentId(int id) {
        return productCategoryMapper.findProductCategoriesByParentId(id);
    }

    @Override
    public List<ProductCategory> findProductCategoryByParentId(int id){
        return productCategoryMapper.findProductCategoriesByParentId(id);
    }
    /*通过name查询节点*/
    @Override
    public ProductCategory findProductCategoryByName(String name){
        return productCategoryMapper.findProductCategoryByName(name);
    }

    /*删除单个节点*/
    @Override
    public boolean deleteProductCategory(int id , int parentId){
        ProductCategory productCategory = productCategoryMapper.findProductCategoryById(id);
        // 有子分类不能删除，不能与递归删除共用
        // 状态为0不能删除
        if(productCategoryMapper.countProductCategoryChildrens(id) != 0 || !productCategory.getStatus()){
          return false;
        }
        // 更新原父节点的所有孩子的SortOrder值
        List<ProductCategory> children = productCategoryMapper.findProductCategoriesByParentId(parentId);
        // 滤掉 id 等于 移走节点id 的项
        children = children.stream()
                .filter(child -> child.getId() != id)
                .collect(Collectors.toList());
        // 对子节点按照 sortOrder 进行排序
        children.sort(Comparator.comparingInt(ProductCategory::getSortOrder));
        // 更新子节点的 sortOrder
        int sortOrder = 1;
        for (ProductCategory child : children) {
            int result = productCategoryMapper.updateChildSortOrder(child.getId(), sortOrder);
            if (result == 0) {
                throw new RuntimeException("Failed to update sort order for child with id: " + child.getId());
            }
            sortOrder++;
        }
        return productCategoryMapper.deleteProductCategory(id);
    }

    /*查询子节点*/
    @Override
    public List<ProductCategory> findProductCategoryChildrens(int parent_id,int page, int size){
        return productCategoryMapper.findProductCategoryChildrens(parent_id,(page - 1) * size, size);
    }

    /*递归删除节点及其子节点*/
//    @Override
//    public boolean deleteProductCategoryAndChildren(int id, int parentId) {
//        List<Integer> childIds = productCategoryMapper.findProductCategoryChildrenIds(id);
//        for (int childId : childIds) {
//            deleteProductCategoryAndChildren(childId, id);
//        }
//        return deleteProductCategory(id,parentId);
//    }

    /*返回所有节点*/
    @Override
    public List<ProductCategory> findAllProductCategorys(){
        return productCategoryMapper.findAllProductCategorys();
    }

    /*查询所有顶级节点*/
    @Override
    public List<ProductCategory> findAllHighestProductCategorys(int page, int size){
        return productCategoryMapper.findAllHighestProductCategorys((page - 1) * size, size);
    }

    /*返回所有节点的数量*/
    @Override
    public int countProductCategorys(){
        return productCategoryMapper.countProductCategorys();
    }

    /*返回子节点数量*/
    @Override
    public int countProductCategoryChildrens(int id){
        return productCategoryMapper.countProductCategoryChildrens(id);
    }

    /*按关键字分页查询节点*/
    @Override
    public List<ProductCategory> searchProductCategorys(String keyword, int page, int size){
        return productCategoryMapper.searchProductCategorys(keyword, (page - 1) * size, size);
    }

    /*返回有关键字节点数量*/
    @Override
    public int countProductCategoryByKeyword(String keyword){
        return productCategoryMapper.countProductCategoryByKeyword(keyword);
    }

    // 2024.7.4
    @Override
    public List<ProductCategory> findAllHighestProductCategories() {
        return productCategoryMapper.findAllHighestProductCategories();
    }


    @Override
    public List<ProductCategory> getProductCategories(Integer parentId){
        return productCategoryMapper.getProductCategories(parentId);
    }
}

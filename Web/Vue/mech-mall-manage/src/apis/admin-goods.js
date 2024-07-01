import httpInstance from "@/utils/http";

const adminGoodsApi = {
    //获取用户信息
    getAllProduct: (page, size) => httpInstance.get('/product/allProduct',{
        params: {
            page: page,
            size: size
        }
    }),
    //修改产品状态
    updateStatus: (product) => httpInstance.post('/admin/product/updateStatus',product),
     
    //增加or修改产品状态
    saveProduct: (product) => httpInstance.post('/admin/product/saveProduct',product),
    //删除产品
    deleteProduct: (ProductId) => httpInstance.get('/admin/product/deleteProduct',{
        params: {
            id: ProductId
        }
    }),
    //查找产品
    searchProduct: (query, page, size) =>httpInstance.get('/admin/product/searchProduct',{
        params: {
            keyword: query,
            page: page,
            size: size
        }
    })


};
export default adminGoodsApi;

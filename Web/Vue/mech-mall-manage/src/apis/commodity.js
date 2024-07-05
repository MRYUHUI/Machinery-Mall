import httpInstance from "@/utils/http";

const commodityApi = {
	// 获取所有热门商品
	findAllHotCommodity: () => httpInstance.get("/commodity/home/hot/all"),
	// 获取相应数量的热门商品
	findAllHotCommodityLimit: (limit) => httpInstance.get("/commodity/home/hot/limit", { params: { limit } }),
	// 获取一级分类的所有商品
	findAllProductOrderByHighestCategory: (limit) => httpInstance.get('/commodity/home/product/highest/category', { params: { limit } }),
	// 根据 parentId 获取下一级分类
	findCategoriesByParentId: (parentId) => httpInstance.get('/commodity/home/find/sub/category', { params: { parentId } }),
	// 根据 partsId 获取商品
	findAllProductsByPartsId: (id) => httpInstance.get('/commodity/home/find/products/third-id', { params: { id } }),
	// 根据 ProductId 获取商品
	findProductsByProductId: (id) => httpInstance.get('/commodity/home/product/highest/category/single', { params: { id } }),
	// 获取轮播图
	findAllCarousel: () => httpInstance.get('/carousel/all')

};
export default commodityApi;

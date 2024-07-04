import httpInstance from "@/utils/http";

const commodityApi = {
	findAllHotCommodity: () => httpInstance.get("/commodity/home/hot/all"),
	findAllHotCommodityLimit: (limit) => httpInstance.get("/commodity/home/hot/limit", { params: { limit } }),
	findAllProductOrderByHighestCategory: (limit) => httpInstance.get('/commodity/home/product/highest/category', { params: { limit } }),
	findCategoriesByParentId: (parentId) => httpInstance.get('/commodity/home/find/sub/category', { params: { parentId } }),
	findAllProductsByPartsId: (id) => httpInstance.get('/commodity/home/find/products/third-id', { params: { id } })

};
export default commodityApi;

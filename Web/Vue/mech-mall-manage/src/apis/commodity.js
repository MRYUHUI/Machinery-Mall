import httpInstance from "@/utils/http";

const commodityApi = {
	findAllHotCommodity: () => httpInstance.get("commodity/home/hot/all"),
	findAllHotCommodityLimit: (limit) => httpInstance.get("commodity/home/hot/limit", { params: { limit } })
};
export default commodityApi;

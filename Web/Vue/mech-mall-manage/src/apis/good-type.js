import httpInstance from "@/utils/http";

const GoodTypeApi = {
	getProductCategories: (parentId) => httpInstance.get('/commodity/home/productCategory/getProductCategories', {
		params: {
			parentId: parentId
		}
	}),

};
export default GoodTypeApi;

import httpInstance from "@/utils/http";

const GoodTypeApi = {
	getProductCategories: (parentId) => httpInstance.get('/user/productCategory/getProductCategories', {
		params: {
			parentId: parentId
		}
	}),

};
export default GoodTypeApi;

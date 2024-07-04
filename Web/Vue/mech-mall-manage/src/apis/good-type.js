import httpInstance from "@/utils/http";

const GoodTypeApi = {
	getProductCategories: (parentId) => httpInstance.get('/productCategory/getProductCategories',{
		params: {
			parentId: parentId
		}
	}),

};
export default GoodTypeApi;

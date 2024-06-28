import httpInstance from "@/utils/http";

const adminGoodTypeApi = {
	getAllAdminGoodTypes: (page, size) => httpInstance.get('/productCategory/allProductCategorys', {
		params: {
			page: page,
			size: size
		}
	}),
};
export default adminGoodTypeApi;

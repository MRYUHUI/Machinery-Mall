import httpInstance from "@/utils/http";

const adminGoodTypeApi = {
	allProductCategorys: (page, size) => httpInstance.get('/admin/productCategory/allProductCategorys', {
		params: {
			page: page,
			size: size
		}
	}),
	allHighestProductCategorys: (page, size) => httpInstance.get('/admin/productCategory/allHighestProductCategorys', {
		params: {
			page: page,
			size: size
		}
	}),
	allProductCategoryChildrens: (ParentId) => httpInstance.post('/admin/productCategory/allProductCategoryChildrens', {ParentId}),
	deleteAdminGoodType: (adminGoodTypeid) => httpInstance.get('/admin/productCategory/deleteAdminGoodType', {
		params: { 
			id: adminGoodTypeid 
		}
	}),
	updateAdminGoodTypeInfo: (adminGoodType, oldParentId) => httpInstance.post('/admin/productCategory/updateProductCategoryInfo',adminGoodType, {
		params: { 
			oldParentId: oldParentId
		}
	}),
	searchAdminGoodTypes: (query, page, size) => httpInstance.get('/admin/productCategory/searchProductCategorys', {
		params: {
			keyword: query,
			page: page,
			size: size
		}
	}),
	findAllValidityProductCategorys: (page, size) => httpInstance.get('/admin/productCategory/findAllValidityProductCategorys', {
		params: {
			page: page,
			size: size
		}
	}),
};
export default adminGoodTypeApi;

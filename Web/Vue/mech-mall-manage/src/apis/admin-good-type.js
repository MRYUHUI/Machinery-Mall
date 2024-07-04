import httpInstance from "@/utils/http";

const adminGoodTypeApi = {
	allProductCategorys: () => httpInstance.get('/admin/productCategory/allProductCategorys'),
	allHighestProductCategorys: (page, size) => httpInstance.get('/admin/productCategory/allHighestProductCategorys', {
		params: {
			page: page,
			size: size
		}
	}),
	allProductCategoryChildrens: (ParentId, page, size) => httpInstance.post('/admin/productCategory/allProductCategoryChildrens', { ParentId, page, size }),
	deleteAdminGoodType: (adminGoodTypeid) => httpInstance.post('/admin/productCategory/deleteAdminGoodType', {
		id: adminGoodTypeid
	}),
	updateAdminGoodTypeInfo: (adminGoodType, oldParentId) => httpInstance.post('/admin/productCategory/updateProductCategoryInfo', adminGoodType, {
		params: {
			oldParentId: oldParentId
		}
	}),
	addAdminGoodTypeInfo: (name, parentId) => httpInstance.post('/admin/productCategory/insertProductCategory', {
		name: name,
		parentId: parentId
	}),
	searchAdminGoodTypes: (query, page, size) => httpInstance.get('/admin/productCategory/searchProductCategorys', {
		params: {
			keyword: query,
			page: page,
			size: size
		}
	}),
	fetchProductCategory: (id) => httpInstance.get('/admin/productCategory/parents', {
		params: {
			id: id
		}
	})

};
export default adminGoodTypeApi;

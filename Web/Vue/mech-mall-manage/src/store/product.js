export default {
	state: {
		id: "", // 产品ID
		name: "", // 产品名
		editProductInfoDiaVisible: false, // 是否显示编辑用户信息对话框
		isAdminProductFresh: false, // 管理用户信息是否要刷新
		addProductInfoDiaVisible: false,//是否显示增加用户对话框
		selectedProductInfo: {
			id: '',
			name: '',
			productId: '',
			partsId: '',
			iconUrl: '',
			sunImages: '',
			specParam: '',
			price: '',
			stock: '',
			status: '',
			isHot: '',
			created: '',
			updated: '',
		},
	},
	getters: {
		id: (state) => state.id,
		name: (state) => state.name,
		selectedProductInfo: (state) => state.selectedProductInfo,
		editProductInfoDiaVisible: (state) => state.editProductInfoDiaVisible,
		isAdminProductFresh: (state) => state.isAdminProductFresh,
		addProductInfoDiaVisible: (state) => state.addProductInfoDiaVisible,
		stock: (state) => state.selectedProductInfo.stock
	},
	mutations: {
		setid: (state, id) => {
			state.id = id;
		},
		setName: (state, name) => {
			state.name = name;
		},
		// 更新整个 SelectedProductInfo 
		updateSelectedProductInfo: (state, selectedProductInfo) => {
			state.selectedProductInfo = selectedProductInfo;
		},
		// 更新 SelectedProductInfo  单个字段
		updateSelectedProductInfoField: (state, { field, value }) => {
			if (state.selectedProductInfo.hasOwnProperty(field)) {
				state.selectedProductInfo[field] = value
			}
		},
		setEditProductInfoDiaVisible: (state, editProductInfoDiaVisible) => {
			state.editProductInfoDiaVisible = editProductInfoDiaVisible
		},
		setIsAdminProductFresh: (state, isAdminProductFresh) => {
			state.isAdminProductFresh = isAdminProductFresh
		},
		setAddProductInfoDiaVisible: (state, addProductInfoDiaVisible) => {
			state.addProductInfoDiaVisible = addProductInfoDiaVisible
			console.log('Updating addProductInfoDiaVisible:', addProductInfoDiaVisible);
		},
		setStock: (state, stock) => {
			state.selectedProductInfo.stock = stock
		}


	},
	actions: {
		saveIdAndName ({ commit }, ProductInfo) {
			commit('setProductid', ProductInfo.id);
			commit('setName', ProductInfo.Name);
		},
		// 更新整个 SelectedProductInfo  
		updateSelectedProductInfo ({ commit }, selectedProductInfo) {
			commit('updateSelectedProductInfo', selectedProductInfo);
		},
		// 更新 SelectedProductInfo  单个字段
		updateSelectedProductInfoField ({ commit }, { field, value }) {
			commit('updateSelectedProductInfoField', { field, value });
		}

	}
};

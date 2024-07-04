export default {
	state: {
		adminGoodTypeId: "", // 分类ID
		adminGoodTypeParentId:"", // 父节点ID
		previousNodeParentList: [],
		selectedAdminGoodTypeInfo: { // 被选中的分类信息
			id: '',
			parent_id: '',
			name: '',
			sort_order: '',
			status: '',
			level: '',
			created: '',
			updated: ''
		},
		editAdminGoodTypeInfoDiaVisible: false, // 是否显示编辑分类信息对话框
		addAdminGoodTypeInfoDiaVisible: false, // 是否显示添加分类信息对话框
		isAdminGoodTypeFresh: false, // 管理分类信息是否要刷新
	},
	getters: {
		adminGoodTypeId: (state) => state.adminGoodTypeId,
		adminGoodTypeParentId: (state) => state.adminGoodTypeParentId,
		previousNodeParentList: (state) => state.previousNodeParentList,
		selectedAdminGoodTypeInfo: (state) => state.selectedAdminGoodTypeInfo,
		editAdminGoodTypeInfoDiaVisible: (state) => state.editAdminGoodTypeInfoDiaVisible,
		addAdminGoodTypeInfoDiaVisible: (state) => state.addAdminGoodTypeInfoDiaVisible,
		isAdminGoodTypeFresh: (state) => state.isAdminGoodTypeFresh,
	},
	mutations: {
		setAdminGoodTypeId: (state, adminGoodTypeId) => {
			state.adminGoodTypeId = adminGoodTypeId;
		},
		setAdminGoodTypeParentId: (state, adminGoodTypeParentId) => {
			state.adminGoodTypeParentId = adminGoodTypeParentId;
		},
		setPreviousNodeParentList: (state, parentId) => {
			state.previousNodeParentList.push(parentId);
		},
		newPreviousNodeParentList: (state, newList) => {
			state.previousNodeParentList = newList ;
		},
		// 更新整个 SelectedAdminGoodTypeInfo 
		updateSelectedAdminGoodTypeInfo: (state, selectedAdminGoodTypeInfo) => {
			state.selectedAdminGoodTypeInfo = selectedAdminGoodTypeInfo;
		},
		// 更新 adminGoodTypeLevel 
		updateAdminGoodTypeParentId (state, adminGoodTypeParentId) {
			state.adminGoodTypeParentId = adminGoodTypeParentId;
		},
		setEditAdminGoodTypeInfoDiaVisible: (state, editAdminGoodTypeInfoDiaVisible) => {
			state.editAdminGoodTypeInfoDiaVisible = editAdminGoodTypeInfoDiaVisible
			// console.log('js editAdminGoodTypeInfoDiaVisible = ' + editAdminGoodTypeInfoDiaVisible)
		},
		setAddAdminGoodTypeInfoDiaVisible: (state, addAdminGoodTypeInfoDiaVisible) => {
			state.addAdminGoodTypeInfoDiaVisible = addAdminGoodTypeInfoDiaVisible
			// console.log('js addAdminGoodTypeInfoDiaVisible = ' + addAdminGoodTypeInfoDiaVisible)
		},
		setIsAdminGoodTypeFresh: (state, isAdminGoodTypeFresh) => {
			state.isAdminGoodTypeFresh = isAdminGoodTypeFresh
		}
	},
    actions: {
        // 更新整个 selectedAdminGoodTypeInfo 
		updateSelectedAdminGoodTypeInfo ({ commit }, selectedAdminGoodTypeInfo) {
			commit('updateSelectedAdminGoodTypeInfo', selectedAdminGoodTypeInfo);
		},
        // 更新 adminGoodTypeLevel 
		updateAdminGoodTypeParentId ({ commit }, adminGoodTypeParentId) {
			commit('updateAdminGoodTypeParentId', adminGoodTypeParentId);
		}
	}
};

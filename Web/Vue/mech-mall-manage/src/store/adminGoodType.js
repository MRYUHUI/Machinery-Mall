export default {
	state: {
		adminGoodTypeId: "", // 分类ID
		adminGoodTypeName: "", // 分类名
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
		isAdminGoodTypeFresh: false, // 管理分类信息是否要刷新
	},
	getters: {
		adminGoodTypeId: (state) => state.adminGoodTypeId,
		adminGoodTypeName: (state) => state.adminGoodTypeName,
		previousNodeParentList: (state) => state.previousNodeParentList,
		selectedAdminGoodTypeInfo: (state) => state.selectedAdminGoodTypeInfo,
		editAdminGoodTypeInfoDiaVisible: (state) => state.editAdminGoodTypeInfoDiaVisible,
		isAdminGoodTypeFresh: (state) => state.isAdminGoodTypeFresh,
	},
	mutations: {
		setAdminGoodTypeId: (state, adminGoodTypeId) => {
			state.adminGoodTypeId = adminGoodTypeId;
		},
		setAdminGoodTypeName: (state, adminGoodTypeName) => {
			state.adminGoodTypeName = adminGoodTypeName;
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
		setEditAdminGoodTypeInfoDiaVisible: (state, editAdminGoodTypeInfoDiaVisible) => {
			state.editAdminGoodTypeInfoDiaVisible = editAdminGoodTypeInfoDiaVisible
			// console.log('js editAdminGoodTypeInfoDiaVisible = ' + editAdminGoodTypeInfoDiaVisible)
		},
		setIsAdminGoodTypeFresh: (state, isAdminGoodTypeFresh) => {
			state.isAdminGoodTypeFresh = isAdminGoodTypeFresh
		}
	},
    actions: {
        // 更新整个 selectedAdminGoodTypeInfo 
		updateSelectedAdminGoodTypeInfo ({ commit }, selectedAdminGoodTypeInfo) {
			commit('updateSelectedAdminGoodTypeInfo', selectedAdminGoodTypeInfo);
		}
	}
};

export default {
	state: {
		// 展示的页面index值（用户管理、商品管理、类型管理、订单管理），默认用户管理
		activePageIndex: 1,
		editUserInfoDiaVisible: false, // 是否显示编辑用户信息对话框
		isAdminUserFresh: false, // 管理用户信息是否要刷新
	},
	getters: {
		activePageIndex: (state) => state.activePageIndex,
		editUserInfoDiaVisible: (state) => state.editUserInfoDiaVisible,
		isAdminUserFresh: (state) => state.isAdminUserFresh,
	},
	mutations: {
		setActivePageIndex: (state, activePageIndex) => {
			state.activePageIndex = activePageIndex;
		},
		setEditUserInfoDiaVisible: (state, editUserInfoDiaVisible) => {
			state.editUserInfoDiaVisible = editUserInfoDiaVisible
		},
		setIsAdminUserFresh: (state, isAdminUserFresh) => {
			state.isAdminUserFresh = isAdminUserFresh
		}
	},
};

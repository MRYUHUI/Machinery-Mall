export default {
	state: {
		// 展示的页面index值（用户管理、商品管理、类型管理、订单管理），默认用户管理
		activePageIndex: 1,
	},
	getters: {
		activePageIndex: (state) => state.activePageIndex,
	},
	mutations: {
		setActivePageIndex: (state, activePageIndex) => {
			state.activePageIndex = activePageIndex;
		},
	},
};

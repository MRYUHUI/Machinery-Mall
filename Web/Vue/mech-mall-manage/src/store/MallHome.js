export default {
	state: {
		// 展示的页面index值（我的订单、我的购物车、收货地址管理），默认用户管理
		activeMyMallHomeIndex: 1,
		searchQuery: '',
		secondLevelNode: 4,
		thirdLevelNode: -1,
		rootNode: 1
	},
	getters: {
		activeMyMallHomeIndex: (state) => state.activeMyMallHomeIndex,
		searchQuery: (state) => state.searchQuery,
		secondLevelNode: (state) => state.secondLevelNode,
		thirdLevelNode: (state) => state.thirdLevelNode,
		rootNode: (state) => state.rootNode,
	},
	mutations: {
		setActiveMyMallHomeIndex: (state, activeMyMallHomeIndex) => {
			state.activeMyMallHomeIndex = activeMyMallHomeIndex;
		},
		setSearchQuery: (state, searchQuery) => {
			state.searchQuery = searchQuery;
		},
		setSecondLevelNode: (state, secondLevelNode) => {
			state.secondLevelNode = secondLevelNode;
		},
		setThirdLevelNode: (state, thirdLevelNode) => {
			state.thirdLevelNode = thirdLevelNode;
		},
		setRootNode: (state, rootNode) => {
			state.rootNode = rootNode;
		},
	},
};

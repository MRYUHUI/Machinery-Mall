export default {
	state: {
		// 展示的页面index值（我的订单、我的购物车、收货地址管理），默认用户管理
		activeMyMallHomeIndex: 1,
		secondLevelNode: 4,
		thirdLevelNode: 7,
		rootNode: 1
	},
	getters: {
		activeMyMallHomeIndex: (state) => state.activeMyMallHomeIndex,
		secondLevelNode: (state) => state.secondLevelNode,
		thirdLevelNode: (state) => state.thirdLevelNode,
		rootNode: (state) => state.rootNode,
	},
	mutations: {
		setActiveMyMallHomeIndex: (state, activeMyMallHomeIndex) => {
			state.activeMyMallHomeIndex = activeMyMallHomeIndex;
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


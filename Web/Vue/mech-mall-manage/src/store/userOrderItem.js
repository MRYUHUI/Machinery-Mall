export default {
	state: {
		detailOrderItemDInfoiaVisible: false,
		selectedUserOrderItemInfo: {
			id: '',
			uid: '',
			orderId: '',
			goodsId: '',
			goodsName: '',
			iconUrl: '',
			price: '',
			quantity: '',
			totalPrice: '',
			created: '',
			updated: '',
		}
	},
	getters: {
		detailOrderItemDInfoiaVisible: (state) => state.detailOrderItemDInfoiaVisible,
		selectedUserOrderItemInfo: (state) => state.selectedUserOrderItemInfo,
	},
	mutations: {
		setDetailOrderItemDInfoiaVisible: (state, detailOrderItemDInfoiaVisible) => {
			state.detailOrderItemDInfoiaVisible = detailOrderItemDInfoiaVisible
		},
		setSelectedUserOrderItemInfo: (state, selectedUserOrderItemInfo) => {
			state.selectedUserOrderItemInfo = selectedUserOrderItemInfo;
		}

	},
	actions: {

	}

};
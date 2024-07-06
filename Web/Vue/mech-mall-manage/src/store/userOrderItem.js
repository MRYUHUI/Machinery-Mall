export default {
	state: {
		id: "",
		uid: "",
		detailOrderItemDInfoiaVisible: false,
		//+++++++
		adminDetailOrderItemDInfoiaVisible: false,
		selectedUserOrderItemInfo: {
			id: null,
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
		id: (state) => state.id,
		uid: (state) => state.uid,
		detailOrderItemDInfoiaVisible: (state) => state.detailOrderItemDInfoiaVisible,
		selectedUserOrderItemInfo: (state) => state.selectedUserOrderItemInfo,
		//++++++++++++
		adminDetailOrderItemDInfoiaVisible: (state) => state.adminDetailOrderItemDInfoiaVisible,
	},
	mutations: {
		setId: (state, id) => {
			state.id = id;
		},
		setUid: (state, uid) => {
			state.uid = uid;
		},
		setDetailOrderItemDInfoiaVisible: (state, detailOrderItemDInfoiaVisible) => {
			state.detailOrderItemDInfoiaVisible = detailOrderItemDInfoiaVisible
		},
		setSelectedUserOrderItemInfo: (state, selectedUserOrderItemInfo) => {
			state.selectedUserOrderItemInfo = selectedUserOrderItemInfo;
		},

		//+++++
		setAdminDetailOrderItemDInfoiaVisible: (state, adminDetailOrderItemDInfoiaVisible) => {
			state.adminDetailOrderItemDInfoiaVisible = adminDetailOrderItemDInfoiaVisible
		}

	},
	actions: {

	}

};
export default {
	state: {
		selectedUserOrderInfo: {
			id: '',
			orderNo: '',
			uid: '',
			addrId: '',
			amount: '',
			type: '',
			freight: '',
			status: '',
			paymentTime: '',
			deliveryTime: '',
			finishTime: '',
			closeTime: '',
			created: '',
			updated: '',
		}
	},
	getters: {
		selectedUserOrderInfo: (state) => state.selectedUserOrderInfo,
	},
	mutations: {
		setSelectedUserOrderInfo: (state, selectedUserOrderInfo) => {
			state.selectedUserOrderInfo = selectedUserOrderInfo;
		}

	},
	actions: {

	}

};
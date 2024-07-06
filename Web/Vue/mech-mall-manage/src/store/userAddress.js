export default {

	state: {
		addUserAddressInfoDiaVisible: false,
		selectedUserAddressInfo: {
			id: '',
			userId: '',
			name: '',
			phone: '',
			mobile: '',
			province: '',
			city: '',
			district: '',
			addr: '',
			zip: '',
			dfault: '',
			isDel: '',
			createTime: '',
			updateTime: '',
		},
		isUserAddrPageFresh: false,
	},
	getters: {
		addUserAddressInfoDiaVisible: (state) => state.addUserAddressInfoDiaVisible,
		selectedUserAddressInfo: (state) => state.selectedUserAddressInfo,
		isUserAddrPageFresh: (state) => state.isUserAddrPageFresh,
	},
	mutations: {
		setAddUserAddressInfoDiaVisible: (state, addUserAddressInfoDiaVisible) => {
			state.addUserAddressInfoDiaVisible = addUserAddressInfoDiaVisible
		},
		setSelectedUserAddressInfo: (state, selectedUserAddressInfo) => {
			state.selectedUserAddressInfo = selectedUserAddressInfo;
		},
		setIsUserAddrPageFresh: (state, isUserAddrPageFresh) => {
			state.isUserAddrPageFresh = isUserAddrPageFresh
		}
	},
	actions: {

		saveIdAndUSerId ({ commit }, UserAddressInfo) {
			commit('setId', UserAddressInfo.id);
			commit('setUserId', UserAddressInfo.userId);
		}
	}

};
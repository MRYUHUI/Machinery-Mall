export default {
	state: {
		userId: "", // 用户ID
		account: "", // 用户名
		selectedUserInfo: { // 被选中的用户的信息，如：管理员编辑用户
			id: '',
			account: '',
			password: '',
			email: '',
			phone: '',
			question: '',
			asw: '',
			role: '',
			age: '',
			sex: '',
			create_time: '',
			update_time: '',
			del: '',
			name: ''
		}
	},
	getters: {
		userId: (state) => state.userId,
		account: (state) => state.account,
		selectedUserInfo: (state) => state.selectedUserInfo,
	},
	mutations: {
		setUserId: (state, userId) => {
			state.userId = userId;
		},
		setAccount: (state, account) => {
			state.account = account;
		},
		// 更新整个 selectedUserInfo 
		updateSelectedUserInfo: (state, selectedUserInfo) => {
			state.selectedUserInfo = selectedUserInfo;
		},
		// 更新 selectedUserInfo 单个字段
		updateSelectedUserInfoField: (state, { field, value }) => {
			if (state.selectedUserInfo.hasOwnProperty(field)) {
				state.selectedUserInfo[field] = value;
			}
		}
	},
	actions: {
		saveIdAndAccount ({ commit }, userInfo) {
			commit('setUserId', userInfo.userId);
			commit('setAccount', userInfo.account);
		},
		// 更新整个 selectedUserInfo 
		updateSelectedUserInfo ({ commit }, selectedUserInfo) {
			commit('updateSelectedUserInfo', selectedUserInfo);
		},
		// 更新 selectedUserInfo 单个字段
		updateSelectedUserInfoField ({ commit }, { field, value }) {
			commit('updateSelectedUserInfoField', { field, value });
		}
	}
};

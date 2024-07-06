export default {
	state: {
		userId: "", // 用户ID
		account: "", // 用户名
		isLogin: false, // 是否登录
		role: 1,
		curUserInfo: {
			id: null,
			account: null,
			password: null,
			email: null,
			phone: null,
			question: null,
			asw: null,
			role: null,
			age: null,
			sex: null,
			createTime: null,
			updateTime: null,
			del: null,
			name: null
		},
		selectedUserInfo: {
			id: null,
			account: null,
			password: null,
			email: null,
			phone: null,
			question: null,
			asw: null,
			role: null,
			age: null,
			sex: null,
			createTime: null,
			updateTime: null,
			del: null,
			name: null
		}// 被选中的用户的信息，如：管理员编辑用户
	},
	changePwdPageVisible: false, // 修改密码对话框

	getters: {
		userId: (state) => state.userId,
		account: (state) => state.account,
		isLogin: (state) => state.isLogin,
		role: (state) => state.role,
		selectedUserInfo: (state) => state.selectedUserInfo,
		curUserInfo: (state) => state.curUserInfo,
		changePwdPageVisible: (state) => state.changePwdPageVisible
	},
	mutations: {
		setUserId: (state, userId) => {
			state.userId = userId;
		},
		setAccount: (state, account) => {
			state.account = account;
		},
		setIsLogin: (state, isLogin) => {
			state.isLogin = isLogin
		},
		setRole: (state, role) => {
			state.role = role
		},
		setCurUserInfo: (state, curUserInfo) => {
			state.curUserInfo = curUserInfo
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
		},
		// 重置 curUserInfo
		resetCurUserInfo: (state) => {
			state.curUserInfo = {
				id: null,
				account: null,
				password: null,
				email: null,
				phone: null,
				question: null,
				asw: null,
				role: null,
				age: null,
				sex: null,
				createTime: null,
				updateTime: null,
				del: null,
				name: null
			};
		},
		// 重置 selectedUserInfo
		resetSelectedUserInfo: (state) => {
			state.selectedUserInfo = {
				id: null,
				account: null,
				password: null,
				email: null,
				phone: null,
				question: null,
				asw: null,
				role: null,
				age: null,
				sex: null,
				createTime: null,
				updateTime: null,
				del: null,
				name: null
			};
		},
		setChangePwdPageVisible: (state, changePwdPageVisible) => {
			state.changePwdPageVisible = changePwdPageVisible
		}
	},
	actions: {
		saveIdAndAccount ({ commit }, userInfo) {
			commit('setUserId', userInfo.userId);
			commit('setAccount', userInfo.account);
			commit('setRole', userInfo.role)
			commit('setIsLogin', true)
		},
		// 更新整个 selectedUserInfo 
		updateSelectedUserInfo ({ commit }, selectedUserInfo) {
			commit('updateSelectedUserInfo', selectedUserInfo);
		},
		// 更新 selectedUserInfo 单个字段
		updateSelectedUserInfoField ({ commit }, { field, value }) {
			commit('updateSelectedUserInfoField', { field, value });
		},
		// 重置 selectedUserInfo
		resetSelectedUserInfo ({ commit }) {
			commit('resetSelectedUserInfo');
		}
	}
};

export default {
	state: {
		userId: "", // 用户ID
		account: "", // 用户名
		email: "", // 邮箱
		role: "", // 角色
		sex: "", // 性别
	},
	getters: {
		userId: (state) => state.userId,
		account: (state) => state.account,
		email: (state) => state.email,
		role: (state) => state.role,
		sex: (state) => state.sex,
	},
	mutations: {
		setUserId: (state, userId) => {
			state.userId = userId;
		},
		setAccount: (state, account) => {
			state.account = account;
		},
		setEmail: (state, email) => {
			state.email = email;
		},
		setRole: (state, role) => {
			state.role = role;
		},
		setSex: (state, sex) => {
			state.sex = sex;
		},
	},
	actions: {
		saveUserInfo ({ commit }, userInfo) {
			commit('setUserId', userInfo.userId);
			commit('setAccount', userInfo.account);
			commit('setEmail', userInfo.email);
			commit('setRole', userInfo.role);
			commit('setSex', userInfo.sex);
		},
	}
};

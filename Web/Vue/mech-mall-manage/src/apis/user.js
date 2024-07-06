import httpInstance from "@/utils/http";

const userApi = {
	signUp: (user) => httpInstance.post('/user/auth/do_register.do', user),
	signIn: (user) => httpInstance.post('/user/auth/do_login.do', user),
	findUserByAccount: (account) => httpInstance.get(`/user/auth/find/account`, { params: { account } }),
	updateUserPassword: (user) => httpInstance.post('/user/auth/update/password', user),
	// 修改用户信息
	editUserInfo: (user) => httpInstance.post('/user/manage/update', user),
};
export default userApi;

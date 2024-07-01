import httpInstance from "@/utils/http";

const adminUserApi = {
	getAllUsers: (page, size) => httpInstance.get('/admin/user/allUsers', {
		params: {
			page: page,
			size: size
		}
	}),
	// 修改用户信息
	updateUserInfo: (user) => httpInstance.post('/admin/user/updateUserInfo', user),

	deleteUser: (userId) => httpInstance.get('/admin/user/deleteUser', { params: { id: userId } }),
	searchUsers: (query, page, size) => httpInstance.get('/admin/user/searchUsers', {
		params: {
			keyword: query,
			page: page,
			size: size
		}
	})

};
export default adminUserApi;

import httpInstance from "@/utils/http";

const adminApi = {
	getAllUsers: (page, size) => httpInstance.get('/admin/allUsers', {
		params: {
			page: page,
			size: size
		}
	}),
	// 修改用户信息
	updateUserInfo: (user) => httpInstance.post('/admin/updateUserInfo', user),

	deleteUser: (userId) => httpInstance.get('/admin/deleteUser', { params: { id: userId } }),
	searchUsers: (query, page, size) => httpInstance.get('/admin/searchUsers', {
		params: {
			keyword: query,
			page: page,
			size: size
		}
	})

};
export default adminApi;

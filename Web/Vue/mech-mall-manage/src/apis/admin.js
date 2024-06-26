import httpInstance from "@/utils/http";

const adminApi = {
	getAllUsers: () => httpInstance.get('/admin/allUsers'),

};
export default adminApi;

import httpInstance from "@/utils/http";

const userApi = {
	signUp: (user) => httpInstance.post('/mgr/user/register.do', user),
};
export default userApi;

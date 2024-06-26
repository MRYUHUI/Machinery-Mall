import httpInstance from "@/utils/http";

const userApi = {
	signUp: (user) => httpInstance.post('/user/do_register.do', user),
	signIn: (user) => httpInstance.post('/user/do_login.do', user),

};
export default userApi;

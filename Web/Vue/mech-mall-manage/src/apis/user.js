import httpInstance from "@/utils/http";

const userApi = {
	signUp: (user) => httpInstance.post('/user/auth/do_register.do', user),
	signIn: (user) => httpInstance.post('/user/auth/do_login.do', user),

};
export default userApi;

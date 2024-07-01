// axios 基础封装
import axios from "axios";
import { SystemConsts } from "@/enums/SystemConsts";
import router from "@/router"; // 导入Vue Router实例

const httpInstance = axios.create({
	baseURL: SystemConsts.BASE_URL,
	timeout: 5000,
	withCredentials: true,
	headers: {
		'Content-Type': 'application/json'  // 修改为JSON格式
	}
});

// axios 请求拦截器
httpInstance.interceptors.request.use(config => {
	// 在每次请求前检查并添加JWT令牌
	const token = localStorage.getItem("token");
	if (token) {
		config.headers['Authorization'] = `Bearer ${token}`;
	}
	return config;
}, error => Promise.reject(error));


// 响应拦截器
httpInstance.interceptors.response.use(
	response => response.data,
	error => {
		const { response } = error;

		if (response) {
			const { status } = response;

			switch (status) {
				case 401:
					router.push({ name: "Unauthorized" });
					break;
				case 404:
					router.push({ name: "NotFound" });
					break;
				case 403:
					router.push({ name: "Forbidden" });
					break;
				case 405:
					router.push({ name: "MethodNotAllowed" });
					break;
				case 500:
					router.push({ name: "ServerError" });
					break;
				default:
					console.error(`Received status code: ${status}`);
					break;
			}
		} else {
			console.error('Error without response:', error);
		}

		// 返回一个 resolved 状态的 Promise 来抑制 Axios 默认的错误处理
		return Promise.resolve(error);
	}
);


export default httpInstance;

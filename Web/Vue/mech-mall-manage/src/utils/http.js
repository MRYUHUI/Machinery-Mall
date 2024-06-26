// axios 基础封装
import axios from "axios";
import { SystemConsts } from "@/enums/SystemConsts";
const httpInstance = axios.create({
	baseURL: SystemConsts.BASE_URL,
	timeout: 5000,
	withCredentials: true,
	// headers: {
	// 	'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
	// }
	headers: {
		'Content-Type': 'application/json'  // 修改为JSON格式
	}
})
// 拦截器

// axios 请求拦截器
httpInstance.interceptors.request.use(config => {
	return config
}, error => Promise.reject(error))

// axios 响应拦截器
httpInstance.interceptors.response.use(res => res.data, error => {
	return Promise.reject(error)
})

export default httpInstance
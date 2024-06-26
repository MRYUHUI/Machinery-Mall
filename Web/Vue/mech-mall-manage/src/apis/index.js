import { SystemConsts } from '@/enums/SystemConsts'
import userApi from '@/apis/user'
import adminApi from '@/apis/admin'
const apiRequests = {
	// 获取图片url
	attachImageUrl: (url) => url ? `${SystemConsts.BASE_URL}${url}` : "",
	...userApi,
	...adminApi,
}
export default apiRequests;
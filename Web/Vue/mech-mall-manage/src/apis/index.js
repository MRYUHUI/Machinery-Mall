import { SystemConsts } from '@/enums/SystemConsts'
import userApi from '@/apis/user'

const apiRequests = {
	// 获取图片url
	attachImageUrl: (url) => url ? `${SystemConsts.BASE_URL}${url}` : "",
	...userApi,
}
export default apiRequests;
import { SystemConsts } from '@/enums/SystemConsts'


const apiRequests = {
	// 获取图片url
	attachImageUrl: (url) => url ? `${SystemConsts.BASE_URL}${url}` : "",

}
export default apiRequests;
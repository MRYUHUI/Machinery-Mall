import { SystemConsts } from '@/enums/SystemConsts'
import homeApi from './home';


const apiRequests = {
	// 获取图片url
	attachImageUrl: (url) => url ? `${SystemConsts.BASE_URL}${url}` : "",
	...homeApi,

}
export default apiRequests;
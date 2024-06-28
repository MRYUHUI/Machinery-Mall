import { SystemConsts } from '@/enums/SystemConsts'
import userApi from '@/apis/user'
import adminUserApi from '@/apis/admin-user'
import adminOrderApi from './admin-order'
import adminGoodsApi from './admin-goods'
import adminGoodTypeApi from './admin-good-type'
const apiRequests = {
	// 获取图片url
	attachImageUrl: (url) => url ? `${SystemConsts.BASE_URL}${url}` : "",
	...userApi,
	...adminUserApi,
	...adminGoodsApi,
	...adminOrderApi,
	...adminGoodTypeApi,
}
export default apiRequests;
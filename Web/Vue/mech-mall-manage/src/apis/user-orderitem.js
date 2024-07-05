import httpInstance from "@/utils/http";

const userOrderItemApi = {
	getAllOrderItem: (userId) => httpInstance.get('/user/orderitem/allOrderItem', {
		params: {
			userId: userId
		}
	}),

	getOrderByUserId: (userId) => httpInstance.get('/user/orderitem/getOrderByUserId', {
		params: {
			userId: userId
		}
	}),
	//新增
	addOrderItem: (orderItem) => httpInstance.post('/user/orderitem/addOrderItem', orderItem),

	addOrder: (order) => httpInstance.post('/user/orderitem/addOrder', order),

	deleteOrderAndOrderItem: (orderId, orderItemId) => httpInstance.get('/user/orderitem/delete/order/orderItem', {
		params: {
			orderId,
			orderItemId
		}
	})

};
export default userOrderItemApi;
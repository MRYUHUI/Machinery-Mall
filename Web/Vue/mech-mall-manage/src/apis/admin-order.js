import httpInstance from "@/utils/http";
const adminOrderApi = {
	getAllOrders: (page, size) => httpInstance.get('/admin/order/allOrders', {
		params: {
			page: page,
			size: size
		}
	}),
	updateOrderInfo: (order) => httpInstance.post('/admin/order/updateOrderInfo', order),
	deleteOrder: (orderNo) => httpInstance.get('/admin/order/deleteOrder', { params: { order_no: orderNo } }),  // 使用正确的路径和参数
	searchOrders: (query, page, size) => httpInstance.get('/admin/order/searchOrders', {
		params: {
			keyword: query,
			page: page,
			size: size
		}
	}),
	getnewAllOrderItem: (page, size) => httpInstance.get('/admin/order/allOrderItem', {
		params: {
			page: page,
			size: size
		}
	}),

	getOrderById: (id) => httpInstance.get('/admin/order/getOrderById', {
		params: {
			id: id,
		}
	}),

	getOrderItemByOrderId: (id) => httpInstance.get('/admin/order/getOrderItemById', {
		params: {
			id: id,
		}
	}),
};


export default adminOrderApi;

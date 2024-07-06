import httpInstance from "@/utils/http";

const userCartApi = {
	allCartCount: () => httpInstance.get('/user/cart/allCartCount'),//获取全部商品数
	clearCart: () => httpInstance.get('/user/cart/clearCart'),//清空购物车
	deleteCartItem: (itemID) => httpInstance.post('/user/cart/deleteCartItem', {
		id: itemID
	}),//
	allCartItem: (page, size) => httpInstance.get('/user/cart/allCartItem', {
		params: {
			page: page,
			size: size
		}
	}),//获取全部商品
	addCartItem: (userId, productId, quantity) => httpInstance.post('/user/cart/addCartItem', {
		userId: userId,
		productId: productId,
		quantity: quantity
	}),//添加商品
	updateCartItem: (id, quantity) => httpInstance.post('/user/cart/updateCartItem', {
		id: id,
		quantity: quantity
	}),//更新商品
	findProductById: (id) => httpInstance.get('/user/cart/findProductById', {
		params: {
			id: id,
		}
	}),
};
export default userCartApi;

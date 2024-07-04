import httpInstance from "@/utils/http";

const adminGoodTypeApi = {
	allCartCount: () => httpInstance.get('/user/cart/allCartCount'),
    // updatedCartCount: (newCount) => httpInstance.get('/user/cart/updatedCartCount',newCount),
    clearCart: () => httpInstance.get('/user/cart/clearCart'),
    deleteCartItem: (itemID) => httpInstance.post('/user/cart/deleteCartItem', {
		id: itemID
	}),
    allCartItem: () => httpInstance.get('/user/cart/allCartItem'),
    addCartItem: (userId , productId , quantity) => httpInstance.post('/user/cart/addCartItem',{
		userId: userId,
        productId: productId,
		quantity: quantity
	}),
};
export default adminGoodTypeApi;

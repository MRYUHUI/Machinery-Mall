import httpInstance from "@/utils/http";

const userAddressApi = {
	getUserAddress: (userId) => httpInstance.get('user/address/allAddress', {
		params: {
			userId: userId
		}
	}),
    updateUserAddress: (shippingAddress) =>httpInstance.post('/user/address/updateAddress',shippingAddress),

    addUserAddress: (shippingAddress) =>httpInstance.post('/user/address/addAddress',shippingAddress),
    
    deleteUserAddress: (id) => httpInstance.get('/user/address/deleteAddress', {
		params: {
			id: id
		}
	}),
	setDfaultAddress: (id,userId) => httpInstance.get('/user/address/updateDfault',{
		params: {
			id: id,
			userId: userId
		}
	}),

};
export default userAddressApi;
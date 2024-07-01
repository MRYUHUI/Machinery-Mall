import { createStore } from "vuex";
import createPersistedState from 'vuex-persistedstate';
import user from "./user";
import admin from "./admin";
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import product from "./product";
=======
import adminGoodType from "./adminGoodType";

>>>>>>> 6bce09d549f33257efc61c0ad0d00c9901d1d2bb
=======
import adminGoodType from "./adminGoodType";

>>>>>>> 6bce09d549f33257efc61c0ad0d00c9901d1d2bb

=======
import adminOrder from "./adminOrder";
>>>>>>> zzx
const store = createStore({
	modules: {
		user,
		admin,
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		product,
=======
		adminGoodType,
>>>>>>> 6bce09d549f33257efc61c0ad0d00c9901d1d2bb
=======
		adminGoodType,
>>>>>>> 6bce09d549f33257efc61c0ad0d00c9901d1d2bb
=======
		adminOrder,
>>>>>>> zzx
	},
	plugins: [
		createPersistedState({
			key: 'my-app-state', // 自定义存储键名
		}),
	],
});

export default store;

// // 在页面即将刷新时，存储本地存储
// window.addEventListener('beforeunload', () => {
// 	const currentState = store.state; // 获取当前 Vuex 状态
// 	localStorage.setItem('my-app-state', JSON.stringify(currentState)); // 存储到本地存储
// });
// import { createStore } from "vuex";
// import createPersistedState from 'vuex-persistedstate';
// import user from "./user";
// import admin from "./admin";
// import adminGoodType from "./adminGoodType";
// import adminOrder from "./adminOrder";
// import product from "./product";
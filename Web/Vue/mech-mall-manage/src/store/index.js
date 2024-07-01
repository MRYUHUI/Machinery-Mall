import { createStore } from "vuex";
import createPersistedState from 'vuex-persistedstate';
import user from "./user";
import admin from "./admin";


const store = createStore({
	modules: {
		user,
		admin,
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

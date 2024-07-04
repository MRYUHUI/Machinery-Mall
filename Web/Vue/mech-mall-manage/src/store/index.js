import { createStore } from "vuex";
import createPersistedState from 'vuex-persistedstate';
import user from "./user";
import adminUser from "./adminUser";
import product from "./product";
import adminGoodType from "./adminGoodType";
import adminOrder from "./adminOrder";
import mallHome from "./MallHome";
const store = createStore({
	modules: {
		user,
		adminUser,
		product,
		adminGoodType,
		adminOrder,
		mallHome,
	},
	plugins: [
		createPersistedState({
			key: 'my-app-state', // 自定义存储键名
		}),
	],
});

export default store;

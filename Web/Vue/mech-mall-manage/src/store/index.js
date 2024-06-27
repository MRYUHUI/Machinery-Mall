import { createStore } from "vuex";
import createPersistedState from 'vuex-persistedstate';
import user from "./user";
import admin from "./admin";
export default createStore({
	modules: {
		user,
		admin,
	},
	plugins: [createPersistedState()],
});

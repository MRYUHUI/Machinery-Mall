import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
	{
		path: '/',
		name: 'layout',
		component: () => import('@/views/Layout.vue'),
		children: [
			{
				path: '/',
				name: 'login',
				component: () => import('@/views/Login.vue'),
			},
			{
				path: '/sign-up',
				name: 'register',
				component: () => import('@/views/Register.vue')
			}
		]
	},
	{
		path: '/user-home',
		name: 'user-home',
		component: () => import('@/views/UserHome.vue')
	},
	{
		path: '/admin-home',
		name: 'admin-home',
		component: () => import('@/views/AdminHome.vue'),
		children: [
			{
				path: '/admin-home',
				name: 'admin-user',
				component: () => import('@/components/admin/AdminManageUser.vue'),
			}
		]
	}
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})

// 导航守卫，用于在导航前进行检查
router.beforeEach((to, from, next) => {
	// 检查是否是相同的路由
	if (to.name === from.name) {
		// 如果是相同路由，则不进行导航
		console.log('当前路由与目标路由相同，不进行导航。');
		return;
	}
	// 继续导航
	next();
});
export default router

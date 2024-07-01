import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
	{
		path: '/:pathMatch(.*)*',
		name: 'CatchAll',
		component: () => import('@/views/error/ERROR_404.vue'), // 页面不存在
	},
	{
		path: '/401',
		name: 'Unauthorized',
		component: () => import('@/views/error/ERROR_401.vue'), // 未授权
	},
	{
		path: '/404',
		name: 'NotFound',
		component: () => import('@/views/error/ERROR_404.vue'), // 页面不存在
	},
	{
		path: '/403',
		name: 'Forbidden',
		component: () => import('@/views/error/ERROR_403.vue'), // 禁止访问
	},
	{
		path: '/405',
		name: 'MethodNotAllowed',
		component: () => import('@/views/error/ERROR_405.vue'), // 方法不允许
	},
	{
		path: '/500',
		name: 'ServerError',
		component: () => import('@/views/error/ERROR_500.vue'), // 服务器错误
	},
	{
		path: '/',
		name: 'layout',
		component: () => import('@/views/Layout.vue'),
		redirect: { name: 'user-home' },  // 添加默认重定向
		children: [
			{
				path: '/login',
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
		component: () => import('@/views/UserHome.vue'),
		meta: {
			requireAuth: true,
		},
	},
	{
		path: '/admin-home',
		name: 'admin-home',
		component: () => import('@/views/AdminHome.vue'),
		meta: {
			requireAuth: true,
		},
		redirect: { name: 'admin-user' },  // 添加默认重定向
		children: [
			{
				path: '',
				name: 'admin-user',
				component: () => import('@/components/admin/AdminManageUser.vue'),
				meta: {
					requireAuth: true,
				},
			},
			{
				path: '/admin-good-type',
				name: 'admin-good-type',
				component: () => import('@/components/admin/AdminManageGoodType.vue'),
				meta: {
					requireAuth: true,
				},
			},
			{
				path: '/admin-goods',
				name: 'admin-goods',
				component: () => import('@/components/admin/AdminManageGoods.vue'),
				meta: {
					requireAuth: true,
				},
			},
			{
				path: '/admin-order',
				name: 'admin-order',
				component: () => import('@/components/admin/AdminMangeOrder.vue'),
				meta: {
					requireAuth: true,
				},
			},
		]
	}
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})


// 导航守卫，用于在导航前进行检查
router.beforeEach((to, from, next) => {
	const token = localStorage.getItem('token')

	// 检查是否是相同的路由
	if (to.path === from.path) {
		// 如果是相同路由，则不进行导航
		console.log('当前路由与目标路由相同，不进行导航。');
		next(false); // 阻止导航
	} else if (to.meta.requireAuth && !token) {
		// 需要用户认证 && 没有登录
		next({
			name: 'login'
		});
	} else {
		// 继续导航
		next();
	}
});
export default router

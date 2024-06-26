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

export default router

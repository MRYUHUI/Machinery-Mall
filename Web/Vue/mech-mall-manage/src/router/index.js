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
		path: '/home',
		name: 'home',
		component: () => import('@/views/Home.vue')
	}
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})

export default router

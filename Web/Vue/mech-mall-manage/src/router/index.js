import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
	{
		path: '/',
		name: 'container',
		component: () => import('@/views/Container.vue'),
		children: [
			{
				path: '/',
				name: 'home',
				component: () => import('@/views/Home.vue')
			}
		]
	},
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})

export default router

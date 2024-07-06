import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [

	{
		path: '/',
		name: 'layout',
		component: () => import('@/views/Layout.vue'),
		redirect: { name: 'user-home' },
		children: [
			{
				// 前台界面
				path: '/',
				name: 'user-home',
				component: () => import('@/views/UserHome.vue'),
				redirect: { name: 'font-page' },
				meta: {
					breadcrumb: '首页'
				},
				children: [
					{
						// 搜索商品详情
						path: '/search-detail',
						name: 'search-detail',
						component: () => import('@/views/Home/DisplaySearchGoods.vue'),
						meta: {
							breadcrumb: '搜索商品'
						},
					},
					{
						// 前台的首页
						path: '/',
						name: 'font-page',
						component: () => import('@/views/FrontPage.vue')
					},
					{
						// 热门商品详情
						path: '/hot-detail',
						name: 'hot-detail',
						component: () => import('@/views/Home/DisplayHotGoods.vue'),
						meta: {
							breadcrumb: '热销商品'
						},
					},
					{
						// 商品详情
						path: '/good/detail',
						name: 'good-detail',
						component: () => import('@/views/Commodity/GoodDetail.vue'),
						meta: {
							breadcrumb: '商品详情'
						},
					},
					{
						// 分类商品详情
						path: '/category/good/detail',
						name: 'category-good-detail',
						component: () => import('@/views/Commodity/GoodOrderByCategory.vue'),
						meta: {
							breadcrumb: '分类详情'
						},
					},
					{
						// 我的商城
						path: '/mall-home',
						name: 'mall-home',
						component: () => import('@/views/MyMallHome.vue'),
						redirect: { name: 'order-user' },  // 添加默认重定向
						children: [
							{
								// 我的订单
								path: '/order-user',
								name: 'order-user',
								component: () => import('@/components/user/UserOrder.vue')
							},
							{
								// 我的购物车
								path: '/cart-user',
								name: 'cart-user',
								component: () => import('@/components/user/UserCart.vue')
							},
							{
								// 收货地址管理
								path: '/address-user',
								name: 'address-user',
								component: () => import('@/components/user/UserAddress.vue')
							},
						]
					},
				]
			},
		]
	},
	{
		// 登录
		path: '/login',
		name: 'login',
		component: () => import('@/views/Login.vue'),
	},
	{
		// 注册
		path: '/sign-up',
		name: 'register',
		component: () => import('@/views/Register.vue')
	},
	{
		// 后台
		path: '/admin-home',
		name: 'admin-home',
		component: () => import('@/views/AdminHome.vue'),
		meta: {
			requireAuth: true,
		},
		redirect: { name: 'admin-user' },  // 添加默认重定向
		children: [
			{
				// 用户管理
				path: '',
				name: 'admin-user',
				component: () => import('@/components/admin/AdminManageUser.vue'),
				meta: {
					requireAuth: true,
				},
			},
			{
				// 商品类型管理
				path: '/admin-good-type',
				name: 'admin-good-type',
				component: () => import('@/components/admin/AdminManageGoodType.vue'),
				meta: {
					requireAuth: true,
				},
			},
			{
				// 商品管理
				path: '/admin-goods',
				name: 'admin-goods',
				component: () => import('@/components/admin/AdminManageGoods.vue'),
				meta: {
					requireAuth: true,
				},
			},
			{
				// 订单管理
				path: '/admin-order',
				name: 'admin-order',
				component: () => import('@/components/admin/AdminMangeOrder.vue'),
				meta: {
					requireAuth: true,
				},
			},
		]
	},
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
]

const router = createRouter({
	history: createWebHashHistory(),
	routes
})


// 导航守卫，用于在导航前进行检查
router.beforeEach((to, from, next) => {
	const token = localStorage.getItem('token')

	// 检查是否是相同的路由
	if (to.name === from.name) {
		// 如果是相同路由，则不进行导航
		console.log('当前路由与目标路由相同，不进行导航。');
		next(false); // 阻止导航
	} else if (to.meta.requireAuth && !token && to.name != 'user-home') {
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

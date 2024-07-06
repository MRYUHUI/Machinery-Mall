import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import installElementPlus from './plugins/element'
import '@/assets/css/global.css'
import store from './store';

import '@/assets/icon-font/iconfont'
// 全局引入ElementPlus组件
import ElementPlus from 'element-plus'
import zhLocale from 'element-plus/lib/locale/lang/zh-cn'
import { lazyPlugin } from '@/directives/index'




const app = createApp(App)
installElementPlus(app)
app.use(router).use(store)
app.use(ElementPlus, {
	locale: zhLocale
})
app.use(lazyPlugin)
app.mount('#app')
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import installElementPlus from './plugins/element'
import '@/assets/css/global.css'
import store from './store';

import '@/assets/icon-font/iconfont'




const app = createApp(App)
installElementPlus(app)
app.use(router).use(store).mount('#app')
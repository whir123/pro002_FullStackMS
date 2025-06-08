import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import '@/assets/global.css'//引入一个全局的css文件

import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';//main.js里引入elementplus
import { zhCn } from 'element-plus/es/locales.mjs';
import * as ElementPlusIconsVue from '@element-plus/icons-vue'//引入icons-vue（全局注册）


const app = createApp(App)

app.use(router)
app.use(ElementPlus,{
    locale:zhCn,
})
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}//一个个注册icon component组件
app.mount('#app')

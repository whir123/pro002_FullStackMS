import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {path:'/', redirect:'/login',},
    {path:'/login', name:'Login', meta:{title:'登录系统'}, component:() => import('../views/Login.vue')},
    {path:'/register', name:'Register', meta:{title:'欢迎注册'}, component:() => import('../views/Register.vue')},
    // 访问 / 页面时自动跳转到 /manager，作为系统首页的入口
    {path:'/manager', name:'Manager', meta:{title:"主页"}, component:() => import('../views/Manager.vue'), children:[
      {path:'test' ,name:'Test', meta:{title:"测试数据页"}, component:() => import('../views/Test_Home.vue'),},
      {path:'home' ,name:'Home', meta:{title:"系统首页"}, component:() => import('../views/Home.vue'),},
      {path:'data' ,name:'Data', meta:{title:"数据统计"}, component:() => import('../views/Data.vue'),},
      {path:'admin' ,name:'Admin', meta:{title:"管理员信息"}, component:() => import('../views/Admin.vue'),},
      {path:'employee' ,name:'Emplotee', meta:{title:"员工信息"}, component:() => import('../views/Employee.vue'),},
      {path:'person' ,name:'Person', meta:{title:"个人信息"}, component:() => import('../views/Person.vue'),},
      {path:'password' ,name:'Password', meta:{title:"修改密码"}, component:() => import('../views/Password.vue'),},
    ]},
    {path:'/404', name:'NotFound', meta:{title:'404找不到页面'}, component:() => import('../views/404.vue')},
    {path:'/:pathMatch(.*)',redirect:'/404'}
  ],
})

//beforeEach 表示跳转之前的一些操作
router.beforeEach((to, from, next) => {
  document.title = to.meta.title as string;
  next() //必须调用这个函数才能实现跳转
})

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),  // 确保你的路径和文件名正确
    redirect:'/home',//重定向到主页
    children:[
      {path:'/home',name:'Home',component:()=>import('../views/manager/Home')},
      {path:'/user',name:'User',component:()=>import('../views/manager/User')},
      {path:'/403',name:'Auth',component:()=>import('../views/manager/Auth')}
    ]
  },
  {path: '/login', name:'Login', component: () => import('../views/Login.vue')},  // 确保你的路径和文件名正确
  {path: '/register', name:'Register', component: () => import('../views/Register.vue')},  // 确保你的路径和文件名正确
  {path: '*', name:'404', component: () => import('../views/404.vue')}  // 确保你的路径和文件名正确
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  // to 是到达的路由信息
  // from 是开源的路由信息
  // next 是帮助我们跳转路由的函数
  let adminPaths = ['/user']
  let user = JSON.parse(localStorage.getItem('honey-user') || '{}')

  if (user.role !== '管理员' && adminPaths.includes(to.path)) {
    // 如果当前登录的用户不是管理员，然后当前的到达的路径是管理员才有权限访问的路径，那这个时候我就让用户去到一个没有权限的页面，不让他访问实际的页面
    next('/403')
  } else {
    next()
  }
})

export default router

import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/HomeView.vue')  // 确保你的路径和文件名正确
  },
  {
    path: '/login',
    name:'Login',
    component: () => import('../views/Login.vue')  // 确保你的路径和文件名正确
  },
  {
    path: '/register',
    name:'Register',
    component: () => import('../views/Register.vue')  // 确保你的路径和文件名正确
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router

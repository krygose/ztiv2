import { createRouter, createWebHistory } from 'vue-router'

import HomeView from '@/views/HomeView.vue'

import { useAuthStore } from '@/stores/auth'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import NewItemView from '@/views/NewItemView.vue'
import ItemsView from '@/views/ItemsView.vue'
import OrderView from '@/views/OrderView.vue'
import OrderModifyView from '@/views/OrderModifyView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register',
      name: 'register',
      component: RegisterView
    },
    {
      path: '/newItem',
      name: 'newItem',
      component: NewItemView
    },
    {
      path: '/items',
      name: 'items',
      component: ItemsView
    },
    {
      path: '/orders',
      name: 'orders',
      component: OrderView
    },
    {
      path: '/orderModify',
      name: 'orderModify',
      component: OrderModifyView
    }
  ]
})

router.beforeEach(async (to) => {
  const publicPages = ['/login', '/register', '/', '/newItem', '/items', '/orders', '/orderModify']
  const authRequired = !publicPages.includes(to.path)
  const auth = useAuthStore()

  if (authRequired && !auth.user) {
    return '/login'
  }
})

export default router

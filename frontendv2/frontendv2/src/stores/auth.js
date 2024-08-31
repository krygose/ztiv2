import { defineStore } from 'pinia'
import router from '@/router/index.js'
import { isLoggedIn } from '@/authStatus.js'
import axios from 'axios'

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => {
    return {
      user: localStorage.getItem('user') ? localStorage.getItem('user') : null,
      userId: localStorage.getItem('userId') ? localStorage.getItem('userId') : null,
      token: localStorage.getItem('token') ? localStorage.getItem('token') : '',
      role: localStorage.getItem('role') ? localStorage.getItem('role') : null,
      returnUrl: '/'
    }
  },
  actions: {
    async login(email, password) {
      const response = await axios.post('api/token', {
        email: email,
        password: password
      })

      if (response.status === 200) {
        console.log(response)
        console.log(response.data['userId'])
        localStorage.setItem('user', email)
        localStorage.setItem('userId', response.data['userId'])
        localStorage.setItem('token', response.data['token'])
        this.user = email
        this.userId = response.data['userId']
        this.token = response.data['token']
        isLoggedIn.value = true
        await this.fetchUser()
        router.push(this.returnUrl || '/')
      }
    },
    async fetchUser() {
      const response = await axios.get('api/users/user')

      if (response.status === 200) {
        const role = response.data['role']
        localStorage.setItem('role', role)
        this.role = role
      }
    },
    logout() {
      this.user = null
      this.userId = null
      this.token = ''
      this.role = ''
      localStorage.removeItem('user')
      localStorage.removeItem('userId')
      localStorage.removeItem('token')
      localStorage.removeItem('role')
    }
  }
})

import { ref } from 'vue'
export const isLoggedIn = ref(localStorage.getItem('token') != null)
export const userRole = ref(localStorage.getItem('role'))

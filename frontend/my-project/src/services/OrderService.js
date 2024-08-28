// src/services/OrderService.js
import axios from 'axios'

const API_URL = 'http://localhost:8080/api/order'

export default {
  createOrder (newOrderDto) {
    return axios.post(API_URL, newOrderDto)
  },
  getOrderById (orderId) {
    return axios.get(`${API_URL}/${orderId}`)
  },
  getAllOrders () {
    return axios.get(API_URL)
  }
}

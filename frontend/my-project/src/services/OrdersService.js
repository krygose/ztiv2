// src/services/OrdersService.js
import axios from 'axios'

const API_URL = 'http://localhost:8080/api/orders'

export default {
  getAllOrders () {
    return axios.get(API_URL)
  },
  getOrderById (orderId) {
    return axios.get(`${API_URL}/${orderId}`)
  },
  modifyOrderState (id) {
    return axios.put(`${API_URL}/${id}/modifyState`)
  }
}

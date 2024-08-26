// src/services/ItemService.js
import axios from 'axios'

const API_URL = 'http://localhost:8080/api/items'

export default {
  getItems () {
    return axios.get(API_URL)
  },
  createItem (newItemDto) {
    return axios.post(API_URL, newItemDto)
  },
  modifyItem (id, quantity) {
    return axios.put(`${API_URL}/${id}/modifyItem/${quantity}`)
  },
  getItemsExist () {
    return axios.get(`${API_URL}/itemExist`)
  }
}

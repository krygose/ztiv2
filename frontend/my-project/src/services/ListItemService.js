// src/services/ListItemService.js
import axios from 'axios'

const API_URL = 'http://localhost:8080/api/listItem'

export default {
  getListItems () {
    return axios.get(API_URL)
  },
  createListItem (newListItemDto) {
    return axios.post(API_URL, newListItemDto)
  }
}

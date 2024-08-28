<!-- src/components/ListItemList.vue -->
<template>
    <div>
      <h2>List Items</h2>
      <ul>
        <li v-for="item in listItems" :key="item.id">
          {{ item.name }} - {{ item.description }}
        </li>
      </ul>
      <form @submit.prevent="createListItem">
        <input v-model="newListItem.name" placeholder="Name" />
        <input v-model="newListItem.description" placeholder="Description" />
        <button type="submit">Add List Item</button>
      </form>
    </div>
  </template>

<script>
import ListItemService from '../services/ListItemService'

export default {
  data () {
    return {
      listItems: [],
      newListItem: {
        name: '',
        description: ''
      }
    }
  },
  methods: {
    fetchListItems () {
      ListItemService.getListItems().then(response => {
        this.listItems = response.data
      })
    },
    createListItem () {
      ListItemService.createListItem(this.newListItem).then(() => {
        this.fetchListItems()
        this.newListItem = { name: '', description: '' }
      })
    }
  },
  created () {
    this.fetchListItems()
  }
}
</script>

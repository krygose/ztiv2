<!-- src/components/ItemList.vue -->
<template>
    <div>
      <h2>Items</h2>
      <ul>
        <li v-for="item in items" :key="item.id">
          {{ item.name }} - {{ item.quantity }}
          <button @click="modifyItem(item.id, item.quantity + 1)">Increase Quantity</button>
        </li>
      </ul>
      <form @submit.prevent="createItem">
        <input v-model="newItem.name" placeholder="Name" />
        <input v-model="newItem.quantity" type="number" placeholder="Quantity" />
        <button type="submit">Add Item</button>
      </form>
    </div>
  </template>

<script>
import ItemService from '../services/ItemService'

export default {
  data () {
    return {
      items: [],
      newItem: {
        name: '',
        quantity: 0
      }
    }
  },
  methods: {
    fetchItems () {
      ItemService.getItems().then(response => {
        this.items = response.data
      })
    },
    createItem () {
      ItemService.createItem(this.newItem).then(() => {
        this.fetchItems()
        this.newItem = { name: '', quantity: 0 }
      })
    },
    modifyItem (id, quantity) {
      ItemService.modifyItem(id, quantity).then(() => {
        this.fetchItems()
      })
    }
  },
  created () {
    this.fetchItems()
  }
}
</script>

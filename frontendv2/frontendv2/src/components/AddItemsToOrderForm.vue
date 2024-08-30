<!-- src/AddItemsToOrderForm.vue -->
<template>
  <div class="p-6 bg-white rounded-lg shadow-md">
    <h2 class="text-2xl font-bold mb-4">Add Items to Order</h2>
    <form @submit.prevent="submitItem">
      <div class="mb-4">
        <label for="orderId" class="block text-sm font-medium text-gray-700">Order ID</label>
        <input
          id="orderId"
          v-model="listItem.personOrderId"
          type="text"
          class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
          required
        />
      </div>
      <div class="mb-4">
        <label for="itemId" class="block text-sm font-medium text-gray-700">Item ID</label>
        <input
          id="itemId"
          v-model="listItem.itemId"
          type="text"
          class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
          required
        />
      </div>
      <div class="mb-4">
        <label for="quantity" class="block text-sm font-medium text-gray-700">Quantity</label>
        <input
          id="quantity"
          v-model="listItem.quantity"
          type="number"
          min="1"
          class="mt-1 block w-full border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
          required
        />
      </div>
      <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">
        Add Item
      </button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      listItem: {
        personOrderId: '',
        itemId: '',
        quantity: 1
      }
    }
  },
  methods: {
    async submitItem() {
      try {
        await axios.post('http://localhost:8080/api/items', this.listItem)
        alert('Item added to order successfully')
        // Optionally, reset form or redirect
        this.listItem.personOrderId = ''
        this.listItem.itemId = ''
        this.listItem.quantity = 1
      } catch (error) {
        console.error('Error adding item to order:', error)
        alert('Error adding item to order')
      }
    }
  }
}
</script>

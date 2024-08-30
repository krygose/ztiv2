<template>
  <div class="bg-white p-6 rounded-lg shadow-md w-full max-w-md mx-auto">
    <h2 class="text-2xl font-bold mb-4 text-center">Add New Item</h2>
    <form @submit.prevent="submitForm">
      <div class="mb-4">
        <label for="name" class="block text-gray-700 font-semibold mb-2">Item Name</label>
        <input
          id="name"
          v-model="newItem.name"
          type="text"
          class="w-full p-2 border border-gray-300 rounded"
          required
        />
      </div>
      <div class="mb-4">
        <label for="price" class="block text-gray-700 font-semibold mb-2">Price</label>
        <input
          id="price"
          v-model="newItem.price"
          type="number"
          step="0.01"
          class="w-full p-2 border border-gray-300 rounded"
          required
        />
      </div>
      <div class="mb-4">
        <label for="quantity" class="block text-gray-700 font-semibold mb-2">Quantity</label>
        <input
          id="quantity"
          v-model="newItem.quantity"
          type="number"
          class="w-full p-2 border border-gray-300 rounded"
          required
        />
      </div>
      <button type="submit" class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600">
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
      newItem: {
        name: '',
        price: 0,
        quantity: 0
      }
    }
  },
  methods: {
    async submitForm() {
      try {
        await axios.post('http://localhost:8080/api/items', this.newItem)
        alert('Item added successfully!')
        this.resetForm() // Reset the form after successful submission
      } catch (error) {
        console.error('Error adding item:', error)
        alert('Failed to add item. Please try again.')
      }
    },
    resetForm() {
      this.newItem = {
        name: '',
        price: 0,
        quantity: 0
      }
    }
  }
}
</script>

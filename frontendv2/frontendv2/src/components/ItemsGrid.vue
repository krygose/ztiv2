<!-- src/ItemsList.vue -->
<template>
  <div class="flex flex-col items-center min-h-screen pt-20">
    <!-- Section for items from the 'items' endpoint -->
    <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
      <h1 class="text-4xl font-bold text-gray-800">Items in Database</h1>
    </section>
    <div
      class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-4xl mb-12"
    >
      <!-- Loop through items from the 'items' endpoint -->
      <div v-for="item in items" :key="item.id" class="bg-white p-4 rounded-lg shadow-md">
        <h2 class="text-xl font-bold mb-2">{{ item.name }}</h2>
        <p class="text-gray-700">Price: ${{ item.price.toFixed(2) }}</p>
        <p class="text-gray-700">Quantity: {{ item.quantity }}</p>
      </div>
    </div>

    <!-- Section for items from the 'items/itemExist' endpoint -->
    <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
      <h1 class="text-4xl font-bold text-gray-800">Existing Items</h1>
    </section>
    <div
      class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-4xl"
    >
      <!-- Loop through items from the 'items/itemExist' endpoint -->
      <div v-for="item in existingItems" :key="item.id" class="bg-white p-4 rounded-lg shadow-md">
        <h2 class="text-xl font-bold mb-2">{{ item.name }}</h2>
        <p class="text-gray-700">Price: ${{ item.price.toFixed(2) }}</p>
        <p class="text-gray-700">Quantity: {{ item.quantity }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      items: [], // Array to store fetched items from the 'items' endpoint
      existingItems: [] // Array to store fetched items from the 'items/itemExist' endpoint
    }
  },
  created() {
    // Fetch items from both endpoints when the component is created
    this.fetchItems()
  },
  methods: {
    async fetchItems() {
      try {
        // Fetch items from the 'items' endpoint
        const response1 = await axios.get('http://localhost:8080/api/items')
        this.items = response1.data

        // Fetch items from the 'items/itemExist' endpoint
        const response2 = await axios.get('http://localhost:8080/api/items/itemExist')
        this.existingItems = response2.data
      } catch (error) {
        console.error('Error fetching items:', error)
      }
    }
  }
}
</script>

<style scoped>
/* Add any additional styles you may need here */
</style>

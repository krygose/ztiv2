<template>
  <div class="flex flex-col items-center min-h-screen pt-20">
    <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
      <h1 class="text-4xl font-bold text-gray-800">Orders in Database</h1>
    </section>
    <div
      class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-4xl mb-12"
    >
      <div v-for="order in orders" :key="order.id" class="bg-white p-4 rounded-lg shadow-md">
        <h2 class="text-xl font-bold mb-2">{{ order.id }}</h2>
        <p class="text-gray-700">personId: {{ order.personId }}</p>
        <p class="text-gray-700">state: {{ order.state }}</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      orders: [] // Array to store fetched items from the 'items' endpoint
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
        const response1 = await axios.get('http://localhost:8080/api/orders')
        this.orders = response1.data
      } catch (error) {
        console.error('Error fetching items:', error)
      }
    }
  }
}
</script>

<!-- <template>
  <div class="flex flex-col items-center min-h-screen pt-20">
    <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
      <h1 class="text-4xl font-bold text-gray-800">Welcome to BikeShop</h1>
    </section>
    <div
      class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-4xl"
    >
      
      <div v-for="item in items" :key="item.id" class="bg-white p-4 rounded-lg shadow-md">
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
      items: [] // Array to store fetched items
    }
  },
  created() {
    // Fetch items when the component is created
    this.fetchItems()
  },
  methods: {
    async fetchItems() {
      try {
        const response = await axios.get('http://localhost:8080/api/items')
        this.items = response.data // Set the items data from the backend response
      } catch (error) {
        console.error('Error fetching items:', error)
      }
    }
  }
}
</script> -->

<!-- src/MainView.vue -->
<template>
  <div class="flex flex-col items-center min-h-screen pt-20">
    <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
      <h1 class="text-4xl font-bold text-gray-800">Welcome to BikeShop</h1>
    </section>

    <!-- Add a link to navigate to the NewItemView -->
    <div class="mb-8">
      <router-link
        v-if="role === 'admin'"
        to="/newItem"
        class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
      >
        Add New Item
      </router-link>
    </div>

    <div class="mb-8">
      <router-link to="/items" class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">
        Items
      </router-link>
    </div>

    <div class="mb-8">
      <router-link
        v-if="role === 'admin' || role === 'user'"
        to="/orders"
        class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
      >
        Order
      </router-link>
    </div>

    <div class="mb-8">
      <router-link
        v-if="role === 'admin'"
        to="/orderModify"
        class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
      >
        Order Modify
      </router-link>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { userRole } from '@/authStatus'

export default {
  setup() {
    return { role: userRole }
  },
  data() {
    return {
      items: [] // Array to store fetched items
    }
  },
  created() {
    // Fetch items when the component is created
    this.fetchItems()
    this.userRole = localStorage.getItem('role')
    console.log(this.userRole)
  },

  methods: {
    async fetchItems() {
      try {
        const response = await axios.get('api/items')
        this.items = response.data // Set the items data from the backend response
      } catch (error) {
        console.error('Error fetching items:', error)
      }
    }
  }
}
</script>

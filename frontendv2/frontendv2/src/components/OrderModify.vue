<!-- 
<template>
  <div class="flex flex-col items-center min-h-screen pt-20">
    <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
      <h1 class="text-4xl font-bold text-gray-800">Orders in progress</h1>
    </section>
    <div
      class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-4xl mb-12"
    >
      <div v-for="order in orders" :key="order.id" class="bg-white p-4 rounded-lg shadow-md">
        <h2 class="text-xl font-bold mb-2">{{ order.id }}</h2>
        <p class="text-gray-700">Person ID: {{ order.personId }}</p>
        <p class="text-gray-700">State: {{ order.state }}</p>
   
        <button
          class="mt-2 bg-blue-500 text-white py-1 px-4 rounded hover:bg-blue-600"
          @click="modifyOrderState(order.id)"
        >
          Modify State
        </button>

        <button
          class="mt-2 bg-green-500 text-white py-1 px-4 rounded hover:bg-green-600"
          @click="fetchOrderItems(order.id)"
        >
          View Items
        </button>

        <div v-if="selectedOrderId === order.id" class="mt-4">
          <h3 class="text-lg font-bold mb-2">Items in this Order:</h3>
          <ul>
            <li v-for="item in orderItems" :key="item.id" class="text-gray-700 mb-1">
              {{ item.name }} - Quantity: {{ item.quantity }}
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      orders: [], // Array to store fetched orders from the 'orders' endpoint
      selectedOrderId: null, // To track the currently selected order
      orderItems: [] // Array to store items of the selected order
    }
  },
  created() {
    // Fetch orders when the component is created
    this.fetchOrders()
  },
  methods: {
    async fetchOrders() {
      try {
        // Fetch orders from the 'orders' endpoint
        const response = await axios.get('http://localhost:8080/api/orders')
        this.orders = response.data
      } catch (error) {
        console.error('Error fetching orders:', error)
      }
    },
    async modifyOrderState(orderId) {
      try {
        // Send PUT request to modify the state of the order
        await axios.put(`http://localhost:8080/api/orders/${orderId}/modifyState`)
        alert(`Order ${orderId} state modified successfully.`)
        // Optionally, you can refresh the orders list after modifying the state
        this.fetchOrders()
      } catch (error) {
        console.error('Error modifying order state:', error)
        alert('Error modifying order state')
      }
    },
    async fetchOrderItems(orderId) {
      try {
        // Fetch order details by ID, including items in the order
        const response = await axios.get(`http://localhost:8080/api/order/${orderId}`)
        this.orderItems = response.data.itemsList // Assuming the order response has an 'items' property
        this.selectedOrderId = orderId // Set the currently selected order ID
      } catch (error) {
        console.error('Error fetching order items:', error)
        alert('Error fetching order items')
      }
    }
  }
}
</script>

 -->

<template>
  <div class="flex flex-col items-center min-h-screen pt-20">
    <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
      <h1 class="text-4xl font-bold text-gray-800">Orders in Progress</h1>
    </section>
    <div
      class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-4xl mb-12"
    >
      <div v-for="order in orders" :key="order.id" class="bg-white p-4 rounded-lg shadow-md">
        <h2 class="text-xl font-bold mb-2">{{ order.id }}</h2>
        <p class="text-gray-700">Person ID: {{ order.personId }}</p>
        <p class="text-gray-700">State: {{ order.state }}</p>
        <!-- Button to modify state -->
        <button
          class="mt-2 bg-blue-500 text-white py-1 px-4 rounded hover:bg-blue-600"
          @click="modifyOrderState(order.id)"
        >
          Modify State
        </button>
        <!-- Clickable area to load items in order -->
        <button
          class="mt-2 bg-green-500 text-white py-1 px-4 rounded hover:bg-green-600"
          @click="fetchOrderItems(order.id)"
        >
          View Items
        </button>
        <!-- Display items in order if the order ID matches selectedOrderId -->
        <div v-if="selectedOrderId === order.id" class="mt-4">
          <h3 class="text-lg font-bold mb-2">Items in this Order:</h3>
          <ul>
            <li v-for="item in orderItems" :key="item.name" class="text-gray-700 mb-1">
              {{ item.name }} - Quantity: {{ item.quantity }} - Price: ${{ item.price.toFixed(2) }}
            </li>
          </ul>
          <!-- Display total value of the order -->
          <p class="text-gray-700 font-bold mt-2">Total Value: ${{ orderValue.toFixed(2) }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      orders: [], // Array to store fetched orders from the 'orders' endpoint
      selectedOrderId: null, // To track the currently selected order
      orderItems: [], // Array to store items of the selected order
      orderValue: 0.0 // To store the total value of the selected order
    }
  },
  created() {
    // Fetch orders when the component is created
    this.fetchOrders()
  },
  methods: {
    async fetchOrders() {
      try {
        // Fetch orders from the 'orders' endpoint
        const response = await axios.get('http://localhost:8080/api/orders')
        this.orders = response.data
      } catch (error) {
        console.error('Error fetching orders:', error)
      }
    },
    async modifyOrderState(orderId) {
      try {
        // Send PUT request to modify the state of the order
        await axios.put(`http://localhost:8080/api/orders/${orderId}/modifyState`)
        alert(`Order ${orderId} state modified successfully.`)
        // Optionally, you can refresh the orders list after modifying the state
        this.fetchOrders()
      } catch (error) {
        console.error('Error modifying order state:', error)
        alert('Error modifying order state')
      }
    },
    async fetchOrderItems(orderId) {
      try {
        // Fetch order details by ID, including items in the order
        const response = await axios.get(`http://localhost:8080/api/order/${orderId}`)
        this.orderItems = response.data.itemsList // Assuming the order response has an 'itemsList' property
        this.orderValue = response.data.value // Assuming the order response has a 'value' property
        this.selectedOrderId = orderId // Set the currently selected order ID
      } catch (error) {
        console.error('Error fetching order items:', error)
        alert('Error fetching order items')
      }
    }
  }
}
</script>

<style scoped>
/* Add any additional styles you may need here */
</style>

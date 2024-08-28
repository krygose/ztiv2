<template>
    <div>
      <h2>Orders</h2>
      <div v-if="orders.length > 0">
        <ul>
          <li v-for="order in orders" :key="order.id">
            <div>
              <strong>Order ID:</strong> {{ order.id }}
              <button @click="getOrderDetails(order.id)">View Details</button>
              <button @click="modifyOrderState(order.id)">Modify State</button>
            </div>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>No orders available.</p>
      </div>

      <div v-if="selectedOrder">
        <h3>Order Details</h3>
        <p><strong>ID:</strong> {{ selectedOrder.id }}</p>
        <p><strong>Status:</strong> {{ selectedOrder.status }}</p>
        <!-- Add more order details here as necessary -->
      </div>
    </div>
  </template>

<script>
import OrdersService from '../services/OrdersService'

export default {
  data () {
    return {
      orders: [],
      selectedOrder: null
    }
  },
  methods: {
    fetchOrders () {
      OrdersService.getAllOrders().then(response => {
        this.orders = response.data
      }).catch(error => {
        console.error('Error fetching orders:', error)
      })
    },
    getOrderDetails (orderId) {
      OrdersService.getOrderById(orderId).then(response => {
        this.selectedOrder = response.data
      }).catch(error => {
        console.error('Error fetching order details:', error)
      })
    },
    modifyOrderState (orderId) {
      OrdersService.modifyOrderState(orderId).then(() => {
        alert('Order state modified successfully!')
        this.fetchOrders() // Refresh the orders list after modifying the state
      }).catch(error => {
        console.error('Error modifying order state:', error)
      })
    }
  },
  created () {
    this.fetchOrders()
  }
}
</script>

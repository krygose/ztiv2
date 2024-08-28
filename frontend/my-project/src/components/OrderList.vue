<!-- src/components/OrderList.vue -->
<template>
    <div>
      <h2>Orders</h2>
      <ul>
        <li v-for="order in orders" :key="order.id">
          Order ID: {{ order.id }} - Customer: {{ order.customerName }}
        </li>
      </ul>
      <form @submit.prevent="createOrder">
        <input v-model="newOrder.customerName" placeholder="Customer Name" />
        <input v-model="newOrder.orderDetails" placeholder="Order Details" />
        <button type="submit">Add Order</button>
      </form>
    </div>
  </template>

<script>
import OrderService from '../services/OrderService'

export default {
  data () {
    return {
      orders: [],
      newOrder: {
        customerName: '',
        orderDetails: ''
      }
    }
  },
  methods: {
    fetchOrders () {
      OrderService.getAllOrders().then(response => {
        this.orders = response.data
      })
    },
    createOrder () {
      OrderService.createOrder(this.newOrder).then(() => {
        this.fetchOrders()
        this.newOrder = { customerName: '', orderDetails: '' }
      })
    }
  },
  created () {
    this.fetchOrders()
  }
}
</script>

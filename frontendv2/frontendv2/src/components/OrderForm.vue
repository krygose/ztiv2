<template>
  <div class="p-6 bg-white rounded-lg shadow-md">
    <h2 class="text-2xl font-bold mb-4">Create New Order</h2>
    <form @submit.prevent="submitOrder">
      <div class="mb-4">
        <label for="orderItems" class="block text-sm font-medium text-gray-700">Items</label>
        <div v-for="(item, index) in items" :key="index" class="flex items-center mb-2">
          <select
            :key="index"
            v-model="items[index].id"
            class="mt-1 block w-1/3 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm"
            required
          >
            <option value="" disabled>Select an item</option>
            <option
              v-for="availableItem in availableItems"
              :key="availableItem.id"
              :value="availableItem.id"
            >
              {{ availableItem.name }} - ${{ availableItem.price.toFixed(2) }}
            </option>
          </select>
          <input
            v-model="items[index].quantity"
            type="number"
            min="1"
            placeholder="Quantity"
            class="mt-1 block w-1/3 border border-gray-300 rounded-lg shadow-sm focus:ring-blue-500 focus:border-blue-500 sm:text-sm ml-2"
            required
          />
          <button
            type="button"
            class="ml-2 bg-red-500 text-white py-1 px-2 rounded hover:bg-red-600"
            @click="removeItem(index)"
          >
            Remove
          </button>
        </div>
        <button
          type="button"
          class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600"
          @click="addItem"
        >
          Add Item
        </button>
      </div>
      <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">
        Create Order
      </button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      items: [{ id: '', quantity: 1 }], // Initial item
      availableItems: [] // List of available items
    }
  },
  async created() {
    await this.fetchAvailableItems()
  },
  methods: {
    async fetchAvailableItems() {
      try {
        const response = await axios.get('api/items')
        this.availableItems = response.data
      } catch (error) {
        console.error('Error fetching available items:', error)
      }
    },
    addItem() {
      this.items.push({ id: '', quantity: 1 }) // Add new item field
    },
    removeItem(index) {
      this.items.splice(index, 1) // Remove item field
    },
    async submitOrder() {
      try {
        // Create the order with state set to 0

        const orderResponse = await axios.post('/api/order', {
          personId: localStorage.getItem('userId'),
          state: 0,
          itemList: this.items
        })

        // Get the created order ID from the response
        // const orderId = orderResponse.data.id

        // Prepare items to be linked to the created order
        // const listItems = this.items.map((item) => ({
        //   personOrderId: orderId,
        //   itemId: item.id,
        //   quantity: item.quantity
        // }))

        alert('Order created successfully')
        this.items = [{ id: '', quantity: 1 }]
      } catch (error) {
        console.error('Error creating order:', error)
      }
    }
  }
}
</script>

<style scoped>
/* Add any additional styles you may need here */
</style>

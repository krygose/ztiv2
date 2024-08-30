<!-- src/OrderManagementView.vue -->
<template>
  <div class="p-8">
    <h1 class="text-4xl font-bold mb-8">Order Management</h1>
    <OrderForm />
  </div>
</template>

<script>
import OrderForm from '@/components/OrderForm.vue'

export default {
  components: {
    OrderForm
  }
}
</script>

<style scoped>
/* Add any additional styles you may need here */
</style>

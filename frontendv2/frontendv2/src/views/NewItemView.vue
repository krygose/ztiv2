<template>
  <div class="bg-dark-mode-gray min-h-screen">
    <NewItemForm />
  </div>
</template>

<script>
import NewItemForm from '@/components/NewItemForm.vue'

export default {
  name: 'NewItemView',
  components: {
    NewItemForm
  }
}
</script>

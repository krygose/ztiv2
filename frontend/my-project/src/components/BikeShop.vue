<template>
    <div class="flex flex-col items-center min-h-screen pt-20">
      <section class="bg-gray-200 text-center py-16 mb-8 rounded-lg w-full max-w-4xl">
        <h1 class="text-4xl font-bold text-gray-800">Welcome to BikeShop</h1>
      </section>
      <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-6 w-full max-w-4xl">
      </div>
    </div>
  </template>

<script>
import axios from 'axios'

export default {
  name: 'BikeShop',
  data () {
    return {
      items: []
    }
  },
  async mounted () {
    await this.fetchTracks()
  },
  methods: {
    handleitemsClick (items) {
      alert(`Clicked on: ${items.name}`)
    },
    async fetchitems () {
      const response = await axios.get('api/items')
      const itemsList = response.data['content']

      itemsList.forEach((items) => {
        this.items.push(items)
      })
    }
  }
}
</script>

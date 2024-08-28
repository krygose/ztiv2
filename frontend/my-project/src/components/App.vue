<!-- src/App.vue -->
<template>
    <div id="app">
      <ItemList />
    </div>
  </template>

<script>
import ItemList from './components/ItemList.vue'

export default {
  components: {
    ItemList
  }
}
</script>

<template>
  <div id="app">
    <OrdersList />
  </div>
</template>

<script>
import OrdersList from './components/OrdersList.vue';

export default {
  components: {
    OrdersList
  }
};
</script>

<template>
  <div id="app">
    <OrderList />
  </div>
</template>

<script>
import OrdersList from './components/OrderList.vue';

export default {
  components: {
    OrderList
  }
};
</script>

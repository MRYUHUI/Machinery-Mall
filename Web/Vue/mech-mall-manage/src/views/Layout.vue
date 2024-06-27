<script setup>
// import Header from '../components/Header.vue';
import { getCurrentInstance } from 'vue';
const { proxy } = getCurrentInstance()

// if (sessionStorage.getItem('dataStore')) {
//   proxy.$store.replaceState(
//     Object.assign(
//       {},
//       proxy.$store.state,
//       JSON.parse(sessionStorage.getItem('dataStore'))
//     )
//   )
// }

// window.addEventListener('beforeunload', () => {
//   sessionStorage.setItem('dataStore', JSON.stringify(proxy.$store.state))
// })
</script>

<template>
  <router-view></router-view>
</template>


<style>
</style>
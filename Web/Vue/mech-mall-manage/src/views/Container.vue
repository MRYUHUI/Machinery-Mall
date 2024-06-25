<script setup>
// import
import Header from '@/views/Header.vue'
import Footer from './Footer.vue';
</script>

<template>
  <div class="container">
    <Header></Header>
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>



<style>
</style>
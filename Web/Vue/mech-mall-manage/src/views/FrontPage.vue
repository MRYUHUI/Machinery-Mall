<script setup>
import HomeCategoryVue from './Home/HomeCategory.vue'
import HomeHot from './Home/HomeHot.vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const goToCP = () => {
  router.push({ name: 'category-good-detail' })
}
</script>

<template>
  <div class="front-page">
    <el-button @click="goToCP">go</el-button>
    <HomeHot></HomeHot>
    <HomeCategoryVue></HomeCategoryVue>
  </div>
</template>
<style>
</style>
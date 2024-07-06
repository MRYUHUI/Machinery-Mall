<script setup>
import HomeCategoryVue from './Home/HomeCategory.vue'
import HomeHot from './Home/HomeHot.vue'
import { useRouter } from 'vue-router'
import MainComponent from '@/components/MainComponent.vue'
import Carousel from './Home/Carousel.vue'
const router = useRouter()
const goToCP = () => {
  router.push({ name: 'category-good-detail' })
}
</script>

<template>
  <div class="front-page">
    <MainComponent></MainComponent>
    <Carousel></Carousel>
    <HomeHot></HomeHot>
    <HomeCategoryVue></HomeCategoryVue>
  </div>
</template>
<style>
</style>
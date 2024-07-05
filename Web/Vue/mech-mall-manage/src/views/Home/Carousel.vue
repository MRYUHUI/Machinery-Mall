<script setup>
import { ref, onMounted } from "vue";
import apiRequests from "@/apis";

const carouselList = ref([]);

const findAllCarousel = async () => {
  try {
    const { data: res } = await apiRequests.findAllCarousel();
    carouselList.value = res;
  } catch (error) {
    console.error(error);
  }
};

onMounted(() => {
  findAllCarousel();
});
</script>

<template>
  <el-carousel
    v-if="carouselList.length"
    class="swiper-container"
    type="card"
    height="20vw"
    :interval="1000"
  >
    <el-carousel-item v-for="(item, index) in carouselList" :key="index">
      <img :src="apiRequests.attachImageUrl(item.imgUrl)" />
    </el-carousel-item>
  </el-carousel>
</template>

<style scoped>
.swiper-container {
  width: 90%;
  margin: auto;
  padding-top: 20px;
}
img {
  width: 100%;
}
.swiper-container:deep(
    .el-carousel__indicators.el-carousel__indicators--outside
  ) {
  display: inline-block;
  transform: translateX(30vw);
}
</style>

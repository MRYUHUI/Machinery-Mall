<script setup>
import apiRequests from '@/apis';
import { defineProps } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
const router = useRouter()
const store = useStore()
const props = defineProps({
  product: {
    type: Object,
    required: true
  }
});

const goToDetail = () => {
  const curProduct = props.product
  store.dispatch('updateSelectedProductInfo', curProduct)
  router.push({ name: 'good-detail' })

}
</script>

<template>
  <el-card class="product-card setPoint" @click="goToDetail">
    <el-image
      :src="apiRequests.attachImageUrl(product.iconUrl)"
      class="product-image"
    />
    <div class="product-info">
      <h4 class="product-name">{{ product.name }}</h4>
      <p class="product-price">&yen;{{ product.price }}</p>
    </div>
  </el-card>
</template>

<style scoped>
.product-card {
  overflow: hidden;
  border-radius: 10px;
  transition: transform 0.3s ease;
  width: 220px;
  height: 300px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  background-color: #ffffff;
}

.product-card:hover {
  transition: all 0.3s;
  transform: translateY(-5px);
  box-shadow: 0 0 10px #00000058;
}

.product-image {
  width: 200px;
  height: 200px;
  object-fit: cover;
}

.product-info {
  text-align: center;
  padding: 10px 0;
  flex-grow: 1;
  display: flex;
  flex-direction: column;
  justify-content: center;
}

.product-name {
  font-size: 18px;
  margin: 10px 0 5px;
}

.product-price {
  font-size: 16px;
  color: #f56c6c;
  margin-bottom: 10px;
}
</style>

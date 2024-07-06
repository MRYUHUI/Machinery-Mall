<script setup>
import { defineProps } from 'vue';
import GoodItem from './GoodItem.vue';

const props = defineProps({
  products: {
    type: Array,
    required: true
  }
});
</script>

<template>
  <div class="product-list">
    <ul class="goods-list">
      <li v-for="item in products" :key="item.id" class="goods-list-item">
        <GoodItem :product="item"></GoodItem>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.product-list {
  padding: 10px;
  background-color: #f0f0f0;
}

.goods-list {
  display: flex;
  flex-wrap: wrap; /* 允许换行 */
  gap: 40px;
  padding: 0 20px;
  list-style: none;
  margin: 0;
}

.goods-list-item {
  box-sizing: border-box;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  margin-bottom: 20px; /* 添加底部间距 */
}
</style>

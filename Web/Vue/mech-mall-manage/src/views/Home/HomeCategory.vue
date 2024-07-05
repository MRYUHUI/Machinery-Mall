<script setup>
import apiRequests from '@/apis';
import { onMounted, ref } from 'vue';
import HomePanel from './HomePanel.vue';
import GoodItem from '@/components/commodity/GoodItem.vue';
import { useRouter } from 'vue-router';
const router = useRouter()
const productListOrderByCategory = ref([]);
const displayGoodNum = 5
const findAllProductOrderByHighestCategory = async () => {
  const { data: res } = await apiRequests.findAllProductOrderByHighestCategory(displayGoodNum);

  productListOrderByCategory.value = res;
};

onMounted(() => {
  findAllProductOrderByHighestCategory();
});

const goToHotDetails = () => {
  router.push({ name: 'hot-detail' })
}
</script>

<template>
  <div class="home-category">
    <!-- <el-button @click="goToHotDetails">更多</el-button> -->

    <div
      v-for="(products, categoryName) in productListOrderByCategory"
      :key="categoryName"
      class="category-section"
    >
      <HomePanel :main-title="categoryName" sub-title="新鲜出炉 品质靠谱">
        <ul class="goods-list">
          <li v-for="item in products" :key="item.id" class="goods-list-item">
            <GoodItem :product="item"></GoodItem>
          </li>
        </ul>
      </HomePanel>
    </div>
  </div>
</template>


<style scoped>
.el-button {
  position: absolute;
  right: 10px;
  margin: 10px 60px;
}
.home-category {
  padding: 10px;

  background-color: #f0f0f0;
  position: relative;
}

.goods-list {
  display: flex;
  gap: 40px;
  padding: 0 20px;
  list-style: none;
  margin: 0;
}

.goods-list-item {
  box-sizing: border-box;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  display: flex;
  margin-bottom: 20px; /* 添加底部间距 */
}
</style>

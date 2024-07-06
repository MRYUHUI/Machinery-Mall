<script setup>
import DisplayGoods from '../../components/commodity/DisplayGoods.vue';
import { onMounted, ref, computed } from 'vue';
import { useStore } from 'vuex';
import apiRequests from '@/apis';
import Breadcrum from '@/components/Breadcrumb.vue';

const store = useStore();
const searchGoodList = ref([]);

const findSearchCommodity = async (keyword) => {
  const { data: res } = await apiRequests.findSearchCommodity(keyword);
  searchGoodList.value = res;
  console.log(res);
};

const searchQuery = computed(() => store.getters.searchQuery);

// 判断搜索结果是否为空
const isEmpty = computed(() => searchGoodList.value.length === 0);

onMounted(() => {
    findSearchCommodity(searchQuery.value);
});
</script>

<template>
  <Breadcrum></Breadcrum>
  <div v-if="isEmpty">
    <div class="no-goods">找不到商品</div>
  </div>
  <DisplayGoods v-else :products="searchGoodList"></DisplayGoods>
</template>

<style>
.no-goods {
  text-align: center;
  margin-top: 200px;
  color: rgba(102, 102, 102, 0.8);
  font-size: 24px;
  font-weight: bold;
}
</style>

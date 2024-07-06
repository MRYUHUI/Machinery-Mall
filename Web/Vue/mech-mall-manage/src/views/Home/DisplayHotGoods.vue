<script setup>
import DisplayGoods from '../../components/commodity/DisplayGoods.vue';
import { onMounted, ref } from 'vue'
import apiRequests from '@/apis';
import Breadcrum from '@/components/Breadcrumb.vue'
const hotGoodList = ref([])
const findAllHotGoods = async () => {
  const { data: res } = await apiRequests.findAllHotCommodity()
  hotGoodList.value = res
}
onMounted(() => {
  findAllHotGoods()


})
</script>

<template>
  <Breadcrum></Breadcrum>
  <DisplayGoods :products="hotGoodList"></DisplayGoods>
</template>



<style>
</style>
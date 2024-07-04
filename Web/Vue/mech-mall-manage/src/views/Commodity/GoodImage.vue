<script setup>
import { ref, computed, watch, onMounted } from 'vue';
import { useMouseInElement } from '@vueuse/core';
import apiRequests from '@/apis';
import { useStore } from 'vuex';

const store = useStore();

// 当前商品
const curProduct = computed(() => store.getters.selectedProductInfo);

const baseLen = 200

// 小滑块位置
const left = ref(0);
const top = ref(0);

// 获取鼠标相对位置
const target = ref(null)
const { elementX, elementY, isOutside } = useMouseInElement(target)

// 计算放大镜效果的背景位置
const posX = computed(() => -left.value * 2);
const posY = computed(() => -top.value * 2);

// 监听鼠标位置变化，更新小滑块位置和放大镜效果位置
watch([elementX, elementY], () => {
  if (!isOutside.value) {
    left.value = Math.max(0, Math.min(baseLen, elementX.value - baseLen / 2));
    top.value = Math.max(0, Math.min(baseLen, elementY.value - baseLen / 2));
  }
});

onMounted(() => {
  console.log(apiRequests.attachImageUrl(curProduct.value.iconUrl));

})
</script>

<template>
  <div class="goods-image">
    <!-- 左侧大图-->
    <div class="middle" ref="target">
      <el-image
        :src="apiRequests.attachImageUrl(curProduct.iconUrl)"
        alt=""
        class="main-image"
      />
      <!-- 蒙层小滑块 -->
      <div
        class="layer"
        v-show="!isOutside"
        :style="{ left: `${left}px`, top: `${top}px` }"
      ></div>
    </div>
    <!-- 放大镜大图 -->
    <div
      class="large"
      v-show="!isOutside"
      :style="{
        backgroundImage: `url(${apiRequests.attachImageUrl(
          curProduct.iconUrl
        )})`,
        backgroundPositionX: `${posX}px`,
        backgroundPositionY: `${posY}px`,
      }"
    ></div>
  </div>
</template>

<style scoped>
.goods-image {
  width: 480px;
  height: 400px;
  position: relative;
  display: flex;
}

.goods-image .middle {
  width: 400px;
  height: 400px;
  background: #f5f5f5;
  position: relative;
}

.goods-image .main-image {
  width: 400px;
  height: 400px;
}

.goods-image .large {
  position: absolute;
  top: 0;
  left: 480px; /* 调整放大镜位置 */
  width: 400px;
  height: 400px;
  z-index: 1000;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  background-repeat: no-repeat;
  background-size: 800px 800px;
  background-color: #f8f8f8;
}

.goods-image .layer {
  width: 200px;
  height: 200px;
  background: rgba(0, 0, 0, 0.2);
  left: 0;
  top: 0;
  position: absolute;
}
</style>

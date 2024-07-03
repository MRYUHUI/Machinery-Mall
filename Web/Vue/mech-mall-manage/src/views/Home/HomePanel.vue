<script setup>
import { defineProps } from 'vue';

const props = defineProps({
  mainTitle: {
    type: String,
    required: true
  },
  subTitle: {
    type: String,
    required: true
  }
});
</script>

<template>
  <div class="home-panel">
    <div class="container-panel">
      <div class="head">
        <!-- 主标题和副标题 -->
        <h3>
          <!-- 将父组件传过来的信息展示 -->
          <span>{{ props.mainTitle }}</span
          ><small>{{ props.subTitle }}</small>
        </h3>
      </div>
      <!-- 放入一个插槽 -->
      <slot></slot>
    </div>
  </div>
</template>

<style scoped>
.home-panel {
  background-color: #fffbfb;
}

.home-panel .head {
  padding: 40px 0;
  display: flex;
  align-items: flex-end;
}

.home-panel .head h3 {
  flex: 1;
  font-size: 32px;
  font-weight: normal;
  margin-left: 6px;
  height: 35px;
  line-height: 35px;
}

.home-panel .head h3 small {
  font-size: 16px;
  color: #999;
  margin-left: 20px;
}
span {
  font-weight: bolder;
}
</style>

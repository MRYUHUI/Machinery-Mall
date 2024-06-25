<script setup>
import { SystemConsts } from '@/enums/SystemConsts';
import { ref } from 'vue'
const projectName = ref(SystemConsts.PROJECT_NAME)
</script>

<template>
  <div class="header">
    <div class="name">
      <span>{{ projectName }}</span>
    </div>
  </div>
</template>


<style>
.header {
  background-color: #fff;
  width: 100vw;
  height: 80px;
}
</style>
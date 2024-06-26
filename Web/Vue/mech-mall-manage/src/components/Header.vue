<script setup>
import { SystemConsts } from '@/enums/SystemConsts';
import { ref } from 'vue'
const projectName = ref(SystemConsts.PROJECT_NAME)
</script>

<template>
  <div class="header">
    <div class="name setPoint">
      <span>{{ projectName }}</span>
    </div>
  </div>
</template>


<style>
.header {
  background-color: #fff;
  width: 100vw;
  height: 80px;
  display: flex;
  align-items: center;
  padding-left: 10px;
  box-shadow: 0 5px #000000a4;
}
.name {
  border: 2px solid #d000ffa3;
  padding: 3px;
  border-radius: 10px;
  background-color: #ed5ad7be;
  color: aliceblue;
}
</style>
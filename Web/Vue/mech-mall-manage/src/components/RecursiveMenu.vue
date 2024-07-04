<script setup>
import { defineProps } from 'vue';
import { useStore } from "vuex";

const store = useStore();
const props = defineProps({
  item: Object
});

const handleClick = (id) => {
  store.commit('setLeafNode', id);
  // console.log(id);
}
</script>

<template>
    <el-submenu v-if="item.children && item.children.length" :index="String(item.id)">
      <template #title>
        {{ item.name }}
      </template>
      <recursive-menu
        v-for="child in item.children"
        :key="child.id"
        :item="child"
      />
    </el-submenu>
    <el-menu-item 
    v-else 
    :index="String(item.id)" 
    @click="handleClick(item.id)">
    {{ item.name }}
  </el-menu-item>
</template>


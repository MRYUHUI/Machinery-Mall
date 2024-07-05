<script setup>
import { defineProps } from 'vue';
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
const router = useRouter()
const store = useStore();
const props = defineProps({
  item: Object,
  level: {
    type: Number,
    default: 1
  }
});

const handleClickNode = (id, nodeLevel) => {
  if (nodeLevel === 2) {
    store.commit('setSecondLevelNode', id);
  } else if (nodeLevel === 3) {
    store.commit('setThirdLevelNode', id);
  }
  router.push({ name: 'category-good-detail' });
}
</script>

<template>
  <el-submenu
    v-if="item.children && item.children.length"
    :index="String(item.id)"
  >
    <template #title>
      {{ item.name }}
    </template>
    <recursive-menu
      v-for="child in item.children"
      @click="handleClickNode(child.id, level + 1)"
      :key="child.id"
      :item="child"
      :level="level + 1"
    />
  </el-submenu>
  <el-menu-item v-else :index="String(item.id)">
    {{ item.name }}
  </el-menu-item>
</template>

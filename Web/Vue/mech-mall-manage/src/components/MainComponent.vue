
<script setup>
import { ref, onMounted } from 'vue';
import zhCn from 'element-plus/lib/locale/lang/zh-cn';
import apiRequests from "@/apis/good-type"; 
import RecursiveMenu from './RecursiveMenu.vue';

const activeMenu = ref('1'); 
const nestedCategories = ref([]);

// 获取id为parentId的所有子孙
async function getProductCategories(parentId) {
  try {
    const response = await apiRequests.getProductCategories(parentId);
    return response.data;
  } catch (error) {
    console.error("Error fetching product categories:", error);
    return [];
  }
}
// 实现树状Map
function buildNestedCategories(categories) {
  const categoryMap = new Map();
  categories.forEach(category => {
    category.children = [];
    categoryMap.set(category.id, category);
  });
  const nestedCategories = [];
  categories.forEach(category => {
    if (category.parentId === 0) {
      nestedCategories.push(category);
    } else {
      const parentCategory = categoryMap.get(category.parentId);
      if (parentCategory) {
        parentCategory.children.push(category);
      }
    }
  });
  return nestedCategories;
}

// 钩子
onMounted(async () => {
  const categories = await getProductCategories(0);
  nestedCategories.value = buildNestedCategories(categories);
  console.log(nestedCategories.value)
});
</script>

<template>
  <el-config-provider :locale="zhCn">
    <el-menu class="custom-menu" mode="horizontal">
      <!-- 递归标签 -->
      <recursive-menu
        v-for="item in nestedCategories"
        :key="item.id"
        :item="item"
      />
    </el-menu>
  </el-config-provider>
</template>

<style scoped>
.custom-menu {
  width: 60%;
  background-color: #FFFFFF;
  border-radius: 8px;
  display: flex;
  justify-content: space-around;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.el-menu-item, .el-submenu__title {
  color: #000000;
  font-size: 16px;
}

.el-menu-item:hover, .el-submenu__title:hover {
  background-color: #F0F0F0;
  color: #000000;
}
</style>


<script setup>
import { ref, onMounted } from 'vue';
import zhCn from 'element-plus/lib/locale/lang/zh-cn';
import apiRequests from "@/apis/good-type";
import RecursiveMenu from './RecursiveMenu.vue';
import { useStore } from "vuex";
import { useRouter } from 'vue-router'
const router = useRouter()
const store = useStore();
const nestedCategories = ref([]);

// 获取id为parentId的所有子孙
async function getProductCategories (parentId) {
  try {
    const response = await apiRequests.getProductCategories(parentId);
    return response.data;
  } catch (error) {
    console.error("Error fetching product categories:", error);
    return [];
  }
}
// 实现树状Map
function buildNestedCategories (categories) {
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

const handleClickRoot = (id) => {
  store.commit('setRootNode', id);
  router.push({ name: 'category-good-detail' });
}

// 钩子
onMounted(async () => {
  const categories = await getProductCategories(0);
  nestedCategories.value = buildNestedCategories(categories);
  // console.log(nestedCategories.value)
});
</script>

<template>
  <div class="nav-container">
    <el-config-provider :locale="zhCn">
      <el-menu class="custom-menu" mode="horizontal">
        <!-- 递归标签 -->
        <recursive-menu
          v-for="item in nestedCategories"
          @click="handleClickRoot(item.id)"
          :key="item.id"
          :item="item"
        />
      </el-menu>
    </el-config-provider>
  </div>
</template>

<style scoped>
.custom-menu {
  width: 100%;
  background-color: #ffffff;
  border-radius: 8px;
  display: flex;
  justify-content: space-around;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  border-bottom: 1px solid rgba(0, 0, 0, 0.212);
}

.el-menu-item,
.el-submenu__title {
  color: #000000;
  font-size: 16px;
}

.el-menu-item:hover,
.el-submenu__title:hover {
  background-color: #f0f0f0;
  color: #000000;
}
.nav-container {
  width: 800px;
  display: flex;
  justify-content: center;
  margin-left: 350px;
}
</style>

<script setup>
import apiRequests from '@/apis';
import { ref, onMounted, computed } from 'vue';
import { useStore } from 'vuex';
import Breadcrumb from '@/components/Breadcrumb.vue'
import DisplayGoods from '@/components/commodity/DisplayGoods.vue'
const store = useStore();

// 数据定义
const firstCateList = ref([]); // 存储一级分类列表
const secCateList = ref([]); // 存储二级分类列表
const thirdCateList = ref([]); // 存储三级分类列表
const displayGoodList = ref([]) //要展示的商品列表

// 根据 parentId 获取子分类
const findAllChildCateByParentId = async (parentId, level) => {
  try {
    const { data: res } = await apiRequests.findCategoriesByParentId(parentId); // 获取子分类数据

    if (level === 1) {
      firstCateList.value = res; // 更新一级分类列表
      // 获取当前一级分类的子分类
      for (let category of res) {
        if (store.getters.rootNode === category.id) {
          await findAllChildCateByParentId(category.id, 2);
        }
      }
    } else if (level === 2) {
      secCateList.value = res; // 更新二级分类列表
      // 获取所有二级分类的子分类
      for (let subCategory of res) {
        await findAllChildCateByParentId(subCategory.id, 3);
      }
    } else if (level === 3) {
      thirdCateList.value = res; // 更新三级分类列表
    }
  } catch (error) {
    console.error('Error fetching categories:', error); // 错误处理
  }
};

// 初始化获取所有分类
const fetchInitialCategories = async () => {
  await findAllChildCateByParentId(0, 1); // 获取所有一级分类
  await findAllProductsByThirdCategory(thirdNode.value)
};

// 切换三级分类
const changeThirdNode = async (id) => {
  store.commit('setThirdLevelNode', id)
  await findAllProductsByThirdCategory(id)
};
// 切换一级分类
const changeRootNode = (id) => {
  findAllChildCateByParentId(id, 2)
  store.commit('setRootNode', id)

}

// 根据三级分类获取所有的商品
const findAllProductsByThirdCategory = async (id) => {
  const { data: res } = await apiRequests.findAllProductsByPartsId(id)
  displayGoodList.value = res
}
// computed =============================================
// 计算属性保留，获取当前应激活的分类信息
const rootNode = computed(() => store.getters.rootNode); // 计算一级分类
const secondNode = computed(() => store.getters.secondLevelNode); // 计算二级分类
const thirdNode = computed(() => store.getters.thirdLevelNode); // 计算三级分类

// 组件挂载时调用初始化函数
onMounted(() => fetchInitialCategories());
</script>


<template>
  <Breadcrumb></Breadcrumb>
  <div class="category-container">
    <!-- 商品类型 -->
    <div class="category-type">
      <h4 class="category-title">商品类型</h4>
      <el-row type="flex" justify="start">
        <el-button
          v-for="category in firstCateList"
          :key="category.id"
          :class="{ active: rootNode === category.id }"
          @click="changeRootNode(category.id)"
          type="text"
          class="category-item"
        >
          {{ category.name }}
        </el-button>
      </el-row>
    </div>

    <el-divider></el-divider>

    <!-- 配件类型 -->
    <div class="accessory-type">
      <h4 class="category-title">配件类型</h4>
      <div class="subcategory-container">
        <el-row
          v-for="subcategory in secCateList"
          :key="subcategory.id"
          class="subcategory-row"
          type="flex"
          justify="start"
          align="top"
        >
          <el-col :span="4" class="subcategory-title">
            {{ subcategory.name }}
          </el-col>
          <el-col :span="20" class="subcategory-items">
            <el-row type="flex" justify="start">
              <el-button
                v-for="thirdCategory in thirdCateList.filter(
                  (tc) => tc.parentId === subcategory.id
                )"
                :key="thirdCategory.id"
                :class="{ active: thirdNode === thirdCategory.id }"
                @click="changeThirdNode(thirdCategory.id)"
                type="text"
                class="category-item"
              >
                {{ thirdCategory.name }}
              </el-button>
            </el-row>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
  <div class="display-goods">
    <DisplayGoods :products="displayGoodList"></DisplayGoods>
  </div>
</template>


<style scoped>
.category-container {
  border: 1px solid #ccc;
  padding: 10px;
  background-color: #ffffff;
  margin: 10px 50px;
}
.subcategory-container {
  font-size: 16px; /* 调整二级分类字体大小 */
  color: rgb(80, 80, 80);
}

.category-type {
  display: flex;
  align-items: center;
}
.accessory-type {
  display: flex;
  align-items: center;
}

.category-item {
  padding: 5px 10px;
  cursor: pointer;
  border: 1px solid transparent;
  font-size: 14px; /* 调整一级和三级分类字体大小 */
  color: #000; /* 设置分类字体为黑色 */
}
.category-item {
  transition: all 0.3s;
}
.category-item.active {
  border-color: rgba(255, 0, 153, 0.372);
  /* background-color: #ff21c872; */
  box-shadow: 0 0 5px #00000051;
  font-weight: bolder;
}

.subcategory-title {
  font-weight: bold;
  display: flex;
  align-items: center;
  width: 400px;
}

.subcategory-items {
  display: flex;
  /* flex-wrap: wrap; */
}

.el-divider {
  margin: 20px 0;
}

.el-button {
  font-size: 14px;
}
.category-title {
  margin: 0 50px 0 10px;
}
.display-goods {
  margin: 5px 35px;
}
</style>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { Icons } from '@/enums/Icons'
import IconItem from '@/components/IconItem.vue'

// data ==================
const store = useStore();
const router = useRouter();
const icons = {
  orderIcon: Icons.USER,
  addressIcon: Icons.GOOD_TYPE,
  cartTypeIcon: Icons.GOODS
}

// method ===================
const goToMyOrderPage = () => {
  // 设置被激活的页面index = 1
  store.commit('setActiveMyMallHomeIndex', 1)
  router.replace({ name: 'order-user' })
}

// 跳转我的购物车界面
const goToMyCartPage = () => {
  // 设置被激活的页面index = 2
  store.commit('setActiveMyMallHomeIndex', 2)
  router.replace({ name: 'cart-user' })
}

// 跳转地址管理界面
const goToAddressManagementPage = () => {
  // 设置被激活的页面index = 3
  store.commit('setActiveMyMallHomeIndex', 3)
  router.replace({ name: 'address-user' })
}

// computed =========================
const curActiveMyMallHomeIndex = computed(() => store.getters.activeMyMallHomeIndex)
onMounted(() => {
  if (curActiveMyMallHomeIndex.value === 2) {
    router.replace({ name: 'cart-user' })
  }
  else if (curActiveMyMallHomeIndex.value === 3) {
    router.replace({ name: 'address-user' })
  }
})
</script>

<template>
  <div class="user-container">
    <!-- 添加一个容器元素来调整位置 -->
    <div class="aside-container">
      <!-- 侧边栏部分 -->
      <el-aside
        class="el-aside"
        width="240px"
        style="background-color: #fff"
      >
        <el-menu
          class="menu-list"
          :default-active="'' + curActiveMyMallHomeIndex"
          style="height: 100%"
          :defaultOpeneds="['1']"
        >
          <el-menu-item index="1" @click="goToMyOrderPage">
            <div class="menu-item">
              <icon-item :icon="icons.orderIcon"></icon-item>
              <span class="menu-item-title">我的订单</span>
            </div>
          </el-menu-item>
          <el-menu-item index="2" @click="goToMyCartPage">
            <div class="menu-item">
              <icon-item :icon="icons.cartTypeIcon"></icon-item>
              <span class="menu-item-title">我的购物车</span>
            </div>
          </el-menu-item>
          <el-menu-item index="3" @click="goToAddressManagementPage">
            <div class="menu-item">
              <icon-item :icon="icons.addressIcon"></icon-item>
              <span class="menu-item-title">收货地址管理</span>
            </div>
          </el-menu-item>
        </el-menu>
      </el-aside>
    </div>
    <!-- 展示主体内容 -->
    <router-view></router-view>
  </div>
</template>

<style scoped>
.aside-container {
  margin-top: 20px; /* 添加 margin-top 来整体移动位置 */
}

.el-aside {
  box-shadow: 0 0 5px #0000003a;
  border-radius: 5px;
  height: 600px;
  background-color: #333744;
  padding-left: 10px;
}

.el-menu {
  padding: 0;
  width: 100%;
  border-right: none;
}

.menu-item {
  display: flex;
  align-items: center;
  gap: 10px;
}

.menu-item-title {
  font-weight: bold;
}

.user-container {
  display: flex;
  gap: 30px;
}
</style>

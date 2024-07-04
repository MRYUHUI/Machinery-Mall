<script setup>
import { ref, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { Icons } from '@/enums/Icons'
import IconItem from '@/components/IconItem.vue'
import EditUserInfo from '@/components/EditUserInfo.vue'
// data ==================
const store = useStore();
const router = useRouter();
const isCollapse = ref(false)
const icons = {
  orderIcon: Icons.USER,
  addressIcon: Icons.GOOD_TYPE,
  cartTypeIcon: Icons.GOODS
}
// method ===================
const toggleCollapse = () => isCollapse.value = !isCollapse.value
// 跳转我的订单界面
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
  <div class="admin-container">
    <!-- 侧边栏部分 -->
    <el-aside
      class="el-aside"
      :width="isCollapse ? '75px' : '240px'"
      style="background-color: #fff"
    >
      <el-menu
        class="menu-list"
        :default-active="'' + curActivePageIndex"
        :collapse="isCollapse"
        style="height: 100%"
        :defaultOpeneds="['1']"
      >
        <div class="toggle-button button-attr" @click="toggleCollapse">|||</div>
        <el-menu-item index="1" @click="goToMyOrderPage">
          <div class="menu-item">
            <icon-item :icon="icons.orderIcon"></icon-item>
            <span class="menu-item-title" v-show="!isCollapse">我的订单</span>
          </div>
        </el-menu-item>
        <el-menu-item index="2" @click="goToMyCartPage">
          <div class="menu-item">
            <icon-item :icon="icons.cartTypeIcon"></icon-item>
            <span class="menu-item-title" v-show="!isCollapse">我的购物车</span>
          </div>
        </el-menu-item>
        <el-menu-item index="3" @click="goToAddressManagementPage">
          <div class="menu-item">
            <icon-item :icon="icons.addressIcon"></icon-item>
            <span class="menu-item-title" v-show="!isCollapse">收货地址管理</span>
          </div>
        </el-menu-item>
      </el-menu>
    </el-aside>
    <!-- 展示主体内容 -->
    <router-view></router-view>

    <!-- 编辑用户信息对话框 -->
    <EditUserInfo></EditUserInfo>
  </div>
</template>

<style scoped>
.el-aside {
  box-shadow: 0 0 5px #0000003a;
  border-radius: 5px;
  height: 700px;
  transition: 1s;
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
  position: absolute;
  top: 50%;
  left: 30%;
  transform: translate(-50%, -50%);
}

.menu-item-title {
  font-weight: bold;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  font-weight: bold;
  width: auto;
}

.button-attr {
  border-radius: 5px;
  box-shadow: 0 1px 5px rgba(10, 85, 85, 0.687);
  transition: all 1s;
}

.button-attr:hover {
  transition: all 1s;
  background: linear-gradient(to right, #3498db, #2ecc71);
  cursor: pointer;
  box-shadow: 0 0 20px rgb(5, 215, 215);
}

.admin-container {
  display: flex;
  gap: 30px;
}
</style>

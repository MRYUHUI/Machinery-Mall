<script setup>
import AdminHeaderVue from '@/components/admin/AdminHeader.vue';
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
const store = useStore();
const router = useRouter();
const isCollapse = ref(false)


const toggleCollapse = () => isCollapse.value = !isCollapse.value
</script>

<template>
  <el-aside
    class="el-aside"
    :width="isCollapse ? '75px' : '240px'"
    style="background-color: #fff"
  >
    <el-menu
      class="menu-list"
      :default-active="defaultActiveItemInAside"
      :collapse="isCollapse"
      style="height: 100%"
      :defaultOpeneds="['1']"
    >
      <div class="toggle-button button-attr" @click="toggleCollapse">|||</div>
      <el-menu-item index="1">
        <div class="menu-item">
          <!-- <icon-item :icon="iconList.UserManagement"></icon-item> -->
          <span class="menu-item-title" v-show="!isCollapse">用户管理</span>
        </div>
      </el-menu-item>
      <el-menu-item index="2">
        <div class="menu-item">
          <!-- <icon-item :icon="iconList.TypeManagement"></icon-item> -->
          <span class="menu-item-title" v-show="!isCollapse">类型管理</span>
        </div>
      </el-menu-item>
      <el-menu-item index="3">
        <div class="menu-item">
          <!-- <icon-item :icon="iconList.ProductManagement"></icon-item> -->
          <span class="menu-item-title" v-show="!isCollapse">商品管理</span>
        </div>
      </el-menu-item>
      <el-menu-item index="4">
        <div class="menu-item">
          <!-- <icon-item :icon="iconList.OrderManagement"></icon-item> -->
          <span class="menu-item-title" v-show="!isCollapse">订单管理</span>
        </div>
      </el-menu-item>
    </el-menu>
  </el-aside>
</template>

<style scoped>
/* 样式可以根据需求自行调整 */
.el-aside {
  box-shadow: 0 0 5px #0000003a;
  border-radius: 5px;
  height: 850px;
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
</style>

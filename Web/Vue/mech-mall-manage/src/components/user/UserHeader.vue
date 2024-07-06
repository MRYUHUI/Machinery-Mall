<script setup>
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { computed, ref } from 'vue';
import { SystemConsts } from '@/enums/SystemConsts';
const store = useStore();
const router = useRouter();
const searchQuery = ref("")
const username = computed(() => store.getters.account);
const projectName = SystemConsts.PROJECT_NAME;
const goToProfile = () => {
  store.dispatch('updateSelectedUserInfo', store.getters.curUserInfo);
  store.commit('setEditUserInfoDiaVisible', true);
};

const changePassword = () => {
  store.commit('setChangePwdPageVisible', true);
};

const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('my-app-state');
  store.commit('setIsLogin', false);
  router.replace({ path: '/' });
};

const isLogin = computed(() => store.getters.isLogin);

const goToLogin = () => {
  router.push({ name: 'login' });
};
const goToMyMallHome = () => {
  router.push({ name: 'mall-home' });
};

const goToRegister = () => {
  router.push({ name: 'register' });
};

const goToHome = () => {
  router.push({ name: 'user-home' });
}

// 搜索函数
const handleSearch = () => {
  store.commit('setSearchQuery', searchQuery.value)
  router.push({ name: 'search-detail' })
};
</script>

<template>
  <div class="user-header">
    <h3 class="logo setPoint" @click="goToHome">{{ projectName }}</h3>
    <!-- 搜索框 -->
    <el-input
      class="search-input"
      placeholder="搜索..."
      prefix-icon="el-icon-search"
      v-model="searchQuery"
      @keyup.enter="handleSearch"
    ></el-input>
    <div class="hasLogin" v-if="isLogin">
      <el-button class="mall-button" @click="goToMyMallHome"
        >我的商城</el-button
      >
      <el-dropdown>
        <div class="user-button">
          Hi, {{ username }}<el-icon class="el-icon--right"></el-icon>
        </div>
        <template #dropdown>
          <div>
            <el-dropdown-menu>
              <el-dropdown-item class="custom-item" @click="goToProfile"
                >编辑信息</el-dropdown-item
              >
              <el-dropdown-item class="custom-item" @click="changePassword"
                >修改密码</el-dropdown-item
              >
              <el-dropdown-item class="custom-item" @click="logout"
                >登出</el-dropdown-item
              >
            </el-dropdown-menu>
          </div>
        </template>
      </el-dropdown>
    </div>
    <div class="notLogin" v-else>
      <el-button type="primary" @click="goToLogin">登录</el-button>
      <el-button @click="goToRegister">注册</el-button>
    </div>
  </div>
  <div class="main-content">
    <!-- Your main content goes here -->
  </div>
</template>

<style scoped>
.user-header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 10px 30px;
  width: 100%;
  box-shadow: 0 0 10px #000000a7;
  height: 60px;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1000;
  background-color: #f0f0f0;
}

.el-dropdown {
  margin-left: auto;
}

.custom-item:hover {
  background-color: #f0f0f0;
  color: #333;
}

.user-button {
  display: flex;
  align-items: center;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-size: 18px;
  font-weight: bold;
  margin-right: 30px;
}

.user-button:hover {
  background-color: #3498db;
  color: #fff;
}

.logo {
  margin-left: 20px;
  margin-right: auto;
  transition: all 0.2s;
  border-radius: 10px;
}
.logo:hover {
  transform: scale(1.1);
  box-shadow: 0 0 10px #000000da;
  background-color: #5f5f5f;
  color: #f8f9fa;
}
.logo:active {
  transform: scale(0.8);
}
.notLogin {
  margin-right: 50px;
}

.el-button {
  height: 30px;
  padding: 0;
  width: 80px;
}

.hasLogin {
  transform: translateX(-30px);
}

.main-content {
  padding-top: 80px;
}

.search-input {
  max-width: 600px;
  margin-right: auto;
}
</style>

<!-- 导航栏部分 -->
<template>
  <div class="admin-header">
    <h1 class="loge">机械<span>平台</span></h1>
    <el-dropdown>
      <div class="admin-button">
        你好！管理员<el-icon class="el-icon--right"><arrow-down /></el-icon>
      </div>
      <template #dropdown>
        <el-dropdown-menu>
          <el-dropdown-item class="custom-item">编辑资料</el-dropdown-item>
          <el-dropdown-item class="custom-item" @click="goToLoginPage">退出登录</el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const goToLoginPage = () => {
  // // 设置被激活的页面index = 1
  // store.commit('setActivePageIndex', 1)
  localStorage.removeItem('token')
  router.replace({ path: '/' })
}
// onMounted(() => {
//   router.push({ name: 'login' })
// })
</script>

<style scoped>
 /* 管理后台导航内容 */
.admin-header {
  display: flex;
  justify-content: flex-end; /* 将内容右对齐 */
  align-items: center; /* 垂直居中 */
  margin-top: 15px; /* 上间距 */
  margin-bottom: 15px; /* 下间距 */
  margin-left: 5px;
  margin-right: 5px;

  padding: 50px 80px;
  width: 1370px;
  height: 1px;
  border-radius: 10px;
  box-shadow: 0 0 10px #000000a7;
}
/* 导航右侧管理员块 */
.admin-header .el-dropdown {
  margin-left: auto; /* 可选：调整左侧间距 */
  margin-right: 20px;
  margin-top: 20px; /* 上间距 */
  margin-bottom: 20px; /* 下间距 */
}

.custom-item:hover {
  background-color: #f0f0f0; /* 设置鼠标悬停时的背景色 */
  color: #333; /* 设置鼠标悬停时的文字颜色 */
}

.admin-button {
  display: flex;
  align-items: center;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  font-size: 18px; /* 字体大小 */
  font-weight: bold; /* 字体粗细 */
}

.admin-button:hover {
  background-color: #3498db; /* 鼠标悬停时的背景颜色 */
  color: #fff; /* 鼠标悬停时的文字颜色 */
}

/* 导航左侧loge */
.loge {
  margin-left: 20px; /* 可选：调整左侧间距 */
  margin-right: auto;
  margin-top: 20px; /* 上间距 */
  margin-bottom: 20px; /* 下间距 */
}
</style>

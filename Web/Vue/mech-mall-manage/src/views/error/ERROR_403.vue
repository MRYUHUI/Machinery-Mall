<!-- Forbidden.vue -->

<template>
  <div class="error-page">
    <h1>403</h1>
    <p>没有权限访问</p>
    <router-link to="/">返回首页</router-link>
  </div>
</template>

<script>
export default {
  name: "Forbidden", // 组件名称
};
</script>

<style scoped>
.error-page {
  text-align: center;
  margin-top: 20px;
}

.error-page h1 {
  font-size: 100px;
  margin-bottom: 20px;
  color: #f44336; /* 错误页面标题颜色 */
}

.error-page p {
  font-size: 24px;
  color: #333; /* 错误信息文字颜色 */
}

.error-page router-link {
  display: inline-block;
  margin-top: 20px;
  font-size: 16px;
  color: #007bff; /* 链接文字颜色 */
  text-decoration: none;
}
</style>

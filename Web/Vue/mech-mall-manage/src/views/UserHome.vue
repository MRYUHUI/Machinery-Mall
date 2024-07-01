<script setup>
import UserHeaderVue from "@/components/user/UserHeader.vue";
import { useRouter } from "vue-router";
const router = useRouter()
const doLogin = () => {
  router.push({ name: 'login' })
}
const doRegister = () => {
  router.push({ name: 'register' })

}
</script>

<template>
  <div class="home">
    <UserHeaderVue> </UserHeaderVue>
    <button @click="doLogin">登录</button>
    <button @click="doRegister">注册</button>
    UserHome
  </div>
</template>



<style>
</style>
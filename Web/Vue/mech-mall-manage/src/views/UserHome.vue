<script setup>
import UserHeaderVue from "@/components/user/UserHeader.vue";
import ChangePasswordDia from "@/components/user/ChangePasswordDia.vue";
import EditUserInfo from "@/components/EditUserInfo.vue";
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
    <router-view></router-view>
    <ChangePasswordDia></ChangePasswordDia>
    <EditUserInfo></EditUserInfo>
  </div>
</template>



<style>
.home {
  width: 1600px;
}
</style>
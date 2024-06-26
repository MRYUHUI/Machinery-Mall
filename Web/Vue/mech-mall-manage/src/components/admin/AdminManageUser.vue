<script setup>
import apiRequests from "@/apis"
import { ref } from 'vue'
// data
const userList = ref([])


// method ===============
const getAllUsers = async () => {
  const res = await apiRequests.getAllUsers()
  console.log(res);
  userList.value = res.data
  userList = res.data

}
</script>

<template>
  <div class="admin-contianer">User</div>
</template>



<style>
</style>
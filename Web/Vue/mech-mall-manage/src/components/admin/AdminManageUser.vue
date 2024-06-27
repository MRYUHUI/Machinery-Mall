<script setup>
import apiRequests from "@/apis"
import { onMounted, ref } from 'vue'
import { SystemConsts } from "@/enums/SystemConsts";
// data
const userList = ref([])
const columnWidth = '100px'

// method ===============
// 获取所有用户
const getAllUsers = async () => {
  const res = await apiRequests.getAllUsers()
  userList.value = res.data
}

// 根据波尔值来显示男女
const formatGender = (row, column, cellValue) => {
  return cellValue ? '女' : '男';
}
// onMounted
onMounted(async () => {

  getAllUsers()
})
</script>

<template>
  <div class="admin-contianer">
    <!-- 用户创建歌单 -->
    <el-table
      :data="userList"
      style="width: 100%; height: 90%"
      class=""
      highlight-current-row
      @selection-change="handleSelectionChange"
      :header-cell-style="SystemConsts.headerCellStyle"
      :cell-style="SystemConsts.cellStyle"
    >
      <el-table-column
        type="selection"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="id"
        :width="columnWidth"
        label="编号"
        align="center"
      />
      <el-table-column
        prop="account"
        :width="columnWidth"
        label="用户名"
        align="center"
      />
      <el-table-column
        prop="name"
        :width="columnWidth"
        label="姓名"
        align="center"
      />
      <el-table-column
        label="性别"
        :width="columnWidth"
        align="center"
        :formatter="formatGender"
      />
      <el-table-column
        prop="age"
        :width="columnWidth"
        label="年龄"
        align="center"
      />
      <el-table-column
        prop="phone"
        :width="columnWidth"
        label="电话"
        align="center"
      />
      <el-table-column
        prop="email"
        :width="columnWidth"
        label="邮箱"
        align="center"
      />
      <el-table-column label="操作" :width="200" align="center">
        <template #default="{ row }">
          <el-button type="primary" size="mini" @click="handleEdit(row)"
            >编辑</el-button
          >
          <el-button type="danger" size="mini" @click="handleDelete(row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>



<style>
.admin-contianer {
  background-color: rgb(255, 255, 255);
}
</style>
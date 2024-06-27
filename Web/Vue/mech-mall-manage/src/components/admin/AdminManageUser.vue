<script setup>
import apiRequests from "@/apis"
import { computed, onMounted, ref, watch } from 'vue'
import { SystemConsts } from "@/enums/SystemConsts";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
// data
const store = useStore()
const userList = ref([])
const totalUsers = ref(0) // Total number of users for pagination
const columnWidth = '100px'
const currentPage = ref(1)
const pageSize = ref(10)
// 要删除的用户id
let curDelUserId = -1
// 警告提示
const deleteUserDialogVisible = ref(false)
// method ===============
// 获取所有用户
const getAllUsers = async (page = 1, size = 10) => {
  const res = await apiRequests.getAllUsers(page, size)
  // console.log(res);

  userList.value = res.data
  totalUsers.value = res.total
}

// 根据布尔值来显示男女
const formatGender = (row, column, cellValue) => {
  return cellValue ? '女' : '男';
}



// 页码改变时的处理函数
const handlePageChange = (page) => {
  currentPage.value = page
  getAllUsers(page, pageSize.value)
}

// 每页条数改变时的处理函数
const handleSizeChange = (size) => {
  pageSize.value = size
  getAllUsers(currentPage.value, size)
}

// 编辑用户信息
const handleEdit = (user) => {
  // 将要编辑的用户信息存入 store 里
  store.dispatch('updateSelectedUserInfo', user)
  // 显示编辑用户对话框
  store.commit('setEditUserInfoDiaVisible', true)
}

// 删除用户
const handleDelete = (user) => {
  deleteUserDialogVisible.value = true
  curDelUserId = user.id
}
// 删除用户动作
const deleteUserAction = async () => {
  const res = await apiRequests.deleteUser(curDelUserId)
  if (res.success) {
    deleteUserDialogVisible.value = false
    ElMessage.success(res.message)
    // 刷新
    getAllUsers(currentPage.value, pageSize.value)
  } else {
    ElMessage.error(res.message)
  }
}
// computed
const isAdminUserFresh = computed(() => store.getters.isAdminUserFresh)
watch(isAdminUserFresh, () => {
  getAllUsers(currentPage.value, pageSize.value)
})
// onMounted
onMounted(() => {
  getAllUsers(currentPage.value, pageSize.value)
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
        prop="sex"
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
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handlePageChange"
      :current-page="currentPage"
      :page-size="pageSize"
      :page-sizes="[1, 3, 5, 8, 10, 12, 20]"
      layout="total, sizes, prev, pager, next, jumper"
      :total="totalUsers"
    />
  </div>
  <!-- 删除用户警告 -->
  <el-dialog
    v-model="deleteUserDialogVisible"
    width="450px"
    destroy-on-close
    center
    class="delete-user-dia"
  >
    <!-- 使用 title 插槽自定义标题样式 -->
    <template #title>
      <div class="custom-dialog-title">警 告</div>
    </template>

    <span style="font-size: 20px">
      <strong>你是否要删除用户，确认后操作不可逆。</strong>
    </span>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="deleteUserDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="deleteUserAction">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>



<style>
.admin-contianer {
  background-color: rgb(255, 255, 255);
}
.el-table {
  overflow: auto;
}
.delete-user-dia {
  font-size: 26px;
}
.custom-dialog-title {
  font-size: 30px; /* 自定义标题字体大小 */
  text-align: center; /* 让标题居中 */
}
</style>
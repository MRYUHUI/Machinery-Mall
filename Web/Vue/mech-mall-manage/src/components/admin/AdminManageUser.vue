<script setup>
import apiRequests from "@/apis"
import { computed, onMounted, ref, watch } from 'vue'
import { SystemConsts } from "@/enums/SystemConsts";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import { ElConfigProvider } from 'element-plus'
import zhCn from 'element-plus/lib/locale/lang/zh-cn'
// data
const store = useStore()
const userList = ref([])
const totalUsers = ref(0)
const columnWidth = '100px'
const currentPage = ref(1)
const pageSize = ref(10)
// 要删除的用户id
let curDelUserId = -1
// 警告提示
const deleteUserDialogVisible = ref(false)
const searchQuery = ref('')

// method ===============
// 获取所有用户
const getAllUsers = async (page = 1, size = 10) => {
  try {
    const res = await apiRequests.getAllUsers(page, size)
    userList.value = res.data
    totalUsers.value = res.total
  } catch (error) {
    console.error("API请求失败:", error);
    // 输出具体的错误信息
    if (error.response) {
      // 如果有响应错误（例如 4xx 或 5xx 错误），可以输出响应的状态码和错误信息
      console.error("响应状态码:", error.response.status);
      console.error("错误详情:", error.response.data.message); // 假设后端返回的错误信息在data.message中
    } else if (error.request) {
      // 如果请求被发出但没有收到响应
      console.error("请求未收到响应:", error.request);
    } else {
      // 其他错误
      console.error("发生错误:", error.message);
    }
  }

}

// 搜索用户
const searchUsers = async (query, page = 1, size = 10) => {
  if (!query) {
    getAllUsers(page, size) // 如果查询为空，调用获取所有用户的函数
    return;
  }
  const res = await apiRequests.searchUsers(query, page, size);


  userList.value = res.data;
  totalUsers.value = res.total;
}

// 根据布尔值来显示男女
const formatGender = (row, column, cellValue) => {
  return cellValue ? '女' : '男';
}

// 页码改变时的处理函数
const handlePageChange = (page) => {
  currentPage.value = page
  searchUsers(searchQuery.value, page, pageSize.value)
}

// 每页条数改变时的处理函数
const handleSizeChange = (size) => {
  pageSize.value = size
  searchUsers(searchQuery.value, currentPage.value, size)
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
    searchUsers(searchQuery.value, currentPage.value, pageSize.value)
  } else {
    ElMessage.error(res.message)
  }
}

const handleSearch = async () => {
  searchUsers(searchQuery.value, currentPage.value, pageSize.value)
}
// 复选框操作，可以大量删除用户
const handleSelectionChange = () => {
}

// computed
const isAdminUserFresh = computed(() => store.getters.isAdminUserFresh)


watch(isAdminUserFresh, (newVal, oldVal) => {
  if (newVal === oldVal)
    return
  searchUsers(searchQuery.value, currentPage.value, pageSize.value)
})
// onMounted
onMounted(() => {
  searchUsers(searchQuery.value, currentPage.value, pageSize.value)
})
</script>

<template>
  <div class="admin-contianer">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-input
        v-model="searchQuery"
        placeholder="请输入用户名或姓名"
        class="search-input"
        clearable
      />
      <el-button type="primary" @click="handleSearch">搜索</el-button>
    </div>
    <!-- 用户信息 -->
    <!-- 设置表长和宽 -->
    <el-table
      :data="userList"
      style="width: 100%; height: 82%"
      class=""
      highlight-current-row
      @selection-change="handleSelectionChange"
      :header-cell-style="SystemConsts.headerCellStyle"
      :cell-style="SystemConsts.cellStyle"
      :header-cell-class-name="'fixed-header'"
    >
      <el-table-column
        type="selection"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column prop="id" width="80px" label="编号" align="center" />
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
      <el-table-column prop="email" width="210px" label="邮箱" align="center" />
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
    <el-config-provider :locale="zhCn"
      ><el-pagination
        @size-change="handleSizeChange"
        @current-change="handlePageChange"
        :current-page="currentPage"
        :page-size="pageSize"
        :page-sizes="[1, 3, 5, 8, 10, 12, 20]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalUsers"
    /></el-config-provider>

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
        <strong>你是否要删除该用户，确认后操作不可逆。</strong>
      </span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="deleteUserDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="deleteUserAction">确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
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
.search-bar {
  margin: 20px auto;
  display: flex;
  /* align-items: center;
  justify-content: center; */
}
.fixed-header {
  position: sticky;
  top: 0;
  background-color: white;
  z-index: 1;
}

.search-bar {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.search-input {
  width: 200px;
  margin-right: 10px;
}
</style>
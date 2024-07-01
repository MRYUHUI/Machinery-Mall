
<!-- <script setup>
// import apiRequests from "@/apis";
// import { computed, onMounted, ref, watch } from 'vue';
// import { SystemConsts } from "@/enums/SystemConsts";
// import { useStore } from "vuex";
// import { ElMessage } from "element-plus";
import apiRequests from "@/apis"
import { computed, onMounted, ref, watch } from 'vue'
import { SystemConsts } from "@/enums/SystemConsts";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
// import { ElConfigProvider } from 'element-plus'
// import zhCn from 'element-plus/lib/locale/lang/zh-cn'

const store = useStore();
const userList = ref([]);
const totalUsers = ref(0);
const columnWidth = '100px';
const currentPage = ref(1);
const pageSize = ref(10);
let curDelUserId = -1;
const deleteUserDialogVisible = ref(false);
const searchQuery = ref('');

const getAllUsers = async (page = 2, size = 10) => {
  const res = await apiRequests.getAllUsers(page, size);
  userList.value = res.data;
  totalUsers.value = res.total;
}

const searchUsers = async (query, page = 1, size = 10) => {
  if (!query) {
    getAllUsers(page, size);
    return;
  }
  const res = await apiRequests.searchUsers(query, page, size);
  userList.value = res.data;
  totalUsers.value = res.total;
}

const formatGender = (row, column, cellValue) => {
  return cellValue ? '女' : '男';
}

const handlePageChange = (page) => {
  currentPage.value = page;
  searchUsers(searchQuery.value, page, pageSize.value);
}

const handleSizeChange = (size) => {
  pageSize.value = size;
  searchUsers(searchQuery.value, currentPage.value, size);
}

const handleEdit = (user) => {
  store.dispatch('updateSelectedUserInfo', user);
  store.commit('setEditUserInfoDiaVisible', true);
}

const handleDelete = (user) => {
  deleteUserDialogVisible.value = true;
  curDelUserId = user.id;
}

const deleteUserAction = async () => {
  const res = await apiRequests.deleteUser(curDelUserId);
  if (res.success) {
    deleteUserDialogVisible.value = false;
    ElMessage.success(res.message);
    searchUsers(searchQuery.value, currentPage.value, pageSize.value);
  } else {
    ElMessage.error(res.message);
  }
}

const handleSearch = async () => {
  searchUsers(searchQuery.value, currentPage.value, pageSize.value);
}

const handleSelectionChange = () => {
}

const isAdminUserFresh = computed(() => store.getters.isAdminUserFresh);

watch(isAdminUserFresh, (newVal, oldVal) => {
  if (newVal === oldVal)
    return;
  searchUsers(searchQuery.value, currentPage.value, pageSize.value);
});

onMounted(() => {
  searchUsers(searchQuery.value, currentPage.value, pageSize.value);
});
</script>

<template>
  <div class="admin-container">
    <div class="search-bar">
      <el-input v-model="searchQuery" placeholder="请输入用户名或姓名" class="search-input" clearable />
      <el-button type="primary" @click="handleSearch">搜索</el-button>
    </div>
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
      <el-table-column type="selection" width="80" align="center"></el-table-column>
      <el-table-column prop="id" width="80px" label="编号" align="center"></el-table-column>
      <el-table-column prop="account" :width="columnWidth" label="用户名" align="center"></el-table-column>
      <el-table-column prop="name" :width="columnWidth" label="姓名" align="center"></el-table-column>
      <el-table-column prop="sex" label="性别" :width="columnWidth" align="center" :formatter="formatGender"></el-table-column>
      <el-table-column prop="age" :width="columnWidth" label="年龄" align="center"></el-table-column>
      <el-table-column prop="phone" :width="columnWidth" label="电话" align="center"></el-table-column>
      <el-table-column prop="email" width="210px" label="邮箱" align="center"></el-table-column>
      <el-table-column label="操作" :width="200" align="center">
        <template #default="{ row }">
          <el-button type="primary" size="mini" @click="handleEdit(row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(row)">删除</el-button>
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
    <el-dialog
      v-model="deleteUserDialogVisible"
      width="450px"
      destroy-on-close
      center
      class="delete-user-dialog"
    >
      <template #title>
        <div class="custom-dialog-title">警告</div>
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

<style scoped>
.admin-container {
  background-color: rgb(255, 255, 255);
}
.el-table {
  overflow: auto;
}
.delete-user-dialog {
  font-size: 26px;
}
.custom-dialog-title {
  font-size: 30px;
  text-align: center;
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
.fixed-header {
  position: sticky;
  top: 0;
  background-color: white;
  z-index: 1;
}
</style> -->



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
let curDelOrderNo = -1
// 警告提示
const deleteOrderDialogVisible = ref(false)
const searchQuery = ref('')

// method ===============
// 获取所有用户
const getAllOrders= async (page = 1, size = 10) => {
  const res1 = await apiRequests.getAllOrders(page, size)
  userList.value = res1.data
  totalUsers.value = res1.total
}
// 搜索用户
const searchOrders = async (query, page = 1, size = 10) => {
  if (!query) {
    getAllOrders(page, size) // 如果查询为空，调用获取所有用户的函数
    return;
  }
  const res = await apiRequests.searchOrders(query, page, size);
  console.log(res);

  userList.value = res.data;
  totalUsers.value = res.total;
}

// 根据
const formatOrderNo = (res) => {
  return  res.orderNo? res.orderNo:"-";
};

const formatAddrId = (row) => {
  return row.addrId ? row.addrId.toString() : '-';
};

// 页码改变时的处理函数
const handlePageChange = (page) => {
  currentPage.value = page
  searchOrders(searchQuery.value, page, pageSize.value)
}

// 每页条数改变时的处理函数
const handleSizeChange = (size) => {
  pageSize.value = size
  searchUsers(searchQuery.value, currentPage.value, size)
}



// 编辑用户信息---------------
const handleEdit = (user) => {
  // 将要编辑的用户信息存入 store 里
  store.dispatch('updateSelectedUserInfo', user)
  // 显示编辑用户对话框
  store.commit('setEditUserInfoDiaVisible', true)
}





// 删除用户原版本
const handleDelete = (order) => {
  deleteOrderDialogVisible.value = true
  curDelOrderNo = order.orderNo
}
// 删除用户动作
const deleteOrderAction = async () => {
  const res = await apiRequests.deleteOrder(curDelOrderNo)
  if (res.success) {
    deleteOrderDialogVisible.value = false
    ElMessage.success(res.message)
    // 刷新
    searchOrders(searchQuery.value, currentPage.value, pageSize.value)
  } else {
    ElMessage.error(res.message)
  }
}
const handleSearch = async () => {
  searchOrders(searchQuery.value, currentPage.value, pageSize.value)
}
// 复选框操作，可以大量删除用户
const handleSelectionChange = () => {
}
// computed
const isAdminUserFresh = computed(() => store.getters.isAdminUserFresh)
watch(isAdminUserFresh, (newVal, oldVal) => {
  if (newVal === oldVal)
    return
  searchOrders(searchQuery.value, currentPage.value, pageSize.value)
})
// onMounted
onMounted(() => {
  searchOrders(searchQuery.value, currentPage.value, pageSize.value)
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
        :align="'center'"
      ></el-table-column>

      <el-table-column 
        prop="id" 
        width="80px" 
        label="编号" 
      :align="'center'" />
      <el-table-column
  prop="order_no"
  label="订单编号"
  :width="columnWidth"
   align="center"
  :formatter="formatOrderNo"
></el-table-column>

<!-- kk -->
      <!-- <el-table-column
        prop="order_no"
        width="columnWidth"
        label="订单编号"
        :align="'center'"
      /> -->

      <el-table-column
        prop="uid"
        :width="columnWidth"
        label="用户编号"
        :align="'center'"
      />
      <!-- kk -->
      <!-- <el-table-column
        prop="addr_id"
        width="columnWidth"
        label="收货地址编号"
        :align="'center'"
      /> -->
      <!-- <el-table-column
  prop="order_no"
  label="订单编号"
  :width="columnWidth"
  align="center"
  :formatter="formatOrderNo"
></el-table-column> -->

<el-table-column
  prop="addrId"
  label="地址编号"
  :width="columnWidth"
  align="center"
  :formatter="formatAddrId"

></el-table-column>



      <el-table-column
        prop="amount"
        width="columnWidth"
        label="交易金额"
        :align="'center'"
      />
      <el-table-column
        prop="type"
        width="columnWidth"
        label="付款类型"
        :align="'center'"
      />


      <el-table-column
        prop="freight"
        :width="columnWidth"
        label="运费"
        :align="'center'"
      />
 <!-- kk -->
      <el-table-column label="操作" :width="200" align="center">
        <template #default="{ row }">
          <el-button type="primary" size="mini" @click="handleEdit(row)"
            >详情</el-button
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
      v-model="deleteOrderDialogVisible"
      width="450px"
      destroy-on-close
      center
      class="delete-user-dia"
    >




      <!-- 使用 title 插槽自定义标题样式   删除 -->
      <template #title>
        <div class="custom-dialog-title">警 告</div>
      </template>

      <span style="font-size: 20px">
        <strong>你是否要删除该用户，确认后操作不可逆。</strong>
      </span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="deleteOrderDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="deleteOrderAction">确定</el-button>
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
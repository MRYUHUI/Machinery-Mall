<script setup>
import apiRequests from "@/apis"
import { computed, onMounted, ref, watch } from 'vue'
import { SystemConsts } from "@/enums/SystemConsts";
import { useStore } from "vuex";
import { ElMessage } from "element-plus";
import { ElConfigProvider } from 'element-plus'
import zhCn from 'element-plus/lib/locale/lang/zh-cn'
import DetailManagerOrder from "./DetailManagerOrder.vue";
// data
const store = useStore()
const orderItemList = ref([])
const totalOrderItem = ref(0)
const columnWidth = '200px'
const currentPage = ref(1)
const pageSize = ref(10)

const searchQuery = ref('')


// method ===============
// 
const getAllOrderItem = async (page = 1, size = 10) => {
  const res1 = await apiRequests.getnewAllOrderItem(page, size)
  orderItemList.value = res1.data
  totalOrderItem.value = res1.total
}
// 搜索用户
const searchOrderItem = async (searchQuery, page = 1, size = 10) => {
  if (!searchQuery) {
    getAllOrderItem(page, size) // 如果查询为空，调用获取所有用户的函数
    return;
  }
  const res = await apiRequests.getOrderItemByOrderId(searchQuery);
  orderItemList.value = res.data;
  totalOrderItem.value = res.total;
}

// 页码改变时的处理函数
const handlePageChange = (page) => {
  currentPage.value = page
  searchOrderItem(searchQuery.value, page, pageSize.value)
}

// 每页条数改变时的处理函数
const handleSizeChange = (size) => {
  pageSize.value = size
  searchUsers(searchQuery.value, currentPage.value, size)
}

// 编辑用户信息---------------
const showDetail = (row) => {
  console.log('=====-=-=')
  console.log(row);

  store.commit('setSelectedUserOrderItemInfo', row)
  console.log(store.getters.selectedUserOrderItemInfo);

  // 显示编辑用户对话框
  store.commit('setAdminDetailOrderItemDInfoiaVisible', true)
}



const handleSearch = async () => {
  searchOrderItem(searchQuery.value, currentPage.value, pageSize.value)
}
// 复选框操作，可以大量删除用户
const handleSelectionChange = () => {
}
const adminDetailOrderItemDInfoiaVisible = computed(() => store.getters.adminDetailOrderItemDInfoiaVisible)
// onMounted
onMounted(() => {
  getAllOrderItem();
})
</script>



<template>
  <div class="admin-contianer">
    <!-- 搜索栏 -->
    <div class="search-bar">
      <el-input
        v-model="searchQuery"
        placeholder="请输入"
        class="search-input"
        clearable
      />
      <el-button type="primary" @click="handleSearch">搜索</el-button>
    </div>
    <!-- 用户信息 -->
    <!-- 设置表长和宽 -->
    <el-table
      :data="orderItemList"
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

      <el-table-column prop="id" width="80px" label="编号" :align="'center'" />
      <el-table-column
        prop="orderId"
        label="订单ID"
        :width="columnWidth"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="uid"
        :width="columnWidth"
        label="用户编号"
        :align="'center'"
      />
      <el-table-column
        prop="goodsName"
        label="商品名字"
        :width="columnWidth"
        align="center"
      ></el-table-column>

      <el-table-column
        prop="totalPrice"
        width="columnWidth"
        label="交易金额"
        :align="'center'"
      />
      <!-- kk -->
      <el-table-column label="操作" :width="200" align="center">
        <template #default="{ row }">
          <el-button type="primary" size="mini" @click="showDetail(row)"
            >详情</el-button
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
        :total="totalOrderItem"
    /></el-config-provider>
  </div>
  <DetailManagerOrder
    v-if="adminDetailOrderItemDInfoiaVisible"
  ></DetailManagerOrder>
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
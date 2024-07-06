<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import { SystemConsts } from "@/enums/SystemConsts";
import { useStore } from "vuex";
import { ElMessage } from 'element-plus';
import apiRequests from "@/apis";
import EditAdminGoodTypeInfo from '../EditAdminGoodTypeInfo.vue';
// data
const store = useStore();
const adminGoodTypeList = ref([]);
const totaladminGoodTypes = ref(0);
const columnWidth = '140px';
const backbuttonDiaVisible = ref(false);
const currentPage = ref(1);
const pageSize = ref(10);
const deleteAdminGoodTypeDialogVisible = ref(false)
// 搜索框内容绑定
const searchQuery = ref('');
let curDelAdminGoodTypeId = -1

// 获取全部顶级分类
const getAllHighestAdminGoodTypesPage = async (page = 1, size = 10) => {
  const res = await apiRequests.allHighestProductCategorys(page, size);
  // console.log(res.data)
  adminGoodTypeList.value = res.data;
  // console.log(adminGoodTypeList.value)
  totaladminGoodTypes.value = res.total;
};

// 搜索用户
const searchAdminGoodTypes = async (query, page = 1, size = 10) => {
  if (!query) {
    getAllHighestAdminGoodTypesPage(page, size) // 如果查询为空，调用获取所有用户的函数
    return;
  }
  const res = await apiRequests.searchAdminGoodTypes(query, page, size);
  // console.log(res);

  adminGoodTypeList.value = res.data;
  totaladminGoodTypes.value = res.total;
}
// 返回上节点函数
const ReturnPreviousNode = async (row) => {
  try {
    let previousNodeParentList = store.getters.previousNodeParentList;
    // console.log(store.getters.previousNodeParentList);
    const previousNodeParent = previousNodeParentList.pop();
    const res = await apiRequests.allProductCategoryChildrens(previousNodeParent, currentPage.value, pageSize.value);
    if (previousNodeParent == 0) {
      store.commit('newPreviousNodeParentList', []);
      backbuttonDiaVisible.value = false;
    }
    adminGoodTypeList.value = res.data;
    totaladminGoodTypes.value = res.total;
  } catch (error) {
    console.error('请求失败:', error);
  }
}
// 查看子节点函数
const handleViewSubNodes = async (row) => {
  const res = await apiRequests.allProductCategoryChildrens(row.id, currentPage.value, pageSize.value);
  store.commit('setPreviousNodeParentList', row.parentId);
  // console.log(row.parentId);
  if (row.parentId == 0) { backbuttonDiaVisible.value = true; }

  adminGoodTypeList.value = res.data;
  totaladminGoodTypes.value = res.total;
}

// 删除分类警告
const handleDelete = (adminGoodType) => {
  deleteAdminGoodTypeDialogVisible.value = true
  curDelAdminGoodTypeId = adminGoodType.id
}

// 删除分类动作
const deleteAdminGoodTypeAction = async () => {

  const res = await apiRequests.deleteAdminGoodType(curDelAdminGoodTypeId)
  if (res.success) {
    // console.log(res);
    deleteAdminGoodTypeDialogVisible.value = false
    ElMessage.success(res.message)
    // 刷新
    getAllHighestAdminGoodTypesPage(currentPage.value, pageSize.value)
  } else {
    ElMessage.error(res.message)
  }
}

// 编辑分类信息
const handleEdit = (adminGoodType) => {
  // 将要编辑的用户信息存入 store 里
  store.dispatch('updateSelectedAdminGoodTypeInfo', adminGoodType)
  // 显示编辑用户对话框
  store.commit('setEditAdminGoodTypeInfoDiaVisible', true)
}

const handleSearch = async () => {
  searchAdminGoodTypes(searchQuery.value, currentPage.value, pageSize.value);
};

// 复选框操作，可以大量删除用户
const handleSelectionChange = () => {
}

const handlePageChange = (page) => {
  currentPage.value = page;
  getAllHighestAdminGoodTypesPage(page, pageSize.value);
};

const handleSizeChange = (size) => {
  pageSize.value = size;
  getAllHighestAdminGoodTypesPage(currentPage.value, size);
};

// 监控Switch开关
const handleSwitchChange = async (row) => {
  try {
    const res = await apiRequests.updateAdminGoodTypeInfo(row, row.parentId);
    if (res.success) {
      ElMessage.success('状态更新成功');
    } else {
      ElMessage.error(res.message || '状态更新失败');
      // 如果更新失败，恢复开关状态
      row.status = !row.status;
    }
  } catch (error) {
    console.error('状态更新失败', error);
    // 如果更新失败，恢复开关状态
    row.status = !row.status;
  }
};

// computed
const isAdminGoodTypeFresh = computed(() => store.getters.isAdminGoodTypeFresh);
watch(isAdminGoodTypeFresh, () => {
  getAllHighestAdminGoodTypesPage(currentPage.value, pageSize.value);
});

// onMounted
onMounted(() => {
  console.log('======================');

  console.log(currentPage.value);
  console.log(pageSize.value);


  getAllHighestAdminGoodTypesPage(currentPage.value, pageSize.value);
});
</script>

<template>
  <div class="admin-contianer">
    <!-- 搜索栏和返回按钮 -->
    <div class="search-and-back">
      <div class="search-bar">
        <el-input
          v-model="searchQuery"
          placeholder="请输入分类名或者ID"
          class="search-input"
          clearable
        />
        <el-button type="primary" @click="handleSearch">搜索</el-button>
      </div>
      <div class="add-bar">
        <el-button
          class="add-button"
          type="primary"
          size="medium"
          @click="ReturnPreviousNode(row)"
          >增加</el-button
        >
      </div>
      <div class="space-div"></div>
      <div class="back-bar">
        <el-button
          v-if="backbuttonDiaVisible"
          class="back-button"
          type="primary"
          size="medium"
          @click="ReturnPreviousNode(row)"
          >返回上节点</el-button
        >
      </div>
    </div>

    <!-- 用户信息 -->
    <!-- 设置表长和宽 -->
    <el-table
      :data="adminGoodTypeList"
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
      <el-table-column
        prop="id"
        :width="columnWidth"
        label="编号"
        align="center"
      />
      <el-table-column
        prop="parentId"
        :width="columnWidth"
        label="所属分类"
        align="center"
      />
      <el-table-column
        prop="name"
        :width="columnWidth"
        label="类别名称"
        align="center"
      />
      <el-table-column label="状态" :width="columnWidth" align="center">
        <template #default="{ row }">
          <el-switch v-model="row.status" @change="handleSwitchChange(row)" />
        </template>
      </el-table-column>
      <el-table-column
        prop="level"
        :width="columnWidth"
        label="分类层数"
        align="center"
      />
      <el-table-column label="操作" :width="260" align="center">
        <template #default="{ row }">
          <div class="action-buttons">
            <el-button type="primary" size="mini" @click="handleEdit(row)"
              >编辑</el-button
            >
            <el-button type="danger" size="mini" @click="handleDelete(row)"
              >删除</el-button
            >
            <el-button
              type="success"
              size="mini"
              @click="handleViewSubNodes(row)"
              >查看子节点</el-button
            >
          </div>
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
      :total="totaladminGoodTypes"
    />

    <!-- 删除分类警告 -->
    <el-dialog
      v-model="deleteAdminGoodTypeDialogVisible"
      width="450px"
      destroy-on-close
      center
      class="delete-admin-good-type-dia"
    >
      <!-- 使用 title 插槽自定义标题样式 -->
      <template #title>
        <div class="custom-dialog-title">警 告</div>
      </template>

      <span style="font-size: 20px">
        <strong>你是否要删除该分类，确认后操作不可逆。</strong>
      </span>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="deleteAdminGoodTypeDialogVisible = false"
            >取消</el-button
          >
          <el-button type="primary" @click="deleteAdminGoodTypeAction"
            >确定</el-button
          >
        </div>
      </template>
    </el-dialog>

    <EditAdminGoodTypeInfo></EditAdminGoodTypeInfo>
  </div>
</template>

<style>
.search-and-back {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-bar {
  display: flex;
  align-items: center;
  margin: 0;
}

.search-input {
  width: 200px;
  margin-right: 10px;
}

.action-buttons {
  display: flex;
  gap: 1px; /* 按钮之间的间距 */
  justify-content: flex-start; /* 从左到右排列 */
}

.action-buttons .el-button {
  flex-shrink: 0; /* 防止按钮缩小 */
}

.back-bar {
  display: flex;
  align-items: center;
}

.back-button {
  width: 105px;
  margin-right: 10px;
}

.add-bar {
  display: flex;
  align-items: center;
}

.add-button {
  height: 40px;
  margin-left: 20px;
  margin-right: 200px;
  width: 70px;
}

.space-div {
  display: flex;
  align-items: center;
  flex-grow: 1;
}
</style>
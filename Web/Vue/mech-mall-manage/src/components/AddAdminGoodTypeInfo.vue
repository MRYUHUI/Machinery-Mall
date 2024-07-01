<script setup>
import { useStore } from "vuex";
import { computed, ref, reactive, watch, onMounted } from "vue";
import apiRequests from "@/apis";
import { ElMessage } from "element-plus";

const store = useStore();
const addRef = ref(null);
const addname = ref();
const addLevel = ref(0);

const form = reactive({
  id: '',
  parentId: '',
  name: '',
  sortOrder: '',
  status: '',
  level: '',
  created: '',
  updated: '',
});

const oldform = reactive({
  id: '',
  parentId: '',
  name: '',
  sortOrder: '',
  status: '',
  level: '',
  created: '',
  updated: '',
});

const resetForm = () => {
  form.id = '';
  form.parentId = '';
  form.name = '';
  form.sortOrder = '';
  form.status = '';
  form.level = '';
  form.created = '';
  form.updated = '';
};

const addRule = {
};

const addAdminGoodTypeInfoDiaVisible = computed(() => store.getters.addAdminGoodTypeInfoDiaVisible);
const dialogWidth = '500px';

const onSubmit = () => {
  addRef.value.validate(async (valid) => {
    if (valid) {
      // console.log(oldform.parentId)
      const res = await apiRequests.updateAdminGoodTypeInfo(form , oldform.parentId)
      if (res.success) {
        const updatedAdminGoodTypeInfo = res.data
        // 调用 store 的 action 保存数据
        store.dispatch('updateSelectedAdminGoodTypeInfo', updatedAdminGoodTypeInfo);
        // 通知管理用户界面刷新
        store.commit('setIsAdminGoodTypeFresh', !store.getters.isAdminGoodTypeFresh)
        ElMessage.success(res.message)
        resetForm()
        store.commit('setAddAdminGoodTypeInfoDiaVisible', false);
      }
      else {
        ElMessage.error(res.message)
      }
    }
  })

  store.commit('setAddAdminGoodTypeInfoDiaVisible', false);
};

const cancelEidt = () => {
  resetForm()
  store.commit('setAddAdminGoodTypeInfoDiaVisible', false);
};

const adminGoodTypeLevel = computed(() => store.getters.adminGoodTypeLevel);
// const userInfo = computed(() => store.getters.selectedUserInfo);
// 监听 addAdminGoodTypeInfoDiaVisible 的变化，并更新 addLevel
watch(addAdminGoodTypeInfoDiaVisible, () => {
  if (addAdminGoodTypeInfoDiaVisible)
    Object.assign(addLevel, adminGoodTypeLevel.value);
});

</script>

<template>
  <!-- 对话框 -->
  <el-dialog
    v-model="addAdminGoodTypeInfoDiaVisible"
    title="新增分类信息"
    :close-on-click-modal="false"
    :show-close="false"
    :width="dialogWidth"
  >
    <!-- 编辑表单 -->
    <el-form
      :model="form"
      label-width="80px"
      :rules="addRule"
      ref="addRef"
      class="add-form"
    >
      <!-- 所属分类 -->
      <el-form-item label="所属分类" prop="parentId">
        <el-input v-model="form.parentId" />
      </el-form-item>
      <!-- 分类名称 -->
      <el-form-item label="分类名称" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
    </el-form>
    <!-- 修改按钮 -->
    <div>
      <el-button class="btn-list" type="primary" @click="onSubmit"
        >确认</el-button
      >
      <el-button class="btn-list" @click="cancelEidt">取消</el-button>
    </div>
  </el-dialog>
</template>

<style scoped>
.add-form {
  width: 100%;
}
.btn-list {
  width: 100px;
  margin: 10px;
}
</style>
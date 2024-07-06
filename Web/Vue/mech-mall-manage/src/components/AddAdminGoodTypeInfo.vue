<script setup>
import { useStore } from "vuex";
import { computed, ref, reactive, watch, onMounted } from "vue";
import apiRequests from "@/apis";
import { ElMessage } from "element-plus";

const store = useStore();
const addRef = ref(null);
const dialogWidth = '500px';
const addAdminGoodTypeInfoDiaVisible = computed(() => store.getters.addAdminGoodTypeInfoDiaVisible);
const ParentId = computed(() => store.getters.adminGoodTypeParentId);

// 暂存表
const form = reactive({
  parentId: '',
  name: '',
});

// 刷新表格函数
const resetForm = () => {
  form.parentId = '';
  form.name = '';
};

// 增加数据规范
const addRule = {
  name: [{ required: true, message: '分类名不能为空', trigger: 'blur' }],
};

// 提交按钮函数
const onSubmit = () => {
  addRef.value.validate(async (valid) => {
    if (valid) {
      const res = await apiRequests.addAdminGoodTypeInfo(form.name , form.parentId)
      if (res.success) {
        const addAdminGoodTypeInfo = res.data
        // 调用 store 的 action 保存数据
        store.dispatch('updateSelectedAdminGoodTypeInfo', addAdminGoodTypeInfo);
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

// 取消按钮函数
const cancelEidt = () => {
  resetForm()
  store.commit('setAddAdminGoodTypeInfoDiaVisible', false);
};

// 监听器
watch(addAdminGoodTypeInfoDiaVisible, () => {
  if (addAdminGoodTypeInfoDiaVisible)
    Object.assign(form.parentId, ParentId.value);
    form.parentId = ParentId.value;
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
        <el-input v-model="form.parentId" :value="form.parentId" disabled />
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
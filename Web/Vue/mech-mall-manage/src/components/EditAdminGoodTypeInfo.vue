<script setup>
import { useStore } from "vuex";
import { computed, ref, reactive, watch, onMounted } from "vue";
import apiRequests from "@/apis";
import { ElMessage } from "element-plus";

const store = useStore();
const editRef = ref(null);

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

const editRule = {
  parentId: [{ required: true, trigger: 'blur' }],
  name: [{ required: true, message: '分类名不能为空', trigger: 'blur' }],
};

// 定义下拉选项数据
const parentCategoryOptions = ref([]);

// 获取下拉选项数据
const fetchParentCategoryOptions = async () => {
  try {
    const res = await apiRequests.allProductCategorys();
    // console.log(res.data)
    if (res.success) {
      parentCategoryOptions.value = res.data.map(category => ({
        value: category.id,
        label: category.name
      }));
    } else {
      ElMessage.error(res.message);
    }
  } catch (error) {
    ElMessage.error('获取分类选项失败');
  }
};

const editAdminGoodTypeInfoDiaVisible = computed(() => store.getters.editAdminGoodTypeInfoDiaVisible);
const dialogWidth = '500px';

const onSubmit = () => {
  editRef.value.validate(async (valid) => {
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
        store.commit('setEditAdminGoodTypeInfoDiaVisible', false);
      }
      else {
        ElMessage.error(res.message)
      }
    }
  })

  store.commit('setEditAdminGoodTypeInfoDiaVisible', false);
};

const cancelEidt = () => {
  resetForm()
  store.commit('setEditAdminGoodTypeInfoDiaVisible', false);
};

const adminGoodTypeInfo = computed(() => store.getters.selectedAdminGoodTypeInfo);
// 监听 adminGoodTypeInfo 的变化，并更新 form
watch([editAdminGoodTypeInfoDiaVisible, adminGoodTypeInfo], ([newEditAdminGoodTypeInfoDiaVisible, newAdminGoodTypeInfo], [oldEditAdminGoodTypeInfoDiaVisible, oldAdminGoodTypeInfo]) => {
  if (newEditAdminGoodTypeInfoDiaVisible) {
    Object.assign(form, adminGoodTypeInfo.value);
  } else {
    Object.assign(form, newAdminGoodTypeInfo);
  }
  Object.assign(oldform, oldAdminGoodTypeInfo);
});

// 在组件挂载时获取下拉选项数据
onMounted(fetchParentCategoryOptions);

</script>

<template>
  <!-- 对话框 -->
  <el-dialog
    v-model="editAdminGoodTypeInfoDiaVisible"
    title="编辑分类信息"
    :close-on-click-modal="false"
    :show-close="false"
    :width="dialogWidth"
  >
    <!-- 编辑表单 -->
    <el-form
      :model="form"
      label-width="80px"
      :rules="editRule"
      ref="editRef"
      class="edit-form"
    >
      <!-- 所属分类 -->
      <el-form-item label="所属分类" prop="parentId">
        <el-select v-model="form.parentId" placeholder="请选择分类">
          <el-option
            v-for="option in parentCategoryOptions"
            :key="option.value"
            :label="option.label"
            :value="option.value"
          ></el-option>
        </el-select>
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
.edit-form {
  width: 100%;
}
.btn-list {
  width: 100px;
  margin: 10px;
}
</style>
<script setup>
import { useStore } from "vuex";
import { computed, ref, reactive, watch } from "vue";
import apiRequests from "@/apis";
import { ElMessage } from "element-plus";

const store = useStore();
const editRef = ref(null);
const ageOptions = Array.from({ length: 121 }, (_, i) => i); // 生成0到120的数组


const form = reactive({});

const resetForm = () => {
  form.id = '';
  form.account = '';
  form.password = '';
  form.email = '';
  form.phone = '';
  form.question = '';
  form.asw = '';
  form.role = '';
  form.age = '';
  form.sex = '';
  form.createTime = '';
  form.updateTime = '';
  form.del = '';
  form.name = '';
};

const editRule = {
  account: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  email: [{ required: true, message: '邮箱不能为空', trigger: 'blur' }],
  phone: [{ required: true, message: '电话不能为空', trigger: 'blur' }],
  age: [{ required: true, message: '请选择年龄', trigger: 'change' }],
  sex: [{ required: true, message: '性别不能为空', trigger: 'change' }],
  name: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
};

const editUserInfoDiaVisible = computed(() => store.getters.editUserInfoDiaVisible);
const dialogWidth = '500px';

const onSubmit = () => {
  editRef.value.validate(async (valid) => {
    if (valid) {
      let res
      try {
        if (store.getters.role === 2) {
          res = await apiRequests.updateUserInfo(form)
        }
        else {
          res = await apiRequests.editUserInfo(form)
        }
        if (res.success) {
          const updatedUserInfo = res.data
          // 调用 store 的 action 保存数据
          store.dispatch('updateSelectedUserInfo', updatedUserInfo);
          // 通知管理用户界面刷新
          store.commit('setIsAdminUserFresh', !store.getters.isAdminUserFresh)
          ElMessage.success(res.message)
          resetForm()
          store.commit('setEditUserInfoDiaVisible', false);
        }
        else {
          ElMessage.error(res.message)
        }
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
  })

  store.commit('setEditUserInfoDiaVisible', false);
};

const cancelEidt = () => {
  resetForm()
  store.commit('setEditUserInfoDiaVisible', false);
};


// computed ================
const userInfo = computed(() => store.getters.selectedUserInfo);
// 监听 userInfo 的变化，并更新 form
watch(editUserInfoDiaVisible, () => {
  if (editUserInfoDiaVisible)
    Object.assign(form, userInfo.value);
});
</script>

<template>
  <!-- 对话框 -->
  <el-dialog
    v-model="editUserInfoDiaVisible"
    title="编辑用户信息"
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
      <!-- 用户名 -->
      <el-form-item label="用户名" prop="account">
        <el-input v-model="form.account" />
      </el-form-item>
      <!-- 邮箱 -->
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email" />
      </el-form-item>
      <!-- 电话 -->
      <el-form-item label="电话" prop="phone">
        <el-input v-model="form.phone" />
      </el-form-item>
      <!-- 年龄 -->
      <el-form-item label="年龄" prop="age">
        <el-select v-model="form.age" placeholder="请选择年龄">
          <el-option
            v-for="age in ageOptions"
            :key="age"
            :label="age"
            :value="age"
          />
        </el-select>
      </el-form-item>
      <!-- 性别 -->
      <el-form-item label="性别" prop="sex">
        <el-radio-group v-model="form.sex">
          <el-radio :label="1">男</el-radio>
          <el-radio :label="0">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 姓名 -->
      <el-form-item label="姓名" prop="name">
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

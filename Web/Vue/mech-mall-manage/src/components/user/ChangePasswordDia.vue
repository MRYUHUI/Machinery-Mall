<script setup>
import apiRequests from '@/apis';
import { computed, ref, reactive } from 'vue';
import { useStore } from 'vuex';
import { ElMessage } from 'element-plus';
import CryptoJS from 'crypto-js';
const store = useStore()
const changePwdRef = ref(null)
const changePwdForm = reactive({
  password: '',
  confirmPassword: ''
})
const changePwdRule = {
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' },
    { min: 3, max: 20, message: '密码长度应为3到20个字符', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '确认密码不能为空', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value !== changePwdForm.password) {
          callback(new Error('确认密码与密码不一致'));
        } else {
          callback();
        }
      }, trigger: 'blur'
    }
  ],
}
const isFocused = ref(false);

const togglePasswordVisibility = () => {
  showPassword.value = !showPassword.value;
};

const showPassword = ref(false);

const handleFocus = () => {
  isFocused.value = true;
};

const handleBlur = () => {
  isFocused.value = false;
};
const submitNewPassword = () => {
  changePwdRef.value.validate(async (valid) => {
    if (valid) {
      const user = {
        ...store.getters.selectedUserInfo,
        password: CryptoJS.MD5(changePwdForm.password,).toString().toUpperCase(),
        id: store.getters.userId
      }
      try {
        const res = await apiRequests.updateUserPassword(user)
        if (res.success) {
          store.commit('setChangePwdPageVisible', false)
          ElMessage.success("修改密码成功")
        }
        else {
          ElMessage.error("修改密码失败")
        }
      } catch (error) {
        ElMessage.error("修改密码失败")
      }

    }
  })
  store.dispatch('resetSelectedUserInfo')
}
const cancel = () => {
  store.commit('setChangePwdPageVisible', false)
}
const changePwdPageVisible = computed(() => store.getters.changePwdPageVisible)
</script>

<template>
  <!-- 更换密码 -->
  <el-dialog
    v-model="changePwdPageVisible"
    :close-on-click-modal="false"
    :show-close="false"
    width="600px"
  >
    <el-form
      :model="changePwdForm"
      label-width="auto"
      style="max-width: 600px"
      :rules="changePwdRule"
      ref="changePwdRef"
    >
      <!-- 新密码 -->
      <el-form-item label="新密码" prop="password">
        <el-input
          v-model="changePwdForm.password"
          :type="showPassword ? 'text' : 'password'"
          @focus="handleFocus"
          @blur="handleBlur"
        >
          <template #suffix>
            <i
              :class="'el-icon-view'"
              @click="togglePasswordVisibility"
              style="cursor: pointer"
            ></i>
          </template>
        </el-input>
      </el-form-item>
      <!-- 确认密码 -->
      <el-form-item label="确认密码" prop="confirmPassword">
        <el-input
          v-model="changePwdForm.confirmPassword"
          :type="showPassword ? 'text' : 'password'"
          @focus="handleFocus"
          @blur="handleBlur"
        >
          <template #suffix>
            <i
              :class="'el-icon-view'"
              @click="togglePasswordVisibility"
              style="cursor: pointer"
            ></i>
          </template>
        </el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="cancel">取消</el-button>
      <el-button type="primary" @click="submitNewPassword">确认</el-button>
    </template>
  </el-dialog>
</template>


<style>
</style>
<template>
  <div class="container">
    <div
      :class="{
        login: true,
        'active-shadow': isFocused,
        'unactive-shadow': !isFocused,
      }"
    >
      <el-form
        :model="form"
        label-width="auto"
        style="max-width: 800px"
        :rules="loginRule"
        ref="loginRef"
      >
        <!-- 用户名 -->
        <el-form-item label="用户名" prop="account">
          <el-input
            v-model="form.account"
            :style="{ width: '350px' }"
            @focus="handleFocus"
            @blur="handleBlur"
          />
        </el-form-item>
        <!-- 密码 -->
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="form.password"
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
        <!-- 忘记密码 -->
        <el-button type="text" class="forgot-password">忘记密码?</el-button>
        <el-form-item>
          <!-- 登录按钮 -->
          <el-button class="btn-list" type="primary" @click="onSubmit"
            >登录</el-button
          >
          <el-button class="register-button btn-list" @click="signUp"
            >注册</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup>
import apiRequests from '@/apis';
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { useStore } from 'vuex';
import CryptoJS from 'crypto-js';
const router = useRouter();
const loginRef = ref(null);
const store = useStore();

const form = reactive({
  account: '',
  password: '',
});

const loginRule = {
  account: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' },
    { min: 3, max: 20, message: '密码长度应为3到20个字符', trigger: 'blur' },
  ],
};

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

const signUp = () => {
  router.push({ name: 'register' });
};

const onSubmit = async () => {
  loginRef.value.validate(async (valid) => {
    if (valid) {
      try {
        // 对密码进行MD5加密
        const encryptedPassword = CryptoJS.MD5(form.password).toString().toUpperCase();
        const formData = {
          ...form,
          password: encryptedPassword
        };
        const res = await apiRequests.signIn(formData);

        // 登录成功
        if (res.success) {
          const user = res.data;
          localStorage.setItem('token', res.token)
          // 调用 Vuex 的 action 存储用户信息
          store.dispatch('saveIdAndAccount', {
            userId: user.id,
            account: user.account,
          });
          if (user.role === 1) {
            router.push({ name: 'user-home' });
          } else {
            router.push({ name: 'admin-home' });
          }
          ElMessage.success(res.message);
        } else {
          ElMessage.error(res.message);
        }
      } catch (error) {
        console.error('登录失败:', error);
        ElMessage.error('登录失败，请稍后重试');
      }
    }
  });
};

</script>

<style>
.login {
  padding: 30px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: #e7e7e7;
  border-radius: 10px;
  font-weight: bold;
}

.unactive-shadow {
  border: 5px solid #0055ff6c;
  box-shadow: 0 0 50px #00bfffec;
  transition: all 0.5s;
}

.active-shadow {
  border: 5px solid #dd00ff6c;
  box-shadow: 0 0 50px #ff00e6c7;
  transition: all 0.5s;
}

.forgot-password {
  margin-top: -15px;
  margin-left: 50px;
  display: block;
}

.el-form-item {
  position: relative;
}

.register-button {
  margin-left: 10px;
}

.btn-list {
  width: 100px;
  margin: 10px;
}
</style>

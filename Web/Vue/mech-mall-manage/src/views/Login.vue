
<script setup>
import apiRequests from '@/apis';
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import { useStore } from 'vuex';
import CryptoJS from 'crypto-js';
import ChangePasswordDiaVue from '@/components/user/ChangePasswordDia.vue';
const router = useRouter();
const loginRef = ref(null);
const store = useStore();
const forgotPwdPageVisible = ref(false)
const forgotPwdQuestion = ref('')
const forgotPwdAnswer = ref('')
const correctAnswer = ref('')
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
          store.commit('setCurUserInfo', user)

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

const handleForgotPwd = async () => {
  // 如果未输入用户名
  if (form.account === '' || form.account === null) {
    // 提示未输入用户名
    ElMessage.error("请先输入用户名")
    return
  }
  const account = form.account
  const res = await apiRequests.findUserByAccount(account)
  if (res.success) {
    const user = res.data
    store.commit('setUserId', user.id)
    forgotPwdQuestion.value = user.question
    correctAnswer.value = user.asw
    forgotPwdPageVisible.value = true
  } else {
    ElMessage.error(res.message)
  }
}

const validateAnswer = () => {
  // 回答正确
  if (forgotPwdAnswer.value === correctAnswer.value) {
    // 关闭问答对话框
    forgotPwdPageVisible.value = false
    // 开启修改密码对话框
    store.commit('setChangePwdPageVisible', true)
    store.dispatch('resetSelectedUserInfo')
  } else {
    ElMessage.error("回答错误")
  }
}

</script>
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
        <el-button type="text" class="forgot-password" @click="handleForgotPwd"
          >忘记密码?</el-button
        >
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
  <!-- 忘记密码的问题 -->
  <el-dialog
    v-model="forgotPwdPageVisible"
    :close-on-click-modal="false"
    :show-close="false"
    title="找回密码"
    width="500px"
  >
    <div class="content">
      <p class="question">{{ forgotPwdQuestion }}</p>
      <el-input v-model="forgotPwdAnswer" placeholder="请输入答案"></el-input>
    </div>
    <template #footer>
      <el-button @click="forgotPwdPageVisible = false">取消</el-button>
      <el-button type="primary" @click="validateAnswer">确认</el-button>
    </template>
  </el-dialog>
  <!-- 修改密码对话框 -->
  <ChangePasswordDiaVue></ChangePasswordDiaVue>
</template>


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

.content {
  display: flex;
  flex-direction: column;
  gap: 20px; /* 控制问题文本与输入框的间距 */
}

.question {
  font-size: 18px; /* 控制问题文本的字体大小 */
  margin-bottom: 10px; /* 控制问题文本与输入框的间距 */
}
</style>

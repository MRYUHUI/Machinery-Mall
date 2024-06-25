<script setup>
import { reactive, ref, getCurrentInstance } from 'vue'
import { useRouter } from 'vue-router';
// data============================================
const router = useRouter()
const loginRef = ref(null)
// rules
const loginRule = {
  username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' },
    { min: 3, max: 20, message: '密码长度应为3到20个字符', trigger: 'blur' }
  ]
}

// 输入框焦点状态
const isFocused = ref(false)

// 创建表单数据
const form = reactive({
  username: '',
  password: '',
})

// 查看密码切换
const showPassword = ref(false)


// method=====================================

// 提交表单
const onSubmit = () => {
  loginRef.value.validate((valid) => {
    if (valid) {
      console.log(form.username);
    }
  })
}

// 重置表单
const onReset = () => {
  form.username = ''
  form.password = ''
}

// 切换密码可见性
const togglePasswordVisibility = () => {
  showPassword.value = !showPassword.value
}

// 输入框获得焦点时
const handleFocus = () => {
  isFocused.value = !isFocused.value
}

// 输入框失去焦点时
const handleBlur = () => {
  isFocused.value = !isFocused.value
}
// 注册
const signUp = () => {
  router.push({ name: 'register' })
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
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="form.username"
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
  box-shadow: 0 0 50px #0044ffec;
  transition: all 0.5s;
}
.active-shadow {
  border: 5px solid #dd00ff6c;
  box-shadow: 0 0 50px #ff00e696;
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

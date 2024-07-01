<script setup>
import apiRequests from '@/apis';
import { reactive, ref, getCurrentInstance } from 'vue'
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import CryptoJS from 'crypto-js';
// data============================================
const router = useRouter()
const registerRef = ref(null)
const ageOptions = Array.from({ length: 121 }, (_, i) => i) // 生成0到120的数组
// rules
const registerRule = {
  account: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  password: [
    { required: true, message: '密码不能为空', trigger: 'blur' },
    { min: 3, max: 20, message: '密码长度应为3到20个字符', trigger: 'blur' }
  ],
  confirmPassword: [
    { required: true, message: '确认密码不能为空', trigger: 'blur' },
    {
      validator: (rule, value, callback) => {
        if (value !== form.password) {
          callback(new Error('确认密码与密码不一致'));
        } else {
          callback();
        }
      }, trigger: 'blur'
    }
  ],
  email: [{ required: true, message: '邮箱不能为空', trigger: 'blur' }],
  phone: [{ required: true, message: '电话不能为空', trigger: 'blur' }],
  question: [{ required: true, message: '密码问题不能为空', trigger: 'blur' }],
  asw: [{ required: true, message: '答案不能为空', trigger: 'blur' }],
  age: [{ required: true, message: '请选择年龄', trigger: 'change' }],
  sex: [{ required: true, message: '性别不能为空', trigger: 'change' }],
  name: [{ required: true, message: '姓名不能为空', trigger: 'blur' }],
}

// 输入框焦点状态
const isFocused = ref(false)

// 创建表单数据
const form = reactive({
  account: '',
  password: '',
  confirmPassword: '',
  email: '',
  phone: '',
  question: '',
  asw: '',
  age: '',
  sex: '',
  createTime: '',
  updateTime: '',
  name: ''
})

// 查看密码切换
const showPassword = ref(false)


// method=====================================

// 提交表单
const onSubmit = async () => {
  registerRef.value.validate(async (valid) => {
    if (valid) {
      try {
        // 对密码进行MD5加密
        const encryptedPassword = CryptoJS.MD5(form.password).toString().toUpperCase();
        const formData = {
          ...form,
          password: encryptedPassword
        };

        const response = await apiRequests.signUp(formData);
        // console.log(response);

        if (response.success) {
          // 注册成功处理
          ElMessage.success(response.message);
          router.replace({ name: 'login' })
        } else {
          // 注册失败处理
          ElMessage.error(response.message);
        }
      } catch (error) {
        // 请求失败处理
        ElMessage.error("注册失败，请稍后再试");
        console.error(error);
      }
    }
  });
};

// 重置表单
const onReset = () => {
  form.account = ''
  form.password = ''
  form.confirmPassword = ''
  form.email = ''
  form.phone = ''
  form.question = ''
  form.asw = ''
  form.age = ''
  form.sex = ''
  form.name = ''
}

// 切换密码可见性
const togglePasswordVisibility = () => {
  showPassword.value = !showPassword.value
}

// 输入框获得焦点时
const handleFocus = () => {
  isFocused.value = true
}

// 输入框失去焦点时
const handleBlur = () => {
  isFocused.value = false
}
</script>

<template>
  <div class="container">
    <div
      :class="{
        register: true,
        'active-shadow': isFocused,
        'unactive-shadow': !isFocused,
      }"
    >
      <el-form
        :model="form"
        label-width="auto"
        style="max-width: 1500px"
        :rules="registerRule"
        ref="registerRef"
        class="register-form"
      >
        <div class="form-left">
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
          <!-- 确认密码 -->
          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input
              v-model="form.confirmPassword"
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
          <!-- 邮箱 -->
          <el-form-item label="邮箱" prop="email">
            <el-input
              v-model="form.email"
              :style="{ width: '350px' }"
              @focus="handleFocus"
              @blur="handleBlur"
            />
          </el-form-item>
          <!-- 电话 -->
          <el-form-item label="电话" prop="phone">
            <el-input
              v-model="form.phone"
              :style="{ width: '350px' }"
              @focus="handleFocus"
              @blur="handleBlur"
            />
          </el-form-item>
        </div>
        <div class="form-right">
          <!-- 密码问题 -->
          <el-form-item label="密码问题" prop="question">
            <el-input
              v-model="form.question"
              :style="{ width: '350px' }"
              @focus="handleFocus"
              @blur="handleBlur"
            />
          </el-form-item>
          <!-- 答案 -->
          <el-form-item label="答案" prop="asw">
            <el-input
              v-model="form.asw"
              :style="{ width: '350px' }"
              @focus="handleFocus"
              @blur="handleBlur"
            />
          </el-form-item>
          <!-- 年龄 -->
          <el-form-item label="年龄" prop="age">
            <el-select
              v-model="form.age"
              :style="{ width: '350px' }"
              placeholder="请选择年龄"
              @focus="handleFocus"
              @blur="handleBlur"
            >
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
            <el-input
              v-model="form.name"
              :style="{ width: '350px' }"
              @focus="handleFocus"
              @blur="handleBlur"
            />
          </el-form-item>
        </div>
      </el-form>
      <!-- 注册按钮 -->
      <el-button class="btn-list" type="primary" @click="onSubmit"
        >注册</el-button
      >
      <el-button class="btn-list" @click="onReset">重置</el-button>
    </div>
  </div>
</template>

<style>
.el-form-item {
  width: 450px;
}
.register {
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

.register-form {
  display: flex;
  flex-direction: row;
}

.form-left,
.form-right {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}

.el-form-item {
  position: relative;
}

.btn-list {
  width: 100px;
  margin: 10px;
}
</style>
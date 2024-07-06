<script setup>
import { useStore } from "vuex";
import { ref, reactive, computed, onMounted } from "vue";
import { ElMessage } from "element-plus";
import address from '@/assets/javascript/address';
import apiRequests from '@/apis';

const store = useStore();
const editRef = ref(null);

const form = reactive({
  userId: '',
  name: '',
  phone: '',
  mobile: '',
  province: '',
  city: '',
  district: '',
  addr: '',
  zip: '',
  dfault: '',
  isDel: '',
  createTime: '',
  updateTime: ''
});

const resetForm = () => {
  form.userId = '';
  form.name = '';
  form.phone = '';
  form.mobile = '';
  form.province = '';
  form.city = '';
  form.district = '';
  form.addr = '';
  form.zip = '';
  form.dfault = '';
  form.isDel = '';
  form.createTime = '';
  form.updateTime = '';
};

const editRule = {
  name: [{ required: true, message: '收件人不能为空', trigger: 'blur' }],
  phone: [{ required: true, message: '电话号码不能为空', trigger: 'blur' }],
  zip: [{ required: true, message: '邮政编码不能为空', trigger: 'blur' }]
};

const addUserAddressInfoDiaVisible = computed(() => store.getters.addUserAddressInfoDiaVisible);
const dialogWidth = '500px';
const getuserId = computed(() => store.getters.userId);

const provinces = computed(() => address);
const cities = ref([]);
const districts = ref([]);

const handleProvinceChange = (value) => {
  const selectedProvince = address.find(province => province.name === value);
  cities.value = selectedProvince.children;
  form.province = selectedProvince.name;
  form.city = '';
  form.district = '';
};

const handleCityChange = (value) => {
  const selectedCity = cities.value.find(city => city.name === value);
  districts.value = selectedCity.children;
  form.city = selectedCity.name;
  form.district = '';
};

const onSubmit = async () => {
  await editRef.value.validate(async (valid) => {
    if (valid) {
      form.userId = getuserId.value;
      const res = await apiRequests.addUserAddress(form);
      if (res.success) {
        ElMessage.success(res.message);
        resetForm();
        store.commit('setAddUserAddressInfoDiaVisible', false);
        console.log(store.getters.isUserAddrPageFresh);

        store.commit('setIsUserAddrPageFresh', !store.getters.isUserAddrPageFresh);
        console.log(store.getters.isUserAddrPageFresh);
      } else {
        ElMessage.error(res.message);
      }
    }
  });

  store.commit('setAddUserAddressInfoDiaVisible', false);
};

const cancelEidt = () => {
  resetForm();
  store.commit('setAddUserAddressInfoDiaVisible', false);
};

onMounted(() => {
  // Fetch initial data or perform any other initialization
});

</script>

<template>
  <el-dialog
    v-model="addUserAddressInfoDiaVisible"
    title="添加地址信息"
    :close-on-click-modal="false"
    :show-close="false"
    :width="dialogWidth"
  >
    <el-form
      :model="form"
      label-width="80px"
      :rules="editRule"
      ref="editRef"
      class="edit-form"
    >
      <el-form-item label="收件人" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="固定手机" prop="phone">
        <el-input v-model="form.phone" />
      </el-form-item>
      <el-form-item label="移动手机" prop="mobile">
        <el-input v-model="form.mobile" />
      </el-form-item>
      <el-form-item label="省份" prop="province">
        <el-select
          v-model="form.province"
          placeholder="请选择省份"
          @change="handleProvinceChange"
        >
          <el-option
            v-for="province in provinces"
            :key="province.code"
            :label="province.name"
            :value="province.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="城市" prop="city">
        <el-select
          v-model="form.city"
          placeholder="请选择城市"
          @change="handleCityChange"
        >
          <el-option
            v-for="city in cities"
            :key="city.code"
            :label="city.name"
            :value="city.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="县" prop="district">
        <el-select v-model="form.district" placeholder="请选择县/区">
          <el-option
            v-for="district in districts"
            :key="district.code"
            :label="district.name"
            :value="district.name"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="详细地址" prop="addr">
        <el-input v-model="form.addr" />
      </el-form-item>
      <el-form-item label="邮编" prop="zip">
        <el-input v-model="form.zip" />
      </el-form-item>
    </el-form>
    <div>
      <el-button class="btn-list" type="primary" @click="onSubmit"
        >确认</el-button
      >
      <el-button class="btn-list" @click="cancelEidt">取消</el-button>
    </div>
  </el-dialog>
</template>

<style>
.edit-form {
  width: 100%;
}
.btn-list {
  width: 100px;
  margin: 10px;
}
</style>

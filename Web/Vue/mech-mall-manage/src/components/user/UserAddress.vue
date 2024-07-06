<template>
  <div class="address-container">
    <el-button type="primary" class="add-address" @click="handleAddAddress"
      >新增收货地址</el-button
    >
    <h3>选择收货地址</h3>
    <div class="address-list">
      <div
        v-for="(address, index) in addressList"
        :key="address.id"
        :class="['address-item', { default: address.dfault }]"
      >
        <p>{{ address.name }} 收</p>
        <p>{{ address.province }} {{ address.city }}</p>
        <p>{{ address.phone }}</p>
        <div class="address-actions">
          <button @click="deleteAddress(address.id, index)">删除</button>
          <el-button @click="setDfault(index)" v-if="!address.dfault"
            >设为默认地址</el-button
          >
        </div>
      </div>
    </div>
    <addUserAddress></addUserAddress>
  </div>
</template>

<script setup>
import apiRequests from '@/apis';
import { ref, computed, onMounted, watch } from 'vue';
import { useStore } from 'vuex';
import addUserAddress from './addUserAddress.vue';

const addressList = ref([]);
const store = useStore();
//改默认值
const setDfault = async (index) => {
  const addressId = addressList.value[index].id;

  try {
    const res = await apiRequests.setDfaultAddress(addressId, getuserId.value);
    if (res.success) {
      getAllAddress();
      ElMessage.success(res.message);
    } else {
      ElMessage.error(res.message);
    }
  } catch (error) {
    console.error('Error setting default address:', error);
  }
};
//增加
const handleAddAddress = () => {
  store.commit('setAddUserAddressInfoDiaVisible', true);
};
//删除
const deleteAddress = async (id, index) => {
  try {
    const res = await apiRequests.deleteUserAddress(id);
    if (res.success) {
      getAllAddress();
      ElMessage.success(res.message);
    } else {
      ElMessage.error(res.message);
      getAllAddress();
    }
  } catch (error) {
    console.error('Error deleting address:', error);
  }
};
//获取所有
const getAllAddress = async () => {
  try {
    const res = await apiRequests.getUserAddress(getuserId.value);
    addressList.value = res.data;
  } catch (error) {
    console.error('Error fetching addresses:', error);
  }
};

const getuserId = computed(() => store.getters.userId);

const isUserAddrPageFresh = computed(() => store.getters.isUserAddrPageFresh)
watch(isUserAddrPageFresh, (newVal, oldVal) => {
  if (newVal != oldVal)
    getAllAddress()
  console.log(newVal + ' ' + oldVal);

  console.log(isUserAddrPageFresh.value);


})

onMounted(() => {
  getAllAddress();
});
</script>

<style scoped>
.address-container {
  padding: 20px;
}

.address-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.address-item {
  border: 2px solid #ccc;
  border-radius: 8px;
  padding: 20px;
  width: 200px;
  position: relative;
  font-size: 20px; /* 调整字体大小 */
}

.address-item.default {
  border-color: rgb(99, 169, 255);
}

.address-item p {
  margin: 0 0 10px;
}

.address-actions {
  display: flex;
  justify-content: space-between;
}

.address-actions button {
  border: none;
  background-color: transparent;
  color: rgb(46, 115, 201);
  cursor: pointer;
  padding: 0;
  font-size: 15px;
}

.address-actions button:hover {
  text-decoration: underline;
}

.add-address {
  margin-bottom: 30px;
}

.add-address:hover {
  text-decoration: underline;
}

.address-item.default::after {
  content: '默认地址';
  color: white;
  background-color: rgba(0, 145, 255, 0.874);
  padding: 2px 8px;
  position: absolute;
  top: 8px;
  right: 8px;
  border-radius: 8px;
  font-size: 12px;
}
</style>

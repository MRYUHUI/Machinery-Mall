<script setup>
import { computed, ref, onMounted } from 'vue';
import GoodImage from './GoodImage.vue';
import { useStore } from 'vuex';
import { ElSelect, ElOption, ElInputNumber, ElButton, ElMessage } from 'element-plus';
import apiRequests from '@/apis';

// data =======================================
const store = useStore();
// 购买数量
const quantity = ref(1);
// 选中的配送地址
const selectedAddress = ref(null);
// 用户地址列表
const addrList = ref([])

const order = {
  orderNo: null,
  uid: null,
  addrId: null,
  amount: null,
  type: 1,
  freight: 0,
  status: 1,
  paymentTime: null,
  deliveryTime: null,
  finishTime: null,
  closeTime: null,
  created: null,
  updated: null
};
const orderItem = {
  id: null,           // 自增主键
  uid: null,          // 用户ID
  orderId: null,     // 订单ID
  goodsId: null,     // 商品ID
  goodsName: '',     // 商品名称
  iconUrl: '',       // 图标URL
  price: 0.00,        // 单价
  quantity: 0,        // 数量
  totalPrice: 0.00,  // 总价
  created: null, // 创建时间
  updated: null  // 更新时间
};

// method ======================================
const generateOrderNo = () => {
  const timestamp = Date.now(); // 获取当前时间戳
  const randomNum = Math.floor(Math.random() * 1000000); // 生成随机数
  return `${timestamp}${randomNum}`;
}
const setOrder = () => {
  order.orderNo = generateOrderNo();
  order.uid = userId.value;
  order.addrId = selectedAddress.value;
  order.amount = null;
  order.type = 1;
  order.freight = 0;
  order.status = 1;
  order.paymentTime = null;
  order.deliveryTime = null;
  order.finishTime = null;
  order.closeTime = null;
  order.created = null;
  order.updated = null;
}
const setOrderItem = () => {
  orderItem.uid = userId.value;
  orderItem.orderId = null;
  orderItem.goodsId = curProduct.value.id;
  orderItem.goodsName = curProduct.value.name;
  orderItem.iconUrl = curProduct.value.iconUrl;
  orderItem.price = curProduct.value.price;
  orderItem.quantity = quantity.value;
  orderItem.totalPrice = curProduct.value.price * quantity.value;
  orderItem.created = null;
  orderItem.updated = null;
}
// 购买
const buyNow = async () => {
  if (!isLogin.value) {
    ElMessage.error('请先登录')
    return
  }
  setOrder()
  order.amount = quantity.value * curProduct.value.price
  const res1 = await apiRequests.addOrder(order)
  if (!res1.success) {
    ElMessage.error(res1.message)
    return
  }
  setOrderItem()
  orderItem.orderId = res1.data

  const res2 = await apiRequests.addOrderItem(orderItem)
  if (!res2.success) {
    ElMessage.error(res2.message)
    return
  }
  // 减少库存
  store.commit('setStock', store.getters.stock - quantity.value)
  const res3 = await apiRequests.updateStock(curProduct.value)
  if (res3.success) {
    ElMessage.success(res3.message)
    init()
  }
  else {
    ElMessage.error(res3.message)
  }

};

// 加入购物车
const addToCart = async () => {
  if (!isLogin.value) {
    ElMessage.error('请先登录')
    return
  }
  try {
    const res = await apiRequests.addCartItem(userId.value, curProduct.value.id, quantity.value);
    if (res.success) {
      ElMessage.success('加入购物车成功');
    } else {
      ElMessage.error(res.message || '加入购物车失败');
    }
  } catch (error) {
    ElMessage.error('请求处理异常');
  }
};
// computed ============================
// 当前商品
const curProduct = computed(() => store.getters.selectedProductInfo);
const userId = computed(() => store.getters.userId)
const isLogin = computed(() => store.getters.isLogin)
const init = async () => {
  // 初始化时设置购买数量为1
  quantity.value = 1;
  if (isLogin.value) {
    // 获取用户地址
    const { data: res } = await apiRequests.getUserAddress(userId.value)
    addrList.value = res
    // 查找default字段为1的地址
    const defaultAddress = addrList.value.find(address => address.dfault === 1)
    if (defaultAddress) {
      selectedAddress.value = defaultAddress.id
    }
  }
}
// hook ======================
onMounted(async () => {
  init()
});
</script>

<template>
  <div class="product-detail">
    <GoodImage></GoodImage>
    <div class="product-info">
      <h1 class="product-name">{{ curProduct.name }}</h1>
      <div class="price-info">
        <div class="price-box">
          <div class="current-price">¥{{ curProduct.price }}</div>
          <div class="promotion">描述：{{ curProduct.detail }}</div>
          <div class="product-code">商品编号：{{ curProduct.id }}</div>
        </div>
      </div>
      <div class="stock-info">发货地：福建省厦门市思明区</div>
      <div class="stock-info">库存：{{ curProduct.stock }}</div>
      <div class="divid-line">
        <div class="delivery-info">
          <label for="address">配送至：</label>
          <el-select
            v-model="selectedAddress"
            placeholder="请选择地址"
            style="width: 350px"
          >
            <el-option
              v-for="address in addrList"
              :key="address.id"
              :label="`${address.province}${address.city}${address.district}${address.addr}`"
              :value="address.id"
            ></el-option>
          </el-select>
        </div>

        <div class="quantity-section">
          <label for="quantity">数 量： </label>
          <el-input-number
            v-model="quantity"
            :min="1"
            :max="curProduct.stock"
            controls-position="right"
          />
        </div>
      </div>
      <div class="purchase-method">
        <span class="green-box"><i class="el-icon-check"></i> 全额支付</span>
      </div>
      <div class="action-buttons">
        <el-button type="primary" @click="buyNow">立即购买</el-button>
        <el-button @click="addToCart">加入购物车</el-button>
      </div>
    </div>
  </div>
</template>



<style scoped>
.product-detail {
  display: flex;
  gap: 20px;
  padding: 40px;
  background-color: #f9f9f9;
  width: 1400px;
  box-shadow: 0 0 10px #00000086;
  border-radius: 20px;
  margin: 20px;
}

.product-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 500px;
}

.product-name {
  font-size: 32px;
  font-weight: bold;
  margin-bottom: 10px;
}

.price-info {
  display: flex;
  align-items: center;
  gap: 10px;
  background-color: #f0f0f0;
}

.price-box {
  padding: 10px;
  border-radius: 5px;
}

.current-price {
  font-size: 28px;
  font-weight: bold;
  color: #f56c6c; /* 使用指定的价格颜色 */
}

.promotion,
.product-code,
.stock-info,
.delivery-info,
.quantity-section {
  font-size: 14px;
  color: #333;
}

.promotion {
  margin-top: 5px;
}

.product-code {
  margin-top: 5px;
}

.stock-info {
  margin-top: 5px;
}

.delivery-info {
  margin-top: 10px;
  display: flex;
  align-items: center;
}

.quantity-section {
  margin-top: 10px;
  display: flex;
  align-items: center;
}

.purchase-method {
  margin-top: 10px;
}

.green-box {
  display: inline-block;
  padding: 0 5px;
  background-color: #8bc34a;
  color: #fff;
  border-radius: 3px;
  font-size: 20px;
}

.el-button {
  width: 150px;
}

.divid-line {
  border-top: 1px solid #3333333f;
  border-bottom: 1px solid #3333333f;
  padding: 20px;
}
</style>

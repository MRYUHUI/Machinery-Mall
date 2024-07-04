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
          <el-select v-model="selectedAddress" placeholder="请选择地址">
            <el-option label="地址1" value="address1"></el-option>
            <el-option label="地址2" value="address2"></el-option>
            <el-option label="地址3" value="address3"></el-option>
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

<script setup>
import { computed, ref, onMounted } from 'vue';
import GoodImage from './GoodImage.vue';
import { useStore } from 'vuex';
import { ElSelect, ElOption, ElInputNumber, ElButton } from 'element-plus';

const store = useStore();



// 当前商品
const curProduct = computed(() => store.getters.selectedProductInfo);

// 购买数量
const quantity = ref(1);

// 选中的配送地址
const selectedAddress = ref('address1');

// 购买
const buyNow = () => {
  console.log('立即购买');
};

// 加入购物车
const addToCart = () => {
  console.log('加入购物车，数量: ', quantity.value);
};

onMounted(() => {
  // 初始化时设置购买数量为1
  quantity.value = 1;
});
</script>

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

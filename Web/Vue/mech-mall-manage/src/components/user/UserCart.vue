<script setup>
import { ref, computed, onMounted } from 'vue';
import { SystemConsts } from "@/enums/SystemConsts";
import apiRequests from "@/apis";
import zhCn from 'element-plus/lib/locale/lang/zh-cn';
import { ElConfigProvider } from 'element-plus';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import { ElSelect, ElOption, ElInputNumber, ElButton, ElMessage } from 'element-plus';
// data
const store = useStore();
const router = useRouter();
const cartItems = ref([]);
const currentPage = ref(1);
const pageSize = ref(8);
const totalCartItems = ref(0);
// 用户地址列表
const addrList = ref([])
// 选中的配送地址
const selectedAddress = ref(null);
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
// 获取全部购物车商品
const fetchCartItems = async (page = 1, size = 8) => {
  try {
    const response = await apiRequests.allCartItem(page, size);
    cartItems.value = response.data;

    for (const item of cartItems.value) {
      const res = await apiRequests.findProductById(item.productId);
      item.name = res.data.name;
      item.price = res.data.price;
    }

    totalCartItems.value = response.total;
    console.log(cartItems.value);

    const { data: res } = await apiRequests.getUserAddress(userId.value)
    addrList.value = res
    // 查找default字段为1的地址
    console.log('=============');

    const defaultAddress = addrList.value.find(address => address.dfault === 1)
    console.log(defaultAddress);

    if (defaultAddress) {
      selectedAddress.value = defaultAddress.id
      console.log(selectedAddress.value);

    }
  } catch (error) {
    console.error('Failed to fetch cart items:', error);
  }
};


// 更新数据库数量
const handleUpdateQuantity = async (id, quantity) => {
  const itemIndex = cartItems.value.findIndex(item => item.id === id);
  if (itemIndex !== -1) {
    if (isNaN(quantity) || quantity <= 0) {
      quantity = 1;
    } else if (quantity > cartItems.value[itemIndex].stock) {
      quantity = cartItems.value[itemIndex].stock;
    }
    cartItems.value[itemIndex].quantity = quantity;
    try {
      await apiRequests.updateCartItem(id, quantity);
      console.log(`Item ${id} quantity updated to ${quantity}`);
    } catch (error) {
      console.error('Failed to update item quantity:', error);
    }
  }
};

// 删除商品
const removeItem = async (id) => {
  try {
    await apiRequests.deleteCartItem(id);
    cartItems.value = cartItems.value.filter(item => item.id !== id);
    totalCartItems.value = totalCartItems.value - 1 < 0 ? 0 : totalCartItems.value - 1;
  } catch (error) {
    console.error('Failed to remove item:', error);
  }
};

// 清空购物车
const clearCart = async () => {
  try {
    await apiRequests.clearCart();
    cartItems.value = [];
    totalCartItems.value = 0;
    console.log('Cart cleared');
  } catch (error) {
    console.error('Failed to clear cart:', error);
  }
};

// 分页函数
const handlePageChange = (page) => {
  currentPage.value = page;
  fetchCartItems(page, pageSize.value);
};
const handleSizeChange = (size) => {
  pageSize.value = size;
  fetchCartItems(currentPage.value, size);
};
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
const setOrderItem = (curQuantity) => {
  orderItem.uid = userId.value;
  orderItem.orderId = null;
  orderItem.goodsId = curProduct.value.id;
  orderItem.goodsName = curProduct.value.name;
  orderItem.iconUrl = curProduct.value.iconUrl;
  orderItem.price = curProduct.value.price;
  orderItem.quantity = curQuantity;
  orderItem.totalPrice = curProduct.value.price * curQuantity;
  orderItem.created = null;
  orderItem.updated = null;
}
// 购买
const GoToPay = async (row) => {
  setOrder()
  order.amount = row.quantity * curProduct.value.price
  console.log(order);

  const res1 = await apiRequests.addOrder(order)
  if (!res1.success) {
    ElMessage.error(res1.message)
    return
  }
  setOrderItem(row.quantity)
  orderItem.orderId = res1.data
  console.log(orderItem);

  const res2 = await apiRequests.addOrderItem(orderItem)
  if (!res2.success) {
    ElMessage.error(res2.message)
    return
  }
  // 减少库存
  store.commit('setStock', store.getters.stock - row.quantity)
  const res3 = await apiRequests.updateStock(curProduct.value)
  if (res3.success) {
    ElMessage.success(res3.message)
    fetchCartItems(currentPage.value, pageSize.value);
    removeItem(row.id)
  }
  else {
    ElMessage.error(res3.message)
  }

};

// ‘去购物’按钮函数
const navigateToHome = () => {
  router.push({ name: 'user-home' });
};
const curProduct = computed(() => store.getters.selectedProductInfo);
const userId = computed(() => store.getters.userId)
// onMounted
onMounted(() => {
  fetchCartItems(currentPage.value, pageSize.value);
});
</script>

<template>
  <!-- 购物车不为空时 -->
  <div v-if="totalCartItems > 0" class="user-contianer">
    <el-table
      :data="cartItems"
      style="width: 100%"
      class="full-width-table"
      highlight-current-row
      :header-cell-style="SystemConsts.headerCellStyle"
      :cell-style="SystemConsts.cellStyle"
      :header-cell-class-name="'fixed-header'"
    >
      <el-table-column
        type="selection"
        width="80"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="name"
        label="商品名"
        align="center"
        class="flex-column"
      />
      <el-table-column
        prop="price"
        label="价钱"
        align="center"
        class="flex-column"
      />
      <el-table-column
        prop="quantity"
        label="数量"
        align="center"
        class="flex-column"
      >
        <template v-slot="{ row }">
          <el-input
            type="number"
            v-model="row.quantity"
            @input="handleUpdateQuantity(row.id, row.quantity)"
            min="1"
          />
        </template>
      </el-table-column>
      <el-table-column label="总价" align="center" class="flex-column">
        <template v-slot="{ row }">
          <span>总价: ¥{{ (row.quantity * row.price).toFixed(2) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class="flex-column">
        <template #default="{ row }">
          <div class="action-buttons">
            <el-button type="danger" size="mini" @click="removeItem(row.id)"
              >删除</el-button
            >
            <el-button type="primary" size="mini" @click="GoToPay(row)"
              >结算</el-button
            >
          </div>
        </template>
      </el-table-column>
    </el-table>
    <div class="pagination-and-actions">
      <div class="pagination-container">
        <el-config-provider :locale="zhCn">
          <el-pagination
            @size-change="handleSizeChange"
            @current-change="handlePageChange"
            :current-page="currentPage"
            :page-size="pageSize"
            :page-sizes="[1, 3, 5, 8, 10, 12, 20]"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totalCartItems"
          />
        </el-config-provider>
      </div>
      <div class="price-and-end">
        <div class="space-div"></div>
        <div class="clear-bar">
          <el-button
            class="clear-button"
            type="primary"
            size="medium"
            @click="clearCart()"
            >清空购物车</el-button
          >
        </div>
      </div>
    </div>
  </div>
  <!-- 购物车为空时 -->
  <div v-else class="empty-cart">
    <p class="empty-cart-text">购物车为空</p>
    <a @click="navigateToHome" class="return-home-link">去购物</a>
  </div>
</template>

<style>
.user-contianer {
  width: 75%;
  padding-top: 20px;
}

.full-width-table {
  width: 100%;
}

.pagination-and-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
}

.pagination-container {
  flex: 1;
  display: flex;
  justify-content: flex-start;
}

.price-and-end {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  margin-left: 20px; /* 添加左边距与分页栏分开 */
}

.totalPrice-bar {
  display: flex;
  align-items: center;
  margin: 0;
}

.action-buttons .el-button {
  flex-shrink: 0; /* 防止按钮缩小 */
}

.pay-bar {
  display: flex;
  align-items: center;
}

.pay-button {
  width: 105px;
}

.clear-bar {
  display: flex;
  align-items: center;
}

.clear-button {
  height: 40px;
  margin-left: 20px;
  margin-right: 5px;
  width: 110px;
}

.space-div {
  display: flex;
  align-items: center;
  flex-grow: 1;
}

.empty-cart {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 80vh;
  width: 150vh;
  text-align: center;
}

.empty-cart-text {
  font-size: 20px;
  color: #999; /* 灰色 */
  margin-bottom: 20px;
  font-weight: bold; /* 文字加粗 */
}

.return-home-link {
  font-size: 16px;
  color: #007bff; /* 蓝色 */
  cursor: pointer;
  text-decoration: underline;
}
</style>

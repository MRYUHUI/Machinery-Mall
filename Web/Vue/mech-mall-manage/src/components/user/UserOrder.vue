<template>
  <div class="order-container">
    <div
      v-for="orderItem in orderItemList"
      :key="orderItem.orderId"
      class="order-details"
    >
      <div class="order-info">
        <div class="order-info-item">
          订单编号：{{
            orderList.find((o) => o.id === orderItem.orderId)?.orderNo
          }}
        </div>
        <div class="order-info-item">下单时间：{{ orderItem.created }}</div>
        <div class="order-info-item">
          收件人：{{
            addressList.find(
              (o) =>
                o.id ===
                orderList.find((o) => o.id === orderItem.orderId)?.addrId
            )?.name
          }}
        </div>
        <div class="order-info-item">
          订单状态：{{
            getStatusText(
              orderList.find((o) => o.id === orderItem.orderId)?.status
            )
          }}
        </div>
        <div class="order-info-item">
          订单总额：<span class="total-amount">{{ orderItem.totalPrice }}</span>
        </div>
        <!-- 未支付 -->
        <div
          class="not-pay"
          v-if="orderList.find((o) => o.id === orderItem.orderId)?.status === 1"
        >
          <!-- 买家账户：qcbvcn4823@sandbox.com
				密码：111111 -->
          <el-button
            size="mini"
            type="primary"
            @click="
              payOrder(
                orderList.find((o) => o.id === orderItem.orderId),
                orderItem.goodsName
              )
            "
            >支付</el-button
          ><el-button size="mini" @click="cancelOrder(orderItem)"
            >取消订单</el-button
          >
        </div>
        <!-- 已支付 -->
        <div v-else class="have-pay">已支付</div>

        <el-button size="mini" @click="showOrderDetails(orderItem)"
          >商品详情</el-button
        >
      </div>
      <el-table :data="[orderItem]" style="width: 100%">
        <el-table-column prop="goodsName" label="商品名称" align="center" />
        <el-table-column prop="price" label="单价（元）" align="center" />
        <el-table-column prop="quantity" label="数量" align="center" />
        <el-table-column prop="totalPrice" label="合计（元）" align="center" />
      </el-table>
    </div>
  </div>
  <detailUserOrder></detailUserOrder>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import apiRequests from '@/apis';
import { useStore } from 'vuex';
import detailUserOrder from './detailUserOrder.vue';
import { ElMessage, ElMessageBox } from 'element-plus';

const orderItemList = ref([]);
const orderList = ref([]);
const addressList = ref([]);
const store = useStore();

const getuserId = computed(() => store.getters.userId);
//状态值
const getStatusText = (status) => {
  switch (status) {
    case 1:
      return '未付款';
    case 2:
      return '已付款';
    case 3:
      return '已发货';
    case 4:
      return '交易成功';
    case 5:
      return '交易关闭';
    case 6:
      return '已取消';
    default:
      return '未知状态';
  }
};


//获取订单项
const getAllOrderItem = async () => {
  try {
    const res = await apiRequests.getAllOrderItem(getuserId.value);
    orderItemList.value = res.data;
  } catch (error) {
    console.error('Error fetching addresses:', error);
  }
}
//订单表
const getOrderByUserId = async () => {
  try {
    const res = await apiRequests.getOrderByUserId(getuserId.value);
    orderList.value = res.data;
  } catch (error) {
    console.error('Error fetching addresses:', error);
  }
}
//获取地址表
const getAddressByUserId = async () => {
  try {
    const res = await apiRequests.getUserAddress(getuserId.value);
    addressList.value = res.data;
  } catch (error) {
    console.error('Error fetching addresses:', error);
  }
}



const showOrderDetails = (order) => {

  store.commit('setSelectedUserOrderItemInfo', order);
  const selectedOrder = orderList.value.find(o => o.id === order.orderId);
  store.commit('setSelectedUserOrderInfo', selectedOrder);
  const selectedAddress = addressList.value.find(o => o.id === selectedOrder.addrId);

  store.commit('setSelectedUserAddressInfo', selectedAddress)

  // 设置对话框可见
  store.commit('setDetailOrderItemDInfoiaVisible', true);
};

// 删除订单和订单项
const cancelOrder = async (orderItem) => {
  // 更新库存
  const { data: oldStock } = await apiRequests.findStockById(orderItem.goodsId)
  const curStock = oldStock + orderItem.quantity
  const product = {
    id: orderItem.goodsId,
    stock: curStock
  }
  const res1 = await apiRequests.updateStock(product)
  if (!res1.success) {
    ElMessage.error(res.message)
    return
  }
  const orderId = orderList.value.find((o) => o.id === orderItem.orderId)?.id
  const orderItemId = orderItem.id
  const res = await apiRequests.deleteOrderAndOrderItem(orderId, orderItemId)
  if (res.success) {
    getAllOrderItem();
    getOrderByUserId();
    ElMessage.success(res.message)
  } else {
    ElMessage.error(res.message)
  }
}

// 支付订单
const payOrder = (order, goodName) => {

  // 弹出确认框
  ElMessageBox.confirm('确认支付？ 是否继续?', '付款确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    // 构造支付请求URL
    const orderId = order.id
    const subject = goodName;
    const traceNo = order.orderNo;
    const totalAmount = order.amount;
    console.log(`${subject}\n${traceNo}\n${totalAmount}\n`);

    // 打开支付页面
    window.open(`http://localhost:8080/alipay/pay?subject=${subject}&traceNo=${traceNo}&totalAmount=${totalAmount}`);
    // 显示付款成功信息
    ElMessage.success('正在跳转到支付页面...');
  }).catch(() => {
    // 用户点击取消后，显示取消信息
    ElMessage.info('已取消付款');
  });
}



onMounted(() => {
  getAllOrderItem();
  getOrderByUserId();
  getAddressByUserId();
});
</script>

<style>
.order-container {
  width: 1100px;
  margin: 0 auto;
  /* padding: 20px; */
}
.order-info-item {
  font-size: 14px; /* 调整字体大小 */
  margin-bottom: 5px;
}

.search-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-input {
  width: 300px;
}

.order-details {
  margin-top: 20px;
  border-top: 1px solid #ebebeb;
  padding-top: 20px;
}

.order-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
}

.total-amount {
  font-weight: bold;
  color: red;
}

.have-pay {
  font-size: 20px;
  font-weight: bold;
  color: rgb(2, 71, 0);
}
</style>

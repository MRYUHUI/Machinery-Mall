<template>
  <div>
    <!-- 对话框 -->
    <el-dialog
      title="订单详情"
      v-model="adminDetailOrderItemDInfoiaVisible"
      width="50%"
      :before-close="handleClose"
      :close-on-click-modal="false"
      :show-close="false"
    >
      <!-- 对话框内容 -->
      <div>
        <div class="order-info-item">
          订单状态：{{ getStatusText(orderInfo.status) }}
        </div>
        <div class="order-info-item">订单编号：{{ orderInfo.orderNo }}</div>
        <div class="order-info-item">下单时间：{{ orderInfo.created }}</div>
        <div class="order-info-item">应支付金额：￥{{ orderInfo.amount }}</div>
        <div class="order-info-item">
          支付方式：{{ getPayType(orderInfo.type) }}
        </div>
        <div class="order-info-item">支付时间：{{ orderInfo.paymentTime }}</div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="canceDetail()">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
  
<script setup>
import { useStore } from "vuex";
import { computed, ref, reactive, watch, onMounted } from "vue";
import { ElMessage } from "element-plus";
import apiRequests from "@/apis"

const store = useStore();
const orderInfo = ref([]);

const adminDetailOrderItemDInfoiaVisible = computed(() => store.getters.adminDetailOrderItemDInfoiaVisible);

const orderItemInfo = computed(() => store.getters.selectedUserOrderItemInfo)
const id = orderItemInfo.value.orderId;

const getOrder = async () => {
  const res = await apiRequests.getOrderById(id)
  orderInfo.value = res.data

}


const canceDetail = () => {
  store.commit('setAdminDetailOrderItemDInfoiaVisible', false);

}

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

const getPayType = (type) => {
  switch (type) {
    case 1:
      return '在线支付';
    case 2:
      return '货到付款';
    default:
      return '未知';
  }

}
onMounted(() => {

  console.log("onMounted");
  console.log(orderItemInfo.value);
  if (adminDetailOrderItemDInfoiaVisible.value)
    getOrder()
})

</script>
  
<style>
.order-info-item {
  margin-bottom: 10px;
}
.dialog-footer {
  text-align: right;
}
</style>
  
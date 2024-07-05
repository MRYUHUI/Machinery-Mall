<template>
  <div>
    <!-- 对话框 -->
    <el-dialog
      title="订单详情"
      v-model="detailOrderItemDInfoiaVisible"
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
        <div class="order-info-item">
          收货地址：{{ addressInfo.province }} {{ addressInfo.city }}
          {{ addressInfo.district }}
        </div>
        <div class="order-info-item">订单编号：{{ orderInfo.orderNo }}</div>
        <div class="order-info-item">下单时间：{{ orderInfo.created }}</div>
        <div class="order-info-item">应支付金额：￥{{ orderInfo.amount }}</div>
        <div class="order-info-item">
          支付方式：{{ getPayType(orderInfo.type) }}
        </div>
        <div class="order-info-item">支付时间：{{ orderInfo.paymentTime }}</div>
      </div>
      <el-table :data="[orderItemInfo]" style="width: 100%">
        <el-table-column
          prop="goodsName"
          label="商品名称"
          align="center"
          width="180"
        ></el-table-column>
        <el-table-column
          prop="price"
          label="单价（元）"
          align="center"
          width="180"
        ></el-table-column>
        <el-table-column
          prop="quantity"
          label="数量"
          align="center"
          width="180"
        ></el-table-column>
        <el-table-column
          prop="totalPrice"
          label="商品总价（元）"
          align="center"
          width="180"
        ></el-table-column>
      </el-table>
      <span class="dialog-footer">
        <el-button @click="canceDetail()">关闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
  
<script setup>
import { useStore } from "vuex";
import { computed, ref, reactive, watch, onMounted } from "vue";
import { ElMessage } from "element-plus";

const store = useStore();

const detailOrderItemDInfoiaVisible = computed(() => store.getters.detailOrderItemDInfoiaVisible);

const orderInfo = computed(() => store.getters.selectedUserOrderInfo);

const addressInfo = computed(() => store.getters.selectedUserAddressInfo);

const orderItemInfo = computed(() => store.getters.selectedUserOrderItemInfo)


const canceDetail = () => {
  store.commit('setDetailOrderItemDInfoiaVisible', false);
  console.log("----------------1----")
  console.log(orderItemInfo.value)
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


</script>
  
<style>
.order-info-item {
  margin-bottom: 10px;
}
.dialog-footer {
  text-align: right;
}
</style>
  
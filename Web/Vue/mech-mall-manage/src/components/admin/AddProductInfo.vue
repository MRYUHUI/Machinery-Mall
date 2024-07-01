<script setup>
import { useStore } from "vuex";
import { computed, ref, reactive, watch } from "vue";
import apiRequests from "@/apis";
import { ElMessage } from "element-plus";

const store = useStore();
const editRef = ref(null) ;

const form = reactive({
  id: '',
	name: '',
	productId: '',
	partsId: '',
	iconUrl: '',
	sunImages: '',
	specParam: '',
	price: '',
	stock: '',
	status: '',
	isHot: '',
	created: '',
  updated: '',
});
const resetForm = () =>{
  form.id = '';
	form.name = '';
	form.productId = '';
	form.partsId = '';
	form.iconUrl = '';
	form.sunImages =  '';
	form.specParam =  '';
	form.price =  '';
	form.stock =  '';
	form.status =  '';
	form.isHot =  '';
	form.created =  '';
  form.updated = '';
};
const editRule = {
  name: [{ required: true, message: '产品名不能为空', trigger: 'blur' }],
  productID: [{ required: true, message: '产品类型编号不能为空', trigger: 'blur' }],
  price: [{ required: true, message: '价格不能为空', trigger: 'blur' }],
  stock: [{ required: true, message: '库存不能为空', trigger: 'blur' }],
};
const addProductInfoDiaVisible = computed(() => store.getters.addProductInfoDiaVisible);
const dialogWidth = '500px';

const onSubmit = () => {
  editRef.value.validate(async (valid) => {
    if (valid) {
      const res = await apiRequests.saveProduct(form)
      if (res.success) {
        const updatedProductInfo = res.data
        // 通知管理用户界面刷新
        store.commit('setIsAdminProductFresh', !store.getters.isAdminProductFresh)
        ElMessage.success(res.message)
        resetForm()
        store.commit('setAddProductInfoDiaVisible', false);
      }
      else {
        ElMessage.error(res.message)
      }
    }
  })

  store.commit('setAddProductInfoDiaVisible', false);
};

const cancelEidt = () => {
  resetForm()
  store.commit('setAddProductInfoDiaVisible', false);
};

</script>

<template>
 <!-- 对话框 -->
 <el-dialog
    v-model="addProductInfoDiaVisible"
    title="添加产品信息"
    :close-on-click-modal="false"
    :show-close="false"
    :width="dialogWidth"
  >
    <!-- 编辑表单 -->
    <el-form
      :model="form"
      label-width="80px"
      :rules="editRule"
      ref="editRef"
      class="edit-form"
    >
      <!-- 名称 -->
      <el-form-item label="商品名称" prop="name">
        <el-input v-model="form.name" />
      </el-form-item>
      <!-- 产品类型 -->
      <el-form-item label="产品类型" prop="productId">
        <el-input v-model="form.productId" />
      </el-form-item>
      <!-- 价格 -->
      <el-form-item label="商品价格" prop="price">
        <el-input v-model="form.price" />
      </el-form-item>
      <!-- 库存 -->
      <el-form-item label="商品库存" prop="stock">
        <el-input v-model="form.stock" />
      </el-form-item>
      <!--图片-->

      <!-- 详情 -->
      <el-form-item label="商品详情" prop="detail">
        <el-input v-model="form.detail" />
      </el-form-item>
    </el-form>
    <!-- 修改按钮 -->
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
<template>
  <v-card style="height: 220px;" color="#BF6211">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">生成订单</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      请填写资料
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#402c1a"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="insert"
      >
        <v-icon>mdi-account-plus</v-icon>
        生成订单
      </v-btn>
    </v-card-text>
  </v-card>
  <v-card class="mt-6">
    <v-form
        v-model="valid"
        lazy-validation
        class="ma-6"
    >
      <v-text-field
          v-model="address"
          label="Address"
          required
      ></v-text-field>
    </v-form>
  </v-card>
</template>

<script>
import {reactive, ref, toRefs} from "vue";
import {insertOrder} from "../../../network/order";

export default {
  name: "insertOrder",
  setup() {
    let form = reactive({
      address: "",
      user_id: window.localStorage.getItem("UserId"),
    })
    let valid = true
    const insert = () => {
      insertOrder(form).then(res=>{
        console.log(res.data.status);
      })
    }
    return {
      ...toRefs(form),
      valid,
      insert,
    }
  }
}
</script>

<style scoped>

</style>
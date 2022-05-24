<template>
  <v-card style="height: 200px;" color="#BF6211">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">商品详情</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      要是合适就买下来吧
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#402c1a"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="validate"
      >
        <v-icon>mdi-account-plus</v-icon>
        加入购物车
      </v-btn>
    </v-card-text>
  </v-card>

  <v-card class="mt-2">
    <div style="width: 30%; display: inline;">
      <v-avatar
          class="ma-3"
          size="500"
          rounded="0"
      >
        <v-img :src="'http://1.12.253.71:8081/queryGoodImg/'+id"></v-img>
      </v-avatar>
    </div>
    <div style="width: 70%; display: inline;">
      <v-card-title class="text-h4 font-weight-black mt-4">
            商品名称：{{ list.name }}
      </v-card-title>
      <v-card-text class="text-h5 font-weight-black">
        <p class="mt-6">商品种类：{{ list.item }}</p>
        <p class="mt-6">商品简介：{{ list.intro }}</p>
        <p class="mt-6">商品价格：${{ list.price }}</p>
        <p class="mt-6">商品库存：{{ list.count }}</p>
      </v-card-text>
    </div>

  </v-card>






</template>

<script>
import {useRoute, useRouter} from "vue-router";
import {getGoodByIdFroUser, updateCountOfBroweringGood} from "../../../network/good";
import {onBeforeUnmount, onMounted, ref} from "vue";
import {userDealwithCart} from "../../../network/cart";

export default {
  name: "GoodDetail",
  setup() {
    let browseTime = 0;
    let clearTimeSet = null;
    const route = useRoute();
    const id = route.query.id
    let list = ref([])
    const form = {
      good_id: "",
      user_id: window.localStorage.getItem("UserId"),
      count: 1,
      selected: 1,
    }
    const init = () => {
      getGoodByIdFroUser(id).then(res=>{
        list.value = res.data
        form.good_id = res.data.id
        console.log(list.value);
      })
      updateCountOfBroweringGood(id).then(res=>{
        //console.log(res);
      })
    }
    let setTime = () => {
      clearTimeSet = setInterval(()=>{
        browseTime++
      },1000)
    }
    onMounted(()=>{
      init()
      setTime()
    })
    onBeforeUnmount(()=>{
      clearInterval(clearTimeSet)
      console.log(browseTime);
    })
    const validate = () => {
      console.log(form);
      userDealwithCart(form).then(res=>{
        console.log(res.data.status);
      })
    }

    return {
      id,
      list,
      validate,
    }
  }
}
</script>

<style scoped>

</style>
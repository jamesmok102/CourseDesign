<template>
  <v-card style="height: 200px;" color="#BF6211">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">商品推薦</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      希望有你喜欢的商品
    </v-card-subtitle>
    <v-card-text>

    </v-card-text>
  </v-card>

  <div class="wrapper">
    <div class="wrapper-content" v-for="(item, index) in list">
      <v-card
          class="mx-auto ma-2 mt-4"
          max-width="344"
          height="350px"
      >
        <v-img
            :src="'http://1.12.253.71:8081/queryGoodImg/'+item.id"
            height="200px"
            cover
        ></v-img>

        <v-card-title>
          {{item.name}}
        </v-card-title>

        <v-card-subtitle>
          {{ item.item }} ${{item.price}}
        </v-card-subtitle>

        <v-btn
            color="#f28f16"
            class="font-weight-black text-white mt-2 ml-4"
            plain
            @click="toDetail(item.id)"
        >
          <v-icon>mdi-briefcase-search</v-icon>
          查看详情
        </v-btn>

      </v-card>
    </div>
  </div>





</template>

<script>
import {getRandomGood} from "../../../network/good";
import {onMounted, reactive, ref} from "vue";
import {useRouter} from "vue-router";

export default {
  name: "ProductsFeatured",
  setup() {

    let list = ref([]);
    let router = useRouter();

    const getGood = () => {
      getRandomGood(6).then(res=>{
        list.value = res.data
      })
    }

    onMounted(()=>{
      getGood()
    })

    const toDetail = (id) => {
      router.push({path: "gooddetail", query: {id: id}})
    }


    return {
      list,
      toDetail,
    }
  }
}
</script>

<style scoped>
.wrapper{
  width: 50%;
  height: auto;
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  flex-wrap: wrap;
}
.wrapper-content{
  width: 33%;
}
</style>
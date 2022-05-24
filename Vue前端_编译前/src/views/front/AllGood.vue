<template>
  <v-card style="height: 200px;" color="#BF6211">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">所有商品</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      这里列出了所有的商品
    </v-card-subtitle>
    <v-card-text>

    </v-card-text>
  </v-card>

  <div class="wrapper">
    <div class="wrapper-content" v-for="(item, index) in desserts">
      <v-card
          class="mx-auto ma-2 mt-4"
          max-width="344"
          height="350px"
      >
<!--        <v-img-->
<!--            :src="'http://localhost:8081/queryGoodImg/'+item.id"-->
<!--            height="200px"-->
<!--            cover-->
<!--        ></v-img>-->
        <div style="height: 200px;">
          <img :src="'http://1.12.253.71:8081/queryGoodImg/'+item.id" style="height: 100%;width: 100%;object-fit:cover;">
        </div>


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

  <div class="float-right mt-2">
    <v-pagination
        v-model="page"
        :length="pageLength"
        total-visible="10"
        rounded="circle"

    ></v-pagination>
  </div>

</template>

<script>
import {useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import {getGoodAllForUser} from "../../../network/good";

export default {
  name: "AllGood",
  setup() {
    let router = useRouter();
    let page = ref(1);
    let desserts = ref([]);
    let pageLength = ref(1);
    let pageSize = ref(1);
    let pageTotal = ref(1);

    onMounted(()=>{
      init()
    })

    const init = () => {
      getGoodAllForUser(page.value).then(res=>{
        desserts.value = res.data.data;
        pageLength.value = res.data.PageLength;
        pageSize.value = res.data.PageSize;
        pageTotal.value = res.data.PageTotal;
        console.log(desserts.value);
      })
    }

    const toDetail = (id) => {
      router.push({path: "gooddetail", query: {id: id}})
    }

    watch(page, async(newPage, oldPage) => {
      init()
    })

    return {
      desserts,
      page,
      pageLength,
      pageSize,
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
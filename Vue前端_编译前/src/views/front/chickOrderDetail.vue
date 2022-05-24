<template>
  <v-card style="height: 200px;" color="#BF6211">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">你的订单详情</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      你的订单详情
    </v-card-subtitle>
    <v-card-text>

    </v-card-text>
  </v-card>

  <v-card class="mt-6">
    <v-table>
      <template v-slot:default>
        <thead style="background-color: #733410">
        <tr>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            用户
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            商品
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            数量
          </th>
        </tr>
        </thead>
        <tbody>
        <tr
            v-for="n in desserts"
            :key="n.id"
        >
          <td class="font-weight-medium">{{ n.username }}</td>
          <td class="font-weight-medium">{{ n.goodname }}</td>
          <td class="font-weight-medium">{{ n.count }}</td>
        </tr>
        </tbody>
      </template>
    </v-table>
  </v-card>
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
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import {getOrderDetailByOrderIdForUser} from "../../../network/orderDetail";

export default {
  name: "chickOrderDetail",
  setup() {
    let router = useRouter();
    let route = useRoute();
    let page = ref(1);
    let desserts = ref([]);
    let pageLength = ref(1);
    let pageSize = ref(1);
    let pageTotal = ref(1);

    onMounted(()=>{
      init()
    })

    const init = () => {
      getOrderDetailByOrderIdForUser(route.query.id, page.value).then(res=>{
        desserts.value = res.data.data;
        pageLength.value = res.data.PageLength;
        pageSize.value = res.data.PageSize;
        pageTotal.value = res.data.PageTotal;
      })
    }

    watch(page, async(newPage, oldPage) => {
      init()
    })

    return {
      desserts,
      page,
      pageLength,
      pageSize,
    }

  }
}
</script>

<style scoped>

</style>
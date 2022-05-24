<template>
  <v-card style="height: 200px;" color="#1F8A70">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">销售异常监测</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      这里监测商品的异常情况，当异常情况排除后，这里就会看不见刚才的记录
    </v-card-subtitle>
    <v-card-text>

    </v-card-text>
  </v-card>

  <v-card class="mt-6">
    <v-table>
      <template v-slot:default>
        <thead style="background-color: #1F8A70">
        <tr>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            商品
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            分类
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            浏覧次数
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            库存
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            销售员
          </th>
        </tr>
        </thead>
        <tbody>
        <tr
            v-for="item in desserts"
            :key="item.id"
        >
          <td class="font-weight-medium">{{ item.name }}</td>
          <td class="font-weight-medium">{{item.item}}</td>
          <td class="font-weight-medium">{{item.browse}}</td>
          <td class="font-weight-medium">{{item.count}}</td>
          <td class="font-weight-medium">{{item.salername}}</td>
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
import {getGoodAllCount0} from "../../../../network/good";

export default {
  name: "adminCheckGoodException",
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
      getGoodAllCount0(page.value).then(res=>{
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
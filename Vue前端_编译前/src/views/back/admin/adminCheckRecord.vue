<template>
  <v-card style="height: 200px;" color="#1F8A70">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">操作记录查询</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      管理员、销售人员、用户的操作记录都在这里
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
            帐户
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            IP
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            时间
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            操作内容
          </th>
        </tr>
        </thead>
        <tbody>
        <tr
            v-for="item in desserts"
            :key="item.id"
        >
          <td class="font-weight-medium">{{ item.name }}</td>
          <td class="font-weight-medium">{{item.ip}}</td>
          <td class="font-weight-medium">{{item.time}}</td>
          <td class="font-weight-medium">{{item.content}}</td>
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
import {getRecordAll} from "../../../../network/record";

export default {
  name: "adminCheckRecord",
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
      getRecordAll(page.value).then(res=>{
        desserts.value = res.data.data;
        pageLength.value = res.data.PageLength;
        pageSize.value = res.data.PageSize;
        pageTotal.value = res.data.PageTotal;
        console.log(res.data);
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
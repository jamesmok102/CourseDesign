<template>
  <v-card style="height: 200px;" color="#1F8A70">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">所有商品查询</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      所有销售人员的商品都能查
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
            名称
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            类别
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            价格
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            库存
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            浏览次数
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            销售人员
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            操作
          </th>
        </tr>
        </thead>
        <tbody>
        <tr
            v-for="n in desserts"
            :key="n.id"
        >
          <td class="font-weight-medium">{{ n.name }}</td>
          <td class="font-weight-medium">{{ n.item }}</td>
          <td class="font-weight-medium">{{ n.price }}</td>
          <td class="font-weight-medium">{{ n.count }}</td>
          <td class="font-weight-medium">{{ n.browse }}</td>
          <td class="font-weight-medium">{{ n.salername }}</td>
          <td>
            <v-btn
                color="#FD7400"
                class="font-weight-black text-white"
                plain
                @click="checkGood(n.id)"
            >
              <v-icon>mdi-file-document-edit</v-icon>
              业绩
            </v-btn>

          </td>
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
import {useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import {getGoodAllForAdmin} from "../../../../network/good";

export default {
  name: "adminManageGood",
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
      getGoodAllForAdmin(page.value).then(res=>{
        desserts.value = res.data.data;
        pageLength.value = res.data.PageLength;
        pageSize.value = res.data.PageSize;
        pageTotal.value = res.data.PageTotal;
      })
    }

    watch(page, async(newPage, oldPage) => {
      init()
    })

    const checkGood = (id) => {
      router.push({path: "admincheckgoodorderdetail", query: {id: id}})
    }

    return {
      desserts,
      page,
      pageLength,
      pageSize,
      checkGood,
    }

  }
}
</script>

<style scoped>

</style>
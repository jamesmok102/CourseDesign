<template>
  <v-card style="height: 200px;" color="#4480A6">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">商品查询</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      在此可以查询商品，想看商品的图片请到编辑介面，当然，只能看到当前销售人员负责的商品
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#263F73"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="batchDeleteGood"
      >
        <v-icon>mdi-file-remove</v-icon>
        批量删除
      </v-btn>
    </v-card-text>
  </v-card>
  <v-card class="mt-6">
    <v-table>
      <template v-slot:default>
        <thead style="background-color: #4480A6">
        <tr>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            <v-checkbox hide-details @change="changeFullSelect" v-model="fullSelected"></v-checkbox>
          </th>
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
          <td class="font-weight-medium"><v-checkbox hide-details :value="n.id" v-model="selected"></v-checkbox></td>
          <td class="font-weight-medium">{{ n.name }}</td>
          <td class="font-weight-medium">{{ n.item }}</td>
          <td class="font-weight-medium">{{ n.price }}</td>
          <td class="font-weight-medium">{{ n.count }}</td>
          <td class="font-weight-medium">{{ n.browse }}</td>
          <td class="font-weight-medium">{{ n.salername }}</td>
          <td>
            <v-btn
                color="#73BCD9"
                class="font-weight-black text-white"
                plain
                @click="editGood(n.id)"
            >
              <v-icon>mdi-file-document-edit</v-icon>
              编辑
            </v-btn>
            &nbsp
            <v-btn
                color="#73BCD9"
                plain
                class="font-weight-black text-white"
                @click="deleteGood(n.id)"
            >
              <v-icon>mdi-file-remove</v-icon>
              删除
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
  <v-snackbar v-model="deleteSuccess">删除成功</v-snackbar>
  <v-snackbar v-model="deleteFailed">删除失败</v-snackbar>
</template>

<script>
import {useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import {betchDeleteGoodById, deleteGoodById, getGoodAll, getGoodBySaler} from "../../../../network/good";
import {betchDeleteCategoryById, deleteCategoryById, getCategoryNoPage} from "../../../../network/category";
import store from "@/store";

export default {
  name: "salerManageGood",
  setup() {
    let router = useRouter();
    let page = ref(1);
    let desserts = ref([]);
    let pageLength = ref(1);
    let pageSize = ref(1);
    let pageTotal = ref(1);
    let selected = ref([]);
    let fullSelected = ref(false);
    let deleteSuccess = ref(false);
    let deleteFailed = ref(false);

    onMounted(()=>{
      init()
    })

    const init = () => {
      // getGoodAll(page.value).then(res=>{
      //   desserts.value = res.data.data;
      //   pageLength.value = res.data.PageLength;
      //   pageSize.value = res.data.PageSize;
      //   pageTotal.value = res.data.PageTotal;
      // })
      getGoodBySaler(store.state.saler.id, page.value).then(res=>{
        desserts.value = res.data.data;
        pageLength.value = res.data.PageLength;
        pageSize.value = res.data.PageSize;
        pageTotal.value = res.data.PageTotal;
      })
    }

    const changeFullSelect = () => {
      if (fullSelected.value == true) {
        selected.value = desserts.value.map(item=>item.id)
      } else {
        selected.value = [];
      }
    }

    watch(page, async(newPage, oldPage) => {
      init()
    })

    const deleteGood = (id) => {
      deleteGoodById(id).then(res=>{
        let status = res.data.status;
        if (status > 0) {
          deleteSuccess.value = true;
          setTimeout(()=>{
            deleteSuccess.value = false;
          }, 1000)
        } else {
          deleteFailed.value = true;
          setTimeout(()=>{
            deleteFailed.value = false;
          }, 1000)
        }
        init()
      })
    }

    const batchDeleteGood = () => {
      betchDeleteGoodById(selected.value).then(res=>{
        let status = res.data.status;
        if (status > 0) {
          deleteSuccess.value = true;
          setTimeout(()=>{
            deleteSuccess.value = false;
          }, 1000)
        } else {
          deleteFailed.value = true;
          setTimeout(()=>{
            deleteFailed.value = false;
          }, 1000)
        }
        init()
      })
    }

    const editGood = (id) => {
      router.push({path: "salereditgood", query: {id: id}})
    }

    return {
      desserts,
      page,
      pageLength,
      selected,
      changeFullSelect,
      fullSelected,
      pageSize,
      deleteSuccess,
      deleteFailed,
      deleteGood,
      batchDeleteGood,
      editGood,
    }

  }
}
</script>

<style scoped>

</style>
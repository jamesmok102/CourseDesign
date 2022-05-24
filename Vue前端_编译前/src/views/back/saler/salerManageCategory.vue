<template>
  <v-card style="height: 200px;" color="#4480A6">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">商品分类查询</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      就查询商品分类啊...你觉得还有别的？？？
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#263F73"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="batchDeleteCategory"
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
            类别
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
          <td class="font-weight-medium">{{ n.item }}</td>
          <td>
            <v-btn
                color="#73BCD9"
                plain
                class="font-weight-black text-white"
                @click="deleteCategory(n.id)"
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
import {getCategory} from "../../../../network/category";
import {deleteCategoryById, betchDeleteCategoryById} from "../../../../network/category";

export default {
  name: "salerManageCategory",
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
      getCategory(page.value).then(res=>{
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

    const deleteCategory = (id) => {
      deleteCategoryById(id).then(res=>{
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

    const batchDeleteCategory = () => {
      betchDeleteCategoryById(selected.value).then(res=>{
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
      deleteCategory,
      batchDeleteCategory,
    }

  }
}
</script>

<style scoped>

</style>
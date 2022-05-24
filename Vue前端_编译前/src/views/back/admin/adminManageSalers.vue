<template>
  <v-card style="height: 200px;" color="#1F8A70">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">销售人员查询</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      在此可以查询销售人员的ID、姓名、电子邮箱、手机号码等信息，同时管理员也可以对销售人员的信息进行编辑和删除操作
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#004358"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="batchDeleteSaler"
      >
        <v-icon>mdi-file-remove</v-icon>
        批量删除
      </v-btn>
    </v-card-text>
  </v-card>
  <v-card class="mt-6">
  <v-table>
    <template v-slot:default>
      <thead style="background-color: #1F8A70">
      <tr>
        <th class="text-left text-subtitle-1 font-weight-black text-white">
          <v-checkbox hide-details @change="changeFullSelect" v-model="fullSelected"></v-checkbox>
        </th>
        <th class="text-left text-subtitle-1 font-weight-black text-white">
          姓名
        </th>
        <th class="text-left text-subtitle-1 font-weight-black text-white">
          电子邮箱
        </th>
        <th class="text-left text-subtitle-1 font-weight-black text-white">
          手机号码
        </th>
        <th class="text-left text-subtitle-1 font-weight-black text-white">
          操作
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="item in desserts"
          :key="item.id"
      >
        <td class="font-weight-medium"><v-checkbox hide-details :value="item.id" v-model="selected"></v-checkbox></td>
        <td class="font-weight-medium">{{ item.name }}</td>
        <td class="font-weight-medium">{{item.email}}</td>
        <td class="font-weight-medium">{{item.phone}}</td>
        <td>
          <v-btn
              color="#FD7400"
              class="font-weight-black text-white"
              plain
              @click="checkSaler(item.id)"
          >
            <v-icon>mdi-file-document-edit</v-icon>
            业绩
          </v-btn>
          &nbsp
          <v-btn
              color="#FD7400"
              class="font-weight-black text-white"
              plain
              @click="editSaler(item.id)"
          >
            <v-icon>mdi-file-document-edit</v-icon>
            编辑
          </v-btn>
          &nbsp
          <v-btn
              color="#FD7400"
              plain
              class="font-weight-black text-white"
              @click="deleteSaler(item.id)"
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
import {onMounted, ref, watch} from "vue";
import {deleteSalersById, getSalers, betchDeleteSalersById} from "../../../../network/saler";
import {useRouter} from "vue-router";

export default {
  name: "adminManageSalers",
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
      getSalers(page.value).then(res=>{
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

    const deleteSaler = (id) => {
      deleteSalersById(id).then(res=>{
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

    const batchDeleteSaler = () => {
      betchDeleteSalersById(selected.value).then(res=>{
        console.log(res);
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

    const editSaler = (id) => {
      router.push({path: "admineditSalers", query: {id: id}})
    }

    const checkSaler = (id) => {
      router.push({path: "admincheckorderdetail", query: {id: id}})
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
      deleteSaler,
      batchDeleteSaler,
      editSaler,
      checkSaler,
    }
  }
}
</script>

<style scoped>

</style>
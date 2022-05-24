<template>
  <v-card style="height: 220px;" color="#BF6211">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">你的购物车</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      购物车的商品数量是实时更新的，请放心的按下去<br>
      数量减到1时并不会自动删除，怕你不小心按错了<br>
      CheckBox也是实时更新的，选好就提交订单吧
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#402c1a"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="batchDeleteCart"
      >
        <v-icon>mdi-account-plus</v-icon>
        批量删除
      </v-btn>
      <v-btn
          color="#402c1a"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="goToCreateOrder"
      >
        <v-icon>mdi-account-plus</v-icon>
        生成订单
      </v-btn>
    </v-card-text>
  </v-card>
  <v-card class="mt-6">
    <v-table>
      <template v-slot:default>
        <thead style="background-color: #733410">
        <tr>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            <v-checkbox hide-details @change="changeFullSelect" v-model="fullSelected"></v-checkbox>
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            商品
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            数量
          </th>
          <th class="text-left text-subtitle-1 font-weight-black text-white">
            用户
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
          <td class="font-weight-medium">{{ n.good }}</td>
          <td class="font-weight-medium">
            <v-btn color="#f28f16" class="font-weight-black text-white" density="compact"
                   @click="subCount(n.id, n.count, n.good_id, n.user_id)">
              -
            </v-btn>
            {{ n.count }}
            <v-btn color="#f28f16" class="font-weight-black text-white" density="compact"
                   @click="addCount(n.id, n.count, n.good_id, n.user_id)">
              +
            </v-btn>
          </td>
          <td class="font-weight-medium">{{ n.user }}</td>
          <td>
            <v-btn
                color="#f28f16"
                plain
                class="font-weight-black text-white"
                @click="deleteCart(n.id)"
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
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref, watch} from "vue";
import {
  betchDeleteCartByIdForUser, changeSelected,
  deleteCartByIdForUser,
  getCartByUserIdAllForUser,
  userDealwithCart
} from "../../../network/cart";

export default {
  name: "Cart",
  setup() {
    let router = useRouter();
    let route = useRoute();
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
      getCartByUserIdAllForUser(window.localStorage.getItem("UserId"), page.value).then(res=>{
        desserts.value = res.data.data;
        pageLength.value = res.data.PageLength;
        pageSize.value = res.data.PageSize;
        pageTotal.value = res.data.PageTotal;
        res.data.data.forEach((value)=>{
          if (value.selected == 1) {
            selected.value.push(value.id)
          }
        })
      })
    }

    const changeFullSelect = () => {
      if (fullSelected.value == true) {
        selected.value = desserts.value.map(item=>item.id)
      } else {
        selected.value = [];
      }
    }

    const subCount = (id, count, good_id, user_id) => {
      let form = {
        id,
        count: count - 1,
        good_id,
        user_id,
      }
      userDealwithCart(form).then(res=>{
        console.log(res.data);
        init()
      })

    }

    const addCount = (id, count, good_id, user_id) => {
      let form = {
        id,
        count: count + 1,
        good_id,
        user_id,
      }
      console.log(form);
      userDealwithCart(form).then(res=>{
        console.log(res.data);
        init()
      })
    }

    watch(page, async(newPage, oldPage) => {
      init()
    })

    watch(selected, async(newSelected, oldSelected) => {
      //console.log("oldSelected:"+oldSelected);
      //console.log("newSelected:"+newSelected);
      changeSelected(selected.value, window.localStorage.getItem("UserId")).then(res=>{
        init()
      })
    })

    const deleteCart = (id) => {
      deleteCartByIdForUser(id).then(res=>{
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

    const batchDeleteCart = () => {
      betchDeleteCartByIdForUser(selected.value).then(res=>{
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

    const goToCreateOrder = () => {
      router.push({path: "insertorder"})
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
      deleteCart,
      batchDeleteCart,
      subCount,
      addCount,
      goToCreateOrder,
    }

  }
}
</script>

<style scoped>

</style>
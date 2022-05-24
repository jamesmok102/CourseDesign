<template>
  <v-app>
    <v-navigation-drawer app v-model="drawer">

      <template v-slot:prepend>
        <v-list-item
            two-line
            prepend-avatar="https://randomuser.me/api/portraits/women/81.jpg"
            title="Admin"
            subtitle="已登入"
            style="padding-bottom: 16px;"
        ></v-list-item>
      </template>

      <v-divider></v-divider>

      <v-list v-model:opened="Main" nav density="compact">
        <v-list-group>
          <template v-slot:activator="{ props }">
            <v-list-item v-bind="props" title="主页" value="Main" prepend-icon="mdi-home"></v-list-item>
          </template>

          <v-list-item
              v-for="([title, icon], i) in mainpage"
              :key="i"
              :value="title"
              :title="title"
              :prepend-icon="icon"
          ></v-list-item>
        </v-list-group>
      </v-list>

      <v-list v-model:opened="Category" nav density="compact">
        <v-list-group>

          <template v-slot:activator="{ props }">
            <v-list-item v-bind="props" title="商品分类" value="Category" prepend-icon="mdi-bookmark-multiple"></v-list-item>
          </template>
          <v-list-item
              title="分类查询"
              value="分类查询"
              prepend-icon="mdi-bookmark-check"
              to="/salermanagecategory">
          </v-list-item>
          <v-list-item
              title="分类增加"
              value="分类增加"
              prepend-icon="mdi-bookmark-plus"
              to="/salerinsertcategory">
          </v-list-item>
        </v-list-group>
      </v-list>

      <v-list v-model:opened="Good" nav density="compact">
        <v-list-group>

          <template v-slot:activator="{ props }">
            <v-list-item v-bind="props" title="商品" value="Good" prepend-icon="mdi-basket"></v-list-item>
          </template>
          <v-list-item
              title="商品查询"
              value="商品查询"
              prepend-icon="mdi-basket-outline"
              to="/salermanagegood">
          </v-list-item>
          <v-list-item
              title="商品增加"
              value="商品增加"
              prepend-icon="mdi-basket-plus"
              to="/salerinsertgood">
          </v-list-item>
        </v-list-group>
      </v-list>

      <v-list v-model:opened="user" nav density="compact">
        <v-list-group>

          <template v-slot:activator="{ props }">
            <v-list-item v-bind="props" title="用户" value="user" prepend-icon="mdi-basket"></v-list-item>
          </template>
          <v-list-item
              title="用户查询"
              value="用户查询"
              prepend-icon="mdi-basket-outline"
              to="/salermanageuser">
          </v-list-item>
          <v-list-item
              title="用户增加"
              value="用户增加"
              prepend-icon="mdi-basket-plus"
              to="/salerinsertuser">
          </v-list-item>
        </v-list-group>
      </v-list>

    </v-navigation-drawer>

    <v-app-bar app color="#6456BF">

      <template v-slot:prepend>
        <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer" class="text-white"></v-app-bar-nav-icon>
      </template>

      <v-app-bar-title class="text-white font-weight-black">管理后台</v-app-bar-title>

      <v-btn icon>
        <v-icon class="text-white" @click="logout">mdi-exit-run</v-icon>
      </v-btn>

    </v-app-bar>

    <!-- Sizes your content based upon application components -->
    <v-main style="height: 100vh;">

      <!-- Provides the application the proper gutter -->
      <v-container fluid>

        <!-- If using vue-router -->
        <router-view></router-view>
      </v-container>
    </v-main>

    <v-footer app>
      <!-- -->
    </v-footer>
  </v-app>
</template>

<script>
import {reactive, ref} from "vue";
import {useRouter} from "vue-router";
import store from "@/store";

import {insertLogRecord} from "../../../../network/logRecord";
import {getSalerById_forSaler} from "../../../../network/saler";

export default {
  name: "salerMain",
  setup() {
    let drawer = ref(false);
    let mainpage = reactive([
      ['主页', 'mdi-home-search'],
    ])
    const router = useRouter();
    let title = ref("Saler");

    const logout = () => {

      let form = {
        state: "登出",
        role: "销售员",
        name: window.localStorage.getItem("SalerName")
      }

      insertLogRecord(form).then(res=>{

      })

      window.localStorage.setItem('JWT_Saler_Token', '');
      store.commit('setSalerIsLogin', false);
      store.commit('setSalerId', "");
      router.push({path:'/salerlogin'})
    }

    // getSalerById_forSaler(store.state.saler.id).then(res=>{
    //   title.value = res.data.name
    // })

    return {
      drawer,
      mainpage,
      router,
      logout,
      title,
    }
  }
}
</script>

<style scoped>

</style>
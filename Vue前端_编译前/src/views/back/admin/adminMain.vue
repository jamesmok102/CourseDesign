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

      <v-list v-model:opened="User" nav density="compact">
        <v-list-group>
          
          <template v-slot:activator="{ props }">
            <v-list-item v-bind="props" title="销售人员" value="User" prepend-icon="mdi-account-circle"></v-list-item>
          </template>
          <v-list-item
              title="销售人员查询"
              value="销售人员查询"
              prepend-icon="mdi-account-search"
              to="/adminmanageSalers">
          </v-list-item>
          <v-list-item
              title="增加销售人员"
              value="增加销售人员"
              prepend-icon="mdi-account-plus"
              to="/adminInsertSalers">
          </v-list-item>
        </v-list-group>
      </v-list>

      <v-list v-model:opened="Good" nav density="compact">
        <v-list-group>

          <template v-slot:activator="{ props }">
            <v-list-item v-bind="props" title="商品" value="Good" prepend-icon="mdi-account-circle"></v-list-item>
          </template>
          <v-list-item
              title="商品查询"
              value="商品查询"
              prepend-icon="mdi-account-search"
              to="/adminmanagegood">
          </v-list-item>
        </v-list-group>
      </v-list>

      <v-list v-model:opened="Log" nav density="compact">
        <v-list-group>

          <template v-slot:activator="{ props }">
            <v-list-item v-bind="props" title="记录与监测" value="Log" prepend-icon="mdi-account-circle"></v-list-item>
          </template>
          <v-list-item
              title="登入登出记录查询"
              value="登入登出记录查询"
              prepend-icon="mdi-account-search"
              to="/adminchecklogrecord">
          </v-list-item>
          <v-list-item
              title="操作记录查询"
              value="操作记录查询"
              prepend-icon="mdi-account-search"
              to="/admincheckrecord">
          </v-list-item>
          <v-list-item
              title="销售异常监测"
              value="销售异常监测"
              prepend-icon="mdi-account-search"
              to="/admincheckgoodexception">
          </v-list-item>
          <v-list-item
              title="用户画像"
              value="用户画像"
              prepend-icon="mdi-account-search"
              to="/admincheckuserportrait">
          </v-list-item>
          <v-list-item
              title="分类出售情况"
              value="分类出售情况"
              prepend-icon="mdi-account-search"
              to="/admincheckcategoryorder">
          </v-list-item>
          <v-list-item
              title="商品出售情况"
              value="商品出售情况"
              prepend-icon="mdi-account-search"
              to="/admincheckgoodsell">
          </v-list-item>
        </v-list-group>
      </v-list>

    </v-navigation-drawer>

    <v-app-bar app color="#004358">

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
import store from "@/store";
import {useRouter} from "vue-router";
import {insertLogRecord} from "../../../../network/logRecord";

export default {
  name: "adminMain",
  setup() {
    let drawer = ref(false);
    let mainpage = reactive([
      ['主页', 'mdi-home-search'],
    ])
    const router = useRouter();
    const logout = () => {
      window.localStorage.setItem('JWT_Token', '');
      store.commit('setAdminIsLogin', false);

      let form = {
        name: "admin",
        state: "登出",
        role: "管理员"
      }

      insertLogRecord(form).then(res=>{

      })

      router.push({path:'/adminlogin'})
    }
    return {
      drawer,
      mainpage,
      logout
    }
  }
}
</script>

<style scoped>

</style>
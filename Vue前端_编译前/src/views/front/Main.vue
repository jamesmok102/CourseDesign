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

      <v-list density="compact" nav>
        <v-list-item prepend-icon="mdi-home-city" title="商品推薦" value="home" to="/productsfeatured"></v-list-item>
        <v-list-item prepend-icon="mdi-home-city" title="全部商品" value="allgoods" to="/allgood"></v-list-item>
        <v-list-item prepend-icon="mdi-account" title="注册" value="register" to="/registeruser" ></v-list-item>
        <v-list-item prepend-icon="mdi-account" title="登入" value="login" to="/userlogin" ></v-list-item>
        <v-list-item prepend-icon="mdi-account" title="个人资料" value="profile" to="/profile" ></v-list-item>
        <v-list-item prepend-icon="mdi-account" title="购物车" value="cart" to="/cart" ></v-list-item>
        <v-list-item prepend-icon="mdi-account" title="个人订单" value="checkOrder" to="/checkOrder" ></v-list-item>
      </v-list>

    </v-navigation-drawer>

    <v-app-bar app color="#733410">

      <template v-slot:prepend>
        <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer" class="text-white"></v-app-bar-nav-icon>
      </template>

      <v-app-bar-title class="text-white font-weight-black">迷你商城</v-app-bar-title>

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
import {ref} from "vue";
import store from "@/store";
import {useRouter} from "vue-router";
import {watch} from "vue";
import {insertLogRecord} from "../../../network/logRecord";

export default {
  name: "Main",
  setup() {
    let drawer = ref(false);
    let router = useRouter();
    const logout = () => {

      let form = {
        state: "登出",
        role: "用户",
        name: window.localStorage.getItem("UserName")
      }

      insertLogRecord(form).then(res=>{

      })

      window.localStorage.setItem('JWT_User_Token', '');
      window.localStorage.setItem('UserId', '');
      store.commit('setUserIsLogin', false);
      store.commit('setUserId', "");
      router.push({path:'/userlogin'})
    }

    return {
      drawer,
      logout,

    }
  }
}
</script>

<style scoped>

</style>
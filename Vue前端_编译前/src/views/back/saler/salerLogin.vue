<template>
  <v-app>

    <v-app-bar app color="#6456BF">
      <v-app-bar-title class="text-white font-weight-black">销售人员后台登入</v-app-bar-title>
    </v-app-bar>



    <v-main>
      <v-container fluid>
        <v-row class="justify-center align-center" style="height: 95vh;">
          <v-card width="400" class="">
            <!--            <v-img-->
            <!--                height="200"-->
            <!--                src="https://cdn.pixabay.com/photo/2020/07/12/07/47/bee-5396362_1280.jpg"-->
            <!--                cover-->
            <!--                class="text-white"-->
            <!--            >-->
            <!--            </v-img>-->

            <v-card-title class="font-weight-black mt-2">销售人员登入</v-card-title>

            <v-card-text>
              <v-form
                  ref="form"
                  v-model="valid"
                  lazy-validation
              >
                <v-text-field
                    v-model="name"
                    label="用户名"
                    required
                    class="font-weight-black"
                ></v-text-field>

                <v-text-field
                    v-model="password"
                    label="密码"
                    required
                    type="password"
                    class="font-weight-black"
                ></v-text-field>

                <v-btn
                    color="#6456BF"
                    class="mr-4 font-weight-black text-white"
                    @click="validate"
                >
                  登入
                </v-btn>

              </v-form>
            </v-card-text>
          </v-card>
        </v-row>
      </v-container>
    </v-main>

    <v-footer app>

    </v-footer>

    <v-snackbar v-model="loginFailed">登录失败</v-snackbar>

  </v-app>
</template>

<script>
import {useRouter} from "vue-router";
import {useStore} from "vuex";
import {reactive, toRefs} from "vue";
import {salerLogin} from "../../../../network/salerLogin";
import {ref} from "vue";

export default {
  name: "salerLogin",
  setup() {

    const router = useRouter();
    const store = useStore();
    let loginFailed = ref(false);

    const userinfo = reactive({
      name: '',
      password: ''
    })

    const validate = () => {
      salerLogin(userinfo).then(res=>{
        if (res.data.token != null) {
          window.localStorage.setItem('JWT_Saler_Token', res.data.token);
          store.commit('setSalerId', res.data.id);
          store.commit('setSalerIsLogin', true);
          window.localStorage.setItem("SalerName", userinfo.name);
          router.push("/salermain");
        } else {
          loginFailed.value = true;
          setTimeout(()=>{
            loginFailed.value = false;
          }, 1000)
        }

      })
    }

    return {
      ...toRefs(userinfo),
      validate,
      loginFailed,
    }
  }
}
</script>

<style scoped>

</style>
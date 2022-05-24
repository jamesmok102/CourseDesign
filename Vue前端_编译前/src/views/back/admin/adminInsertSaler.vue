<template>
  <v-card style="height: 200px;" color="#1F8A70">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">销售人员增加</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      在此可以增加销售人员的ID、姓名、电子邮箱、手机号码等信息<br>
      注意：想要增加头像，在增加人员后在编辑介面进行
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#004358"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="validate"
      >
        <v-icon>mdi-account-plus</v-icon>
        增加成员
      </v-btn>
    </v-card-text>
  </v-card>
  <v-card class="mt-6">
    <v-form
        v-model="valid"
        lazy-validation
        class="ma-6"
    >
      <v-text-field
          v-model="name"
          :counter="10"
          :rules="nameRules"
          label="Name"
          required
      ></v-text-field>
      <v-text-field
          v-model="email"
          :rules="emailRules"
          label="E-mail"
          required
      ></v-text-field>
      <v-text-field
          v-model="password"
          :rules="passwordRules"
          label="Password"
          required
      ></v-text-field>
      <v-text-field
          v-model="phone"
          :rules="phoneRules"
          label="Phone"
          required
      ></v-text-field>
      <v-file-input
          label="File input"
          filled
          prepend-icon="mdi-camera"
          show-size
          chips
          id="input"
      ></v-file-input>
    </v-form>
  </v-card>
  <v-snackbar v-model="insertSuccess">增加成功</v-snackbar>
  <v-snackbar v-model="insertFailed">增加失败</v-snackbar>

</template>

<script>

import {onMounted, ref, watch, toRefs, reactive} from "vue";
import {insertSaler} from "../../../../network/saler";

export default {
  name: "adminInsertSaler",
  setup() {

    let insertSuccess = ref(false);
    let insertFailed = ref(false);

    let form = reactive({
      name: "",
      email: "",
      password: "",
      phone: "",
    })

    let valid = true
    let nameRules = [
      v => !!v || 'Name is required',
      v => (v && v.length <= 10) || 'Name must be less than 10 characters',
    ]
    let emailRules = [
      v => !!v || 'E-mail is required',
      v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
    ]
    let passwordRules = [
      v => !!v || 'E-mail is required',
    ]
    let phoneRules = [
      v => !!v || 'Name is required',
      v => (v && v.length !== 11) || 'Name must be less than 10 characters',
    ]

    const validate = () => {
      let formData = new window.FormData();
      formData.append("name", form.name);
      formData.append("email", form.email);
      formData.append("password", form.password);
      formData.append("phone", form.phone);
      formData.append("avatar", document.getElementById("input").files[0]);
      insertSaler(formData).then(res=>{
        let status = res.data.status;
        console.log(res);
        console.log(res.data.status);
        if (status === 1) {
          insertSuccess.value = true;
          setTimeout(()=>{
            insertSuccess.value = false;
          }, 1000)
        } else {
          insertFailed.value = true;
          setTimeout(()=>{
            insertFailed.value = false;
          }, 1000)
        }
        form.name = ""
        form.email = ""
        form.password = ""
        form.phone = ""
      })
    }

    return {
      valid,
      nameRules,
      emailRules,
      passwordRules,
      phoneRules,
      ...toRefs(form),
      validate,
      insertSuccess,
      insertFailed,
    }
  }
}
</script>

<style scoped>

</style>
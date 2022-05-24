<template>
  <v-card style="height: 200px;" color="#4480A6">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">用户增加</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      在此可以增加用户信息<br>
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#263F73"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="validate"
      >
        <v-icon>mdi-account-plus</v-icon>
        增加用户
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
      <v-text-field
          v-model="area"
          label="Area"
          required
      ></v-text-field>
      <v-text-field
          v-model="age"
          label="Age"
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
import {reactive, ref, toRefs} from "vue";
import {insertSaler} from "../../../../network/saler";
import {insertUser} from "../../../../network/user";

export default {
  name: "salerInsertUser",
  setup() {
    let insertSuccess = ref(false);
    let insertFailed = ref(false);

    let form = reactive({
      name: "",
      email: "",
      password: "",
      phone: "",
      area: "",
      age: "",
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
      formData.append("area", form.area)
      formData.append("age", form.age)
      formData.append("avatar", document.getElementById("input").files[0]);
      insertUser(formData).then(res=>{
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
        form.area = ""
        form.age = ""
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
<template>
  <v-card style="height: 200px;" color="#BF6211">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">你的个人资料</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      在这里可更新你的个人资料
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#402c1a"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="validate"
      >
        <v-icon>mdi-account-plus</v-icon>
        更新资料
      </v-btn>
    </v-card-text>
  </v-card>

  <v-card class="mt-6">
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
    <v-avatar
        size="50px"
    >
      <v-img
          alt="Avatar"
          :src="'http://1.12.253.71:8081/queryUserImg/'+ id"
          id="avatar"
      ></v-img>
    </v-avatar>


  </v-card>
  <v-snackbar v-model="updateSuccess">更新成功</v-snackbar>
  <v-snackbar v-model="updateFailed">更新失败</v-snackbar>
</template>

<script>
import {useRoute} from "vue-router";
import {onMounted, reactive, ref, toRefs} from "vue";
import {
  getUserById,
  getUserByIdForUser,
  updateUser,
  updateUserForUser,
  uploadUserImg,
  uploadUserImgForUser
} from "../../../network/user";
import store from "@/store";

export default {
  name: "Profile",
  setup() {
    let route = useRoute();
    let updateSuccess = ref(false);
    let updateFailed = ref(false);

    let form = reactive({
      id: "",
      name: "",
      email: "",
      password: "",
      phone: "",
      area: "",
      age: "",
    })

    form.id = window.localStorage.getItem("UserId");

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

      updateUserForUser(form).then(res=>{
        let status = res.data.status
        if (status >= 1) {
          if (document.getElementById("input").files[0] == null) {
            updateSuccess.value = true
            setTimeout(()=>{
              updateSuccess.value = false
            },1000)
            return
          }
          let formData = new window.FormData();
          formData.append("fileid", form.id)
          formData.append("file", document.getElementById("input").files[0])
          uploadUserImgForUser(formData).then(res=>{
            let imgStatus = res.data.status;
            if (imgStatus === 1) {
              updateSuccess.value = true
              setTimeout(()=>{
                updateSuccess.value = false
              },1000)
            } else {
              updateFailed.value = true
              setTimeout(()=>{
                updateFailed.value = false
              },1000)
            }
          })
        } else {
          updateFailed.value = true
          setTimeout(()=>{
            updateFailed.value = false
          },1000)
        }
      })

    }

    const init = () => {

      getUserByIdForUser(form.id).then(res=>{
        form.name = res.data.name
        form.email = res.data.email
        form.password = res.data.password
        form.phone = res.data.phone
        form.area = res.data.area
        form.age = res.data.age
      })



    }

    onMounted(()=>{
      init()
    })

    return {
      valid,
      nameRules,
      emailRules,
      passwordRules,
      phoneRules,
      ...toRefs(form),
      validate,
      updateSuccess,
      updateFailed,
      //submitFiles,
    }
  }
}
</script>

<style scoped>

</style>
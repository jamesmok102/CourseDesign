<template>
  <v-card style="height: 200px;" color="#1F8A70">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">销售人员更新</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      在此可以编辑销售人员的各种信息<br>
      注意：上传图片后请刷新网页
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#004358"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="validate"
      >
        <v-icon>mdi-account-plus</v-icon>
        更新成员
      </v-btn>
<!--      <v-btn-->
<!--          color="#004358"-->
<!--          class="font-weight-black text-white mt-2 ml-4"-->
<!--          plain-->
<!--          @click="submitFiles"-->
<!--      >-->
<!--        <v-icon>mdi-account-plus</v-icon>-->
<!--        上传图片-->
<!--      </v-btn>-->
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
      <v-avatar
          size="50px"
      >
        <v-img
            alt="Avatar"
            :src="'http://1.12.253.71:8081/querySalerImg/'+ id"
            id="avatar"
        ></v-img>
      </v-avatar>
    </v-form>


  </v-card>
  <v-snackbar v-model="updateSuccess">更新成功</v-snackbar>
  <v-snackbar v-model="updateFailed">更新失败</v-snackbar>
</template>

<script>

import {onMounted, ref, watch, toRefs, reactive} from "vue";
import {insertSaler, getSalerById, updateSaler, uploadSalerImg, testimg} from "../../../../network/saler";
import {useRoute} from "vue-router";

export default {
  name: "adminEditSaler",
  setup() {

    let route = useRoute();
    let updateSuccess = ref(false);
    let updateFailed = ref(false);

    let img;

    let form = reactive({
      id: "",
      name: "",
      email: "",
      password: "",
      phone: "",
    })

    form.id = route.query.id;

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

    // const submitFiles = () => {
    //   console.log(document.getElementById("input").files[0]);
    //   let formData = new window.FormData();
    //   formData.append("fileid", form.id)
    //   formData.append("file", document.getElementById("input").files[0])
    //   uploadSalerImg(formData).then(res=>{
    //     console.log(res);
    //   })
    // }

    const validate = () => {
      updateSaler(form).then(res=>{
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
          uploadSalerImg(formData).then(res=>{
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
        init()
      })
    }

    const init = () => {
      getSalerById(form.id).then(res=>{
        form.name = res.data.name
        form.email = res.data.email
        form.password = res.data.password
        form.phone = res.data.phone
      })
      img = "http://localhost:8081/updateSalerImg/" + form.id

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
      img,
    }
  }
}
</script>

<style scoped>

</style>
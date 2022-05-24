<template>
  <v-card style="height: 200px;" color="#4480A6">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">商品更新</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      在此可以增加商品信息<br>
      注意：想要增加商品图片，在增加商品后在编辑介面进行
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#263F73"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="validate"
      >
        <v-icon>mdi-account-plus</v-icon>
        更新商品
      </v-btn>
<!--      <v-btn-->
<!--          color="#263F73"-->
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
          v-model="intro"
          label="intro"
          required
      ></v-text-field>
      <v-text-field
          v-model="price"
          label="Price"
          required
      ></v-text-field>
      <v-text-field
          v-model="count"
          label="Count"
          required
      ></v-text-field>
      <v-autocomplete
          v-model="category"
          :items="items"
          dense
          filled
          label="Category"
      ></v-autocomplete>
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
            :src="'http://1.12.253.71:8081/queryGoodImg/'+ id"
            id="avatar"
        ></v-img>
      </v-avatar>
    </v-form>


  </v-card>
  <v-snackbar v-model="updateSuccess">更新成功</v-snackbar>
  <v-snackbar v-model="updateFailed">更新失败</v-snackbar>
</template>

<script>
import {useRoute} from "vue-router";
import {onMounted, reactive, ref, toRefs} from "vue";
import {getSalerById} from "../../../../network/saler";
import {getGoodById, updateGood, uploadGoodImg} from "../../../../network/good";
import {getCategoryNoPage} from "../../../../network/category";

export default {
  name: "salerEditGood",
  setup() {
    let route = useRoute();
    let updateSuccess = ref(false);
    let updateFailed = ref(false);

    let img;

    let itemdict = {} //category的id和item相对应的dict

    let form = reactive({
      id: "",
      name: "",
      category: "",
      intro: "",
      count: "",
      price: "",
    })

    form.id = route.query.id;

    let items = ref([])

    let valid = true
    let nameRules = [
      v => !!v || 'Name is required',
      v => (v && v.length <= 10) || 'Name must be less than 10 characters',
    ]

    const validate = () => {

      let sendform = {
        id: form.id,
        name: form.name,
        category_id: itemdict[form.category],
        intro: form.intro,
        count: form.count,
        price: form.price,
      }

      console.log(sendform);

      updateGood(sendform).then(res=>{
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
          uploadGoodImg(formData).then(res=>{
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
        init()
      })
    }

    const init = () => {

      getGoodById(form.id).then(res=>{
        form.name = res.data.name;
        form.category = res.data.item;
        form.intro = res.data.intro;
        form.price = res.data.price;
        form.count = res.data.count;
      })

      getCategoryNoPage().then(res=>{
        res.data.forEach((v, i)=>{
          items.value.push(v.item)
          itemdict[v.item] = v.id;
        })
      })

      img = "http://localhost:8081/updateSalerImg/" + form.id

    }

    onMounted(()=>{
      init()
    })

    return {
      valid,
      nameRules,
      ...toRefs(form),
      validate,
      updateSuccess,
      updateFailed,
      //submitFiles,
      img,
      items,
    }

  }
}
</script>

<style scoped>

</style>
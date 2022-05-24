<template>
  <v-card style="height: 200px;" color="#4480A6">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">商品增加</v-card-title>
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
        增加商品
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
          v-model="intro"
          label="Intro"
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
          label="Filled"
      ></v-autocomplete>
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
import { getCategoryNoPage } from "../../../../network/category"
import {insertGood} from "../../../../network/good";
import store from "@/store";

export default {
  name: "salerInsertGood",
  setup() {
    let insertSuccess = ref(false);
    let insertFailed = ref(false);
    let items = ref([])
    let form = reactive({
      name: "",
      category: "",
      intro: "",
      price: "",
      count: "",
    })

    let valid = true
    let nameRules = [
      v => !!v || 'Name is required',
      v => (v && v.length <= 10) || 'Name must be less than 10 characters',
    ]

    let itemdict = {} //category的id和item相对应的dict

    const init = () => {
      getCategoryNoPage().then(res=>{
        res.data.forEach((v, i)=>{
          items.value.push(v.item)
          itemdict[v.item] = v.id;
        })
      })
    }

    onMounted(()=>{
      init();
    })

    const validate = () => {
      // let sendForm = reactive({
      //   name: form.name,
      //   category_id: itemdict[form.category],
      //   intro: form.intro,
      // })
      let formData = new window.FormData();
      formData.append("name", form.name);
      formData.append("category_id", itemdict[form.category]);
      formData.append("intro", form.intro);
      formData.append("pic", document.getElementById("input").files[0])
      formData.append("price", form.price)
      formData.append("count", form.count)
      formData.append("saler_id", store.state.saler.id)
      insertGood(formData).then(res=>{
        let status = res.data.status;
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
        form.category = ""
        form.intro = ""
      })
    }
    return {
      insertSuccess,
      insertFailed,
      ...toRefs(form),
      valid,
      nameRules,
      validate,
      items
    }
  }
}
</script>

<style scoped>

</style>
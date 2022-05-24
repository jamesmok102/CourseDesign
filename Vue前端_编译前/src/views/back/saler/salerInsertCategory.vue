<template>
  <v-card style="height: 200px;" color="#4480A6">
    <v-card-title class="text-h4 font-weight-black text-white mt-6 ml-4">商品分类增加</v-card-title>
    <v-card-subtitle class="text-h6 font-weight-black text-white mt-4 ml-4">
      把自已想要的分类写好，再按一下确定，一劳永逸
    </v-card-subtitle>
    <v-card-text>
      <v-btn
          color="#263F73"
          class="font-weight-black text-white mt-2 ml-4"
          plain
          @click="validate"
      >
        <v-icon>mdi-bookmark-plus</v-icon>
        增加分类
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
          v-model="item"
          :counter="10"
          :rules="categoryRules"
          label="Category"
          required
      ></v-text-field>
    </v-form>
  </v-card>
  <v-snackbar v-model="insertSuccess">增加成功</v-snackbar>
  <v-snackbar v-model="insertFailed">增加失败</v-snackbar>
</template>

<script>
import {ref} from "vue";
import {insertCategory} from "../../../../network/category";
import {reactive, toRefs} from "vue";

export default {
  name: "salerInsertCategory",
  setup() {
    let insertSuccess = ref(false);
    let insertFailed = ref(false);
    let valid = true
    let form = reactive({
      item: ""
    })
    let categoryRules = [
      v => !!v || 'Name is required',
      v => (v && v.length <= 10) || 'Name must be less than 10 characters',
    ]
    const validate = () => {
      insertCategory(form).then(res=>{
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
        form.item = ""
      })
    }
    return {
      ...toRefs(form),
      insertFailed,
      insertSuccess,
      categoryRules,
      validate,
      valid,
    }
  }
}
</script>

<style scoped>

</style>
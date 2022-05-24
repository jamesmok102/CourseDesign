import { createStore } from 'vuex'

export default createStore({
  state: {
    admin: {
      isLogin: window.localStorage.getItem('JWT_Token') ? true : false,
    },
    saler: {
      isLogin: window.localStorage.getItem('JWT_Saler_Token') ? true : false,
      id: "",
    },
    user: {
      isLogin: window.localStorage.getItem('JWT_User_Token') ? true : false,
      id: "",
    }
  },
  getters: {
  },
  mutations: {
    setAdminIsLogin(state, payload) {
      state.admin.isLogin = payload;
    },
    setSalerIsLogin(state, payload) {
      state.saler.isLogin = payload;
    },
    setSalerId(state, payload) {
      state.saler.id = payload;
    },
    setUserIsLogin(state, payload) {
      state.user.isLogin = payload;
    },
    setUserId(state, payload) {
      state.user.id = payload;
    }
  },
  actions: {
  },
  modules: {
  }
})

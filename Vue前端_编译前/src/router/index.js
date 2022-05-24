import {createRouter, createWebHashHistory, createWebHistory} from 'vue-router'
import HomeView from '../views/HomeView.vue'
import store from "@/store";

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  },
  {
    path: '/adminlogin',
    name: 'adminLogin',
    component: () => import('../views/back/admin/adminLogin.vue')
  },
  {
    path: '/adminmain',
    name: 'adminMain',
    component: () => import('../views/back/admin/adminMain.vue'),
    children: [
      {
        path: '/adminmanagesalers',
        name: 'adminManageSalers',
        component: () => import('../views/back/admin/adminManageSalers.vue')
      },
      {
        path: '/admininsertsalers',
        name: 'adminInsertSalers',
        component: () => import('../views/back/admin/adminInsertSaler.vue')
      },
      {
        path: '/admineditsalers',
        name: 'adminEditSalers',
        component: () => import('../views/back/admin/adminEditSaler.vue')
      },
      {
        path: '/admincheckorderdetail',
        name: 'adminCheckOrderDetail',
        component: () => import('../views/back/admin/adminCheckOrderDetail')
      },
      {
        path: '/admincheckgoodorderdetail',
        name: 'adminCheckGoodOrderDetail',
        component: () => import('../views/back/admin/adminCheckGoodOrderDetail')
      },
      {
        path: '/adminmanagegood',
        name: 'adminManageGood',
        component: () => import('../views/back/admin/adminManageGood')
      },
      {
        path: '/adminchecklogrecord',
        name: 'adminCheckLogRecord',
        component: () => import('../views/back/admin/adminCheckLogRecord')
      },
      {
        path: '/admincheckrecord',
        name: 'adminCheckRecord',
        component: () => import('../views/back/admin/adminCheckRecord')
      },
      {
        path: '/admincheckgoodexception',
        name: 'adminCheckGoodException',
        component: () => import('../views/back/admin/adminCheckGoodException')
      },
      {
        path: '/admincheckuserportrait',
        name: 'adminCheckUserPortrait',
        component: () => import('../views/back/admin/adminCheckUserPortrait')
      },
      {
        path: '/admincheckcategoryorder',
        name: 'adminCheckCategoryOrder',
        component: () => import('../views/back/admin/adminCheckCategoryOrder')
      },
      {
        path: '/admincheckgoodsell',
        name: 'adminCheckGoodSell',
        component: () => import('../views/back/admin/adminCheckGoodSell')
      },
    ],
    meta: {
      //isAuthRequired: true,
      adminPage: true,
    }
  },
  {
    path: '/salerlogin',
    name: 'salerLogin',
    component: () => import('../views/back/saler/salerLogin.vue'),
  },
  {
    path: '/salermain',
    name: 'salerMain',
    component: () => import('../views/back/saler/salerMain.vue'),
    children: [
      {
        path: '/salermanagecategory',
        name: 'salerManageCategory',
        component: () => import('../views/back/saler/salerManageCategory.vue'),
      },
      {
        path: '/salerinsertcategory',
        name: 'salerInsertCategory',
        component: () => import('../views/back/saler/salerInsertCategory.vue'),
      },
      {
        path: '/salerinsertgood',
        name: 'salerInsertGood',
        component: () => import('../views/back/saler/salerInsertGood.vue'),
      },
      {
        path: '/salermanagegood',
        name: 'salerManageGood',
        component: () => import('../views/back/saler/salerManageGood.vue'),
      },
      {
        path: '/salereditgood',
        name: 'salerEditGood',
        component: () => import('../views/back/saler/salerEditGood.vue'),
      },
      {
        path: '/salermanageuser',
        name: 'salerManageUser',
        component: () => import('../views/back/saler/salerManageUser.vue'),
      },
      {
        path: '/salerinsertuser',
        name: 'salerInsertUser',
        component: () => import('../views/back/saler/salerInsertUser.vue'),
      },
      {
        path: '/saleredituser',
        name: 'salerEditUser',
        component: () => import('../views/back/saler/salerEditUser.vue'),
      },
      {
        path: '/salermanagecart',
        name: 'salerManageCart',
        component: () => import('../views/back/saler/salerManageCart.vue'),
      },
      {
        path: '/salermanageOrder',
        name: 'salerManageOrder',
        component: () => import('../views/back/saler/salerManageOrder.vue'),
      },
      {
        path: '/salermanageOrderDetail',
        name: 'salerManageOrderDetail',
        component: () => import('../views/back/saler/salerManageOrderDetail.vue'),
      },
      {
        path: '/salercheckBrowseRecord',
        name: 'salerCheckBrowseRecord',
        component: () => import('../views/back/saler/salerCheckBrowseRecord'),
      },
    ],
    meta: {
      //isAuthRequired: true,
      salerPage: true,
    }
  },
  {
    path: '/index',
    name: 'Main',
    component: () => import('../views/front/Main.vue'),
    children: [
      {
        path: '/productsfeatured',
        name: 'ProductsFeatured',
        component: () => import('../views/front/ProductsFeatured'),
      },
      {
        path: '/allgood',
        name: 'AllGood',
        component: () => import('../views/front/AllGood'),
      },
      {
        path: '/gooddetail',
        name: 'GoodDetail',
        component: () => import('../views/front/GoodDetail'),
      },
      {
        path: '/registeruser',
        name: 'RegisterUser',
        component: () => import('../views/front/RegisterUser'),
      },
      {
        path: '/userlogin',
        name: 'UserLogin',
        component: () => import('../views/front/UserLogin'),
      },
      {
        path: '/cart',
        name: 'Cart',
        component: () => import('../views/front/Cart'),
        meta: {
          userPage: true
        }
      },
      {
        path: '/profile',
        name: 'Profile',
        component: () => import('../views/front/Profile'),
        meta: {
          userPage: true
        }
      },
      {
        path: '/insertorder',
        name: 'insertOrder',
        component: () => import('../views/front/insertOrder'),
        meta: {
          userPage: true
        }
      },
      {
        path: '/checkorder',
        name: 'checkOrder',
        component: () => import('../views/front/checkOrder'),
        meta: {
          userPage: true
        }
      },
      {
        path: '/checkorderdetail',
        name: 'checkOrderDetail',
        component: () => import('../views/front/chickOrderDetail'),
        meta: {
          userPage: true
        }
      },
    ]
  },


]

const router = createRouter({
  //history: createWebHistory(process.env.BASE_URL),
  history: createWebHashHistory(),
  //history: createWebHistory("/"),
   //mode: 'hash',
   //base: process.env.BASE_URL,
  routes
})

router.beforeEach((to,from,next)=>{
  if (to.meta.adminPage && store.state.admin.isLogin == false) {
    return next('/adminlogin');
  } else if(to.meta.salerPage && store.state.saler.isLogin == false) {
    return next('/salerlogin');
  } else if(to.meta.userPage && store.state.user.isLogin == false) {
    return next('/userlogin');
  }  else {
    next();
  }
  // if (to.meta.salerPage && store.state.saler.isLogin == false) {
  //   return next('/salerlogin');
  // } else {
  //   next();
  // }

})

export default router

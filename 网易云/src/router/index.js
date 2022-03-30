import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store'
import ElementUI from 'element-ui'

Vue.use(VueRouter)
//修改原生属性，捕获异常
var oldPush = VueRouter.prototype.push
VueRouter.prototype.push = function (url) {
  return oldPush.call(this, url).catch(function () { })
}

var oldReplace = VueRouter.prototype.replace
VueRouter.prototype.push = function (url) {
  return oldReplace.call(this, url).catch(function () { })
}
const routes = [
  {
    path: '/',
    component: () => import('../views/Main.vue'),
    children: [
      {
        path: 'myshowlist',
        component: () => import('../components/Myshowlist.vue'),
        children: [
          {
            path: 'songslist/:type?',
            component: () => import('../components/Songslist.vue')
          }
        ]
      }
    ]
  },
  {
    path: '/home',
    redirect: '/'
  },
  {
    path: '/listen',
    component: () => import('../views/Listen.vue')
  },
  {
    path: '/singer',
    component: () => import('../views/Singer.vue')
  },
  {
    path: '/upload',
    component: () => import('../views/Upload.vue'),
    meta: {
      isChk: true
    }
  },
  {
    path: '/mymusic',
    component: () => import('../views/MyMusic.vue'),
    meta: {
      isChk: true
    }
  },
  {
    path:'/none',
    component:()=>import('../views/None.vue')
  }

]
const router = new VueRouter({
  routes
})
router.beforeEach(function (to, from, next) {

  // to.matched//路由对象数组
  if (to.matched.some(function (route) {
    return route.meta.isChk
  })
  ) {
    if (store.state.User.Userid !='' 
    && store.state.User.Userid != null ) next()
    else {
      ElementUI.Message.error('请先登录')
      next(false)
    }
  }
  else next()

})

export default router

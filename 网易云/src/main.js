import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import qs from 'qs'
import axios from 'axios'
Vue.prototype.$axios = axios;
Vue.prototype.$qs = qs;

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


/*
*author:hgb
*by vs code and eclipse
*2022-1-3
*/

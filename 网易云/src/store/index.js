import Vue from 'vue'
import Vuex from 'vuex'
import Songs from './Songs'
import User from './User'
import UserLove from './UserLove'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  getters:{

  },
  actions: {
  },
  modules: {
    Songs,
    User,
    UserLove
  }
})

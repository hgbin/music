import ElementUI from "element-ui"
import axios from 'axios'
import qs from 'qs'
import store from "."
export default {
  namespaced: true,
  state: {
    Username: '',
    Userid:'',
    isReg:false,
    isLogin:false
  },
  mutations: {
    //登录函数
    login(state, user) {
      console.log(user.name, user.pwd1)
      var username = user.name
      var pwd = user.pwd1
      //先进行空值处理
      if (username == '' || username == null ||
        pwd == '' || pwd == null) {
        ElementUI.Message.error('LOGIN ERROR,YOUR INPUT NOT ALLOWED NULL')
      } else {
        //访问后端接口login，查询用户是否存在，存在则登录成功
        var data = qs.stringify({
          username,
          pwd
        })
        axios({
          method: 'post',
          url: '/Music/Login',
          data: data
        }).then((res) => {
          console.log(res.data)
          var isExist = res.data.isExist
          if (isExist) {//登录成功
            ElementUI.Message.success('LOGIN SUCCESSFUL!')
            sessionStorage.setItem('user', username)//同时在会话存储保存该用户名
            state.isLogin = true
            state.Username = username
            state.Userid = res.data.userid  
            //触发函数获取最爱列表
            store.commit('UserLove/getUserLove',state.Userid)
          }else{
            ElementUI.Message.error('LOGIN ERROR!')
          }
        }).catch((error) => {
          console.log(error.Message)
        })
      }
    },
    //注册函数
    reg(state, user) {
      console.log(user.name, user.pwd1 ,user.pwd2)
      var username = user.name
      var pwd = user.pwd1
      var pwd1 = user.pwd2
      //先进行空值处理
      if (username == '' || username == null ||
        pwd == '' || pwd == null || pwd1 == '' || pwd1 == null) {
        ElementUI.Message.error('REG ERROR,YOUR INPUT NOT ALLOWED NULL')
      }else{
        //访问后端接口reg，进行用户注册，注册成功则调到登录
        var data = qs.stringify({
          username,
          pwd,
          pwd1
        })
        axios({
          method: 'post',
          url: '/Music/Reg',
          data: data
        }).then((res) => {
          console.log(res.data.isReg)
          var isReg = res.data.isReg
          if (isReg) {//注册成功
            ElementUI.Message.success('Reg SUCCESSFUL!')
            state.isReg = true
          }else{
            ElementUI.Message.error('Reg ERROR!')
          }
        }).catch((error) => {
          console.log(error.Message)
        })
      }
    },
    //注销函数
    exit(state){
      state.isLogin = false
      state.Username = ''
      state.Userid = ''
      sessionStorage.clear()
      ElementUI.Message.success('EXIT SUCCESSFUL!')
    }
  },
  getters: {

  },
  actions: {
  },
  modules: {

  }
}
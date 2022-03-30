import axios from "axios";
import qs from 'qs'
export default {
    namespaced: true,
    state: {
       UserLove:[]
    },
    mutations: {
       getUserLove(state,userid){
        console.log('调用了列表函数',userid)
        var data = qs.stringify({
            userid:userid
        })
        axios({
            method:'post',
            url:'/Music/GetUserLoveMusic',
            data:data
        }).then((res)=>{
            console.log(res.data.lovelist)
            state.UserLove = res.data.lovelist
        }).catch((err)=>{
            console.log(err.message)
        })
       },
       addUserLove(state,Music){
        state.UserLove.push(Music)
       },
       removeUserLove(state,Music){
        var index = 0;
        var flag = state.UserLove.every((item)=>{
            if(item.musicid === Music.musicid){
                return false//相当于break
            }else{
                index++;
                return true
            }
        })
        if(!flag){//相当于找到了
            state.UserLove.splice(index,1)
        }
       }
    },
    getters:{
      
    },
    actions: {
    },
    modules: {
      
    }
  }
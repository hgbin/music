export default {
  namespaced: true,
  state: {
      singerlist:[],//歌手列表
      mvlist:[],//mv列表
      currentsong:{},//当前播放的音乐
      currentcomment:[],//对应的评论
      currentsinger:{},//当前展示的歌手
      searchsinger:{},//当前搜索的歌手
      songstype:[],//歌曲类型//为了上传使用?
      getAddid:'',//获取添加歌曲时访问网易云接口返回的id
      getALLMusicList:[],//获取到所有歌曲列表
      currentindex:0//获取到当前播放歌曲的index下标
  },
  mutations: {
      getsingerlist(state,getlist){
        state.singerlist = getlist
      },
      getmvlist(state,getlist){
        state.mvlist = getlist
      },
      getmycurrentsong(state,getsong){
        state.currentsong = getsong
      },
      getmycurrentcomment(state,getcomment){
        state.currentcomment = getcomment
      },
      getmycurrentsinger(state,getsinger){
        state.currentsinger = getsinger
      },
      getsearchsinger(state,getsinger){
        state.searchsinger = getsinger
      },
      getsongstype(state,getsongstype){
        console.log('获取到音乐类型',getsongstype)
        state.songstype = getsongstype
      },
      getAddid(state,getaddid){
        state.getAddid = getaddid
      },
      getALLMusicList(state,getALLMusicList){
        state.getALLMusicList = getALLMusicList
      },
      getcurrentindex(state,song){
        var index = 0
        var flag = state.getALLMusicList.every((item)=>{
          if(item.musicid === song.musicid)return false
          else{
            index++
            return true
          }
        })
        if(!flag)state.currentindex = index
      },
      updatecurrentindex(state,type){
        if(type == -1)state.currentindex--
        else if(type == 1)state.currentindex++ 
      }
  },
  getters:{
    
  },
  actions: {
  },
  modules: {
    
  }
}
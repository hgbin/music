import axios from 'axios'
import store from '../../store'
const url = 'http://localhost:3000'
export const returnpost = function(){//返回tomcat服务器的端口号（为了--更新图像
    return 'http://localhost:8099/Music/assets/image/'
}
//访问网易云接口-----热门歌手
export const getsingers = function(){
    axios({
        method:"get",
        url:  "/api/top/artists?offset=0&limit=10",
    }).then((res)=>{
        console.log('singer',res.data.artists)
        store.commit('Songs/getsingerlist',res.data.artists)
    }).catch((error)=>{
        console.log(error.message)
    })
}

//访问网易云接口-----MV排行
export const getmv = function(){
    axios({
        method:"get",
        url: "/api/top/mv?limit=10",
    }).then((res)=>{
        console.log('mv',res.data.data)
        store.commit('Songs/getmvlist',res.data.data)
    }).catch((error)=>{
        console.log(error.message)
    })
}

//访问网易云接口-----热门评论
export const getcomment = function(musicid){
    axios({
        method:"get",
        url:  "/api/comment/music?id="+ musicid +"&limit=10",
    }).then((res)=>{
        console.log('comments',res.data.hotComments)
        store.commit('Songs/getmycurrentcomment',res.data.hotComments)
    }).catch((error)=>{
        console.log(error.message)
    })
}

//访问网易云接口-----获取歌手基本信息(对象)（搜索框需要先搜索id）
export const getSingerInfo = function(singername){
    axios({
        method:"get",
        url:  "/api/search?keywords="+ singername + "&type=100",
    }).then((res)=>{
        console.log(res.data.result.artists[0])
        store.commit('Songs/getsearchsinger',res.data.result.artists[0])
    }).catch((error)=>{
        console.log(error.message)
    })
}

//访问网易云接口-----获取歌手id
export const getSingerid = function(singername){
    axios({
        method:"get",
        url:  "/api/search?keywords="+ singername + "&type=100",
    }).then((res)=>{
        console.log(res.data.result)
        console.log('mvSinger',res.data.result.artists[0].id)
        var singerid = res.data.result.artists[0].id
        getSingerData(singerid)//访问当前歌手数据
    }).catch((error)=>{
        console.log(error.message)
    })
}

//访问网易云接口-----获取歌手数据(被上面函数访问)
export const getSingerData = function(singerid){
    axios({
        method:"get",
        url: "/api/artist/desc?id="+ singerid,
    }).then((res)=>{
        console.log('mvSinger',res.data)
        store.commit('Songs/getmycurrentsinger',res.data)
    }).catch((error)=>{
        console.log(error.message)
    })
}

//访问网易云接口------获取待添加音乐的相关的信息(id)--我好像只需要id?
export const getAddsongid = function(songname,songsinger){
    axios({
        method:"get",
        url:  url + "/search?keywords="+ songname +"," + songsinger
    }).then((res)=>{
        console.log('mvSinger',res.data.result.songs[0])
        store.commit('Songs/getAddid',res.data.result.songs[0].id)
    }).catch((error)=>{
        console.log(error.message)
    })
}

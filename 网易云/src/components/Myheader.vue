<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <div id="logo-img">
          >
         <router-link to="/home" class="home">
            <el-image
              style="height: 70px; cursor: pointer"
              :src="url"
              fit:fill
            >
            </el-image>
         </router-link>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="header-link">
          <router-link to="/none" id="small-span" class="unclick">发现音乐</router-link>
          <router-link to="/mymusic" id="small-span">我的音乐</router-link>
          <router-link to="/none" id="small-span" class="unclick">关注</router-link>
          <router-link to="/none" id="small-span" class="unclick">商城</router-link>
          <router-link to="/none" id="small-span" class="unclick">音乐人</router-link>
          <router-link to="/none" id="small-span" class="unclick">下载客户端</router-link>
        </div>
      </el-col>
      <el-col :span="4" id="search-box">
        <el-input
          placeholder="音乐/歌手"
          clearable
          v-model="search"
          class="my-input"
        >
        </el-input>
        <div id="search-list" v-if="search_show">
          <span id="small-span" class="show-list super-small-span">歌曲</span>
          <template v-for="(item) in searchsongs">
            <div :key="item.musicid" id="search-div">
             <router-link :to='{path:"/listen",query:{item}}'>
                <div id="small-span"
                 class="super-small-span"
                 @click="miss">{{item.musicname}}</div>
             </router-link>
            </div>
          </template>
          <span id="small-span" class="show-list super-small-span">歌手</span>
           <template v-for="(item,index) in searchsingers">
            <div :key="index" id="search-div">
             <div id="small-span" 
             class="super-small-span"
             @click="miss();findsinger(item.musicsinger)">{{item.musicsinger}}</div>
            </div>
          </template>
        </div>
      </el-col>
      <el-col :span="2">
        <div id="upload-box">
          <router-link to="/upload" id="small-span" >上传</router-link>
        </div>
      </el-col>
    </el-row>
    <el-row ::gutter="20" id="my-header2">
      <el-col :span="12" id="my-header2-box">
        <router-link to="/none" 
        id="small-span" 
        class="super-small-span unclick"
          >推荐</router-link
        >
        <router-link to="/none" 
        id="small-span" 
        class="super-small-span unclick"
          >排行榜</router-link
        >
        <router-link to="/none"
         id="small-span"
          class="super-small-span unclick"
          >歌单</router-link
        >
        <router-link to="/none" 
        id="small-span" 
        class="super-small-span unclick"
          >主播电台</router-link
        >
        <router-link to="/none"
         id="small-span"
          class="super-small-span unclick"
          >歌手</router-link
        >
        <router-link to="/none"
         id="small-span" 
         class="super-small-span unclick"
          >新碟上架</router-link
        >
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as myapi from "../assets/js/Myapi.js";
import { mapState } from "vuex";
export default {
  data() {
    return {
      url: require("../assets/image/header.png"),
      search: "",
      search_show: false,
      searchsongs: [],
      searchsingers: [],
    };
  },
  created () {
    window.addEventListener('scroll',()=>{
      this.miss()
    })
  },
  methods: {
    show() {
      if (this.search == "") this.search_show = false;
      else {
        this.search_show = true;
        //访问后端获取数据(只有输入不为空的情况下)
        this.getsearch();
      }
    },
    miss() {
      this.search = ''
      this.search_show = false;
    },
    getsearch() {
      this.$axios({
        method: "get",
        url: "/Music/SearchAll",
        params: {
          search: this.search,
        },
      })
        .then((res) => {
          console.log(res.data.searchsongs,res.data.searchsingers);
          this.searchsongs = res.data.searchsongs
          this.searchsingers = res.data.searchsingers
        })
        .catch((error) => {
          console.log(error);
        });
    },
    findsinger(singer){
      myapi.getSingerInfo(singer)
    }
  },
  computed: {
    ...mapState("Songs",["searchsinger"])
  },
  watch: {
    search(newvalue) {
      console.log("new搜索", newvalue);
      this.show(); //如果搜索不为空，则显示，否则关闭查询结果框
    },
    searchsinger(newvalue){
      //如果观察到变化，证明搜索到该歌手的信息
      //继续跳转到singer路由
      console.log('搜索的歌手',newvalue)
      var singer = newvalue
      this.$router.push({path:'/singer',query:{singer}})
    }
  }
  
};
</script>

<style>
@import "../assets/css/myheader.css";
@import "../assets/css/normal.css";
</style>

<template>
  <div>
    <el-container>
      <el-main id="show-big-box" v-cloak>
        <div
          class="list"
          v-infinite-scroll="load"
          infinite-scroll-disabled="disabled"
        >
          <template v-for="(item, index) in musiclist" class="list-item">
            <div :key="index" id="my-show-box">
              <router-link :to='{path:"/listen",query:{item}}'><el-image :src="`${item.musicbackurl}`" fit="fit"></el-image></router-link>
              <span id="my-less-span">{{item.musicname}}</span>
            </div>
          </template>
        </div>
        <p v-if="loading"><i class="el-icon-loading"></i></p>
        <p v-if="noMore" class="super-small-span tip">No more</p>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {  mapMutations } from "vuex";
export default {
  data() {
    return {
      searchtype: "",
      musiclist: [],
      count: 1,
      totalpages: 0, //后端传递过来的总页数...
      loading: false,
    };
  },
  created() {
    this.initlist();
    this.getmusiclist();
  },
  methods: {
    initlist() {
      var search = this.$route.query.showtype;
      console.log(this.$route.query.showtype);
      if (search != null && search != "") {
        this.searchtype = search;
        console.log("searchtype", this.searchtype);
      }
    },
    load() {
      this.loading = true;
      setTimeout(() => {
        this.count += 1; //每次查询的页数+1
        console.log("调用了load?", this.count, this.totalpages);
        this.getmusiclist(); //调用后端接口，每次查询最新的记录...//然后更新音乐列表
      }, 500);
    },
    getmusiclist() {
      var data = this.$qs.stringify({
        searchtype: this.searchtype,
        musiclist: JSON.stringify(this.musiclist),
        currentpage: this.count,
        pagesize: 6,
      });
      this.$axios({
        method: "post",
        url: "/Music/SearchmusicInit",
        data: data,
      })
        .then((res) => {
          console.log(res.data.musiclist)
          this.musiclist = res.data.musiclist;
          //刷新所有歌曲到列表中
          this.getALLMusicList(this.musiclist)
          this.totalpages = res.data.totalnum;
          this.loading = false;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    ...mapMutations('Songs',["getALLMusicList"])
  },
  computed: {
    noMore() {
      //没有数据的界限???
      //当起始页数大于总页数时停止加载
      return this.count >= this.totalpages;
    },
    disabled() {
      return this.loading || this.noMore;
    },
  },
  watch: {
    $route: function () {
      this.initlist();
      this.count = 0;//改变查询的类型时需要清空列表，以及设置count = 0,然后调用load函数
      this.musiclist = [];
      this.load();
    },
  },
};
</script>

<style>
@import '../assets/css/songslist.css';
@import '../assets/css/normal.css';
</style>

<template>
  <div>
    <el-container>
      <el-main id="my-singer-main">
        <div id="new-singer-box">
          <el-image
            :src="singer.img1v1Url"
            id="singer-img"
            fit="fit"
          ></el-image>
        </div>
        <div id="new-singer-content">
          <div class="title-span">个人介绍</div>
          <div id="small-span">{{ singercontent.briefDesc }}</div>
          <template v-for="(item, index) in singercontent.introduction">
            <div :key="index">
              <div class="title-span">{{ item.ti }}</div>
              <div id="small-span">{{item.txt}}</div>
            </div>
          </template>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import * as myapi from "../assets/js/Myapi.js";
import { mapState } from "vuex";
export default {
  data() {
    return {
      singer: {},
      singercontent: {},
    };
  },
  created() {
    this.initsinger();
  },
  methods: {
    initsinger() {
      var get = this.$route.query.singer;
      console.log(get.length);
      if (get.length == 15) {
        this.$router.push("/home");
      }
      if (get != "undefined" && get != null && get != "") {
        this.singer = Object.assign({}, get);
        console.log(this.singer);
        //访问网易云接口获取到当前歌手的详情介绍
        myapi.getSingerid(this.singer.name);
      }
    },
  },
  computed: {
    ...mapState("Songs", ["currentsinger"]),
  },
  watch: {
    currentsinger(newvalue) {
      this.singercontent = newvalue;
    },
     $route() {
      this.initsinger();
    },
  },
};
</script>

<style>
@import "../assets//css/normal.css";
@import "../assets/css/singer.css";
</style>

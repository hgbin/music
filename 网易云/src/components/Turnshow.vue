<template>
  <div>
    <el-container>
      <el-main  id="my-turn-box">
        <el-carousel :interval="5000" arrow="always">
          <el-carousel-item 
          v-for="(item, index) in turnshow.url2" 
          :key="index"
          :style="{backgroundImage:`url(${turnshow.url1[index].url})`}">
            <el-image
              style="height:300px"
              :src="item.url"
              fit="cover"
            ></el-image>
          </el-carousel-item>
        </el-carousel>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import * as tools from "../assets/js/Mytools.js";
export default {
  data() {
    return {
      turnshow: tools.getMyturnurl(),
      turn_index:0
    };
  },
  methods: {
  },
  mounted () {
  }
};
</script>

<style>
@import '../assets/css/Myturn.css';
</style>

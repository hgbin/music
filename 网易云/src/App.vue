<template v-cloak>
  <div class="my_app">
    <el-backtop :bottom="100" :visibility-height="100" :right="30">
      <i
        class="el-icon-arrow-up"
        style="
           {
            font-weight: bolder;
            font-size: 20px;
          }
        "
      ></i>
    </el-backtop>
    <myheader></myheader>
    <router-view></router-view>
  </div>
</template>

<script>
import Myheader from "./components/Myheader.vue";
export default {
  components: { Myheader },
  data() {
    return {
     
    };
  },
  methods: {},
};
</script>

<style></style>

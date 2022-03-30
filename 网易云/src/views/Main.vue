<template>
  <div>
    <turnshow></turnshow>
    <router-view></router-view>
  </div>
</template>

<script>
import Turnshow from "../components/Turnshow.vue";
export default {
  components: { Turnshow },
  data() {
    return {
      homenone: true,
    };
  },
  created() {
    console.log("初始化");
    this.$router.push({ path: "/myshowlist/songslist" });
  },
  methods: {
    checkpath() {
      var router_path = this.$route.fullPath;
      if (router_path === '/myshowlist/songslist?showtype='){
        this.homenone = false;
        console.log('???',this.homenone)
      } 
      if (!this.homenone) {
        document.getElementsByClassName("home")[0].classList.add("unclick");
      }
    },
    removeclass(){
      document.getElementsByClassName("home")[0].classList.remove("unclick");
    }
  },
  mounted() {
    this.checkpath();
  },
  watch: {
    $route: function () {
      console.log("watch", this.$route.fullPath);
      this.checkpath()
      // this.$router.push({path:'/myshowlist/songslist'})
    },
  },
  beforeRouteLeave:function(to,from,next){
    console.log('离开',to.fullPath)
    this.removeclass()
    next()
  }
};
</script>

<style></style>

<template>
 <el-container>
      <el-main class="to-middle">
          <div class="my-love-box">
              <template v-for="(item, index) in UserLove">
                    <div :key="index" class="my-new-love-box">
                      <router-link :to='{path:"/listen",query:{item}}'><el-image 
                      :src="`${item.musicbackurl}`" 
                      fit="contain"
                      >
                      </el-image>
                      </router-link>
                      <span id="my-less-span">{{item.musicname}}</span>
                    </div>
              </template>
          </div>
      </el-main>
 </el-container>
</template>

<script>
import { mapState ,mapMutations} from "vuex";
export default {
    data () {
        return {
            
        }
    },
    created () {
    },
    computed: {
        ...mapState('UserLove',["UserLove"]),
        ...mapState('User',["Userid"])
    },
    methods: {
        ...mapMutations('UserLove',["getUserLove"])
    }
}
</script>

<style>
@import '../assets/css/MyMusic.css';
@import '../assets/css/normal.css';
</style>
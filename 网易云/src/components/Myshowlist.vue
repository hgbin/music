<template>
  <div class="app">
    <el-container id="main-big-box">
      <el-main id="main-box1">
        <el-container
          style="border-bottom: 2px solid #c10d0c; flex: 0 !important"
        >
          <div id="main-header">
            <span id="big-span">热门推荐</span>
            <ul>
              <li
                v-for="(item, index) in musictype"
                :key="index"
                class="super-small-span li-span"
                @click="changetype(item)"
              >
                {{ item }}
              </li>
            </ul>
          </div>
        </el-container>
        <el-container>
          <el-main>
            <!-- 子路由显示的地方 -->
            <router-view></router-view>
          </el-main>
        </el-container>
        <!-- Mv排行榜 -->
        <el-container
          style="border-bottom: 2px solid #c10d0c; flex: 0 !important"
        >
          <el-main>
            <div id="main-header">
              <span id="big-span">MV排行</span>
            </div>
          </el-main>
        </el-container>
        <el-container>
          <el-main>
            <template v-for="(item, index) in mymvlist">
              <div :key="index" id="mv-box">
                <span id="small-span"> {{ index + 1 }}</span>
                <el-image
                  :src="item.cover"
                  fit="cover"
                  style="
                    width: 50px;
                    height: 50px;
                    border-radius: 50%;
                    cursor: pointer;
                  "
                ></el-image>
                <span id="small-span"> {{ item.name }}</span
                >---
                <span id="small-span"> {{ item.artistName }}</span>
              </div>
            </template>
          </el-main>
        </el-container>
      </el-main>
      <el-main id="main-box2">
        <div id="main-box2-login">
          <div id="login-tip">
            <span class="super-small-span login-span"
            v-if="!isLogin">
              登录网易云音乐，可以享受无限收藏的乐趣，并且无限同步到手机
            </span>
            <el-button
              size="mini"
              round
              class="super-small-span my-login-bt"
              @click="dialogFormVisible = true"
              v-if="!isLogin">登录</el-button>
              <span v-if="isLogin" 
              class=" super-small-span login-span"
              >WelCome to 网抑云！Dear -{{Username}}</span>
            <el-button
              size="mini"
              round
              class="super-small-span my-login-bt"
              v-if="isLogin"
              @click="myexit">注销</el-button>
          </div>
        </div>
        <div id="singer-box">
          <div id="small-span" class="tip-box tip-box-span">热门歌手</div>
          <template v-for="(item, index) in singerlist">
            <div :key="index" id="singer-show">
              <el-image
                :src="item.img1v1Url"
                style="
                  width: 40px;
                  height: 40px;
                  border-radius: 50%;
                  cursor: pointer;
                "
                fit="fit"
                @click="getsinger(item)"
              ></el-image>
              <span id="small-span"> {{ item.name }}</span>
            </div>
          </template>
        </div>
      </el-main>
    </el-container>
    <!-- 登录窗口 -->

    <el-dialog
      class="login-dialog title-span"
      title="登录"
      :visible.sync="dialogFormVisible"
    >
      <div slot="title" class="login-title">
        <el-tabs
          v-model="activeName"
          @tab-click="handleClick"
          class="my-login-tab"
        >
          <el-tab-pane
            label="用户登录"
            name="login"
            class="my-login-tab-pane tab-span"
          >
          </el-tab-pane>
          <el-tab-pane
            label="用户注册"
            name="reg"
            class="my-login-tab-pane tab-span"
          >
          </el-tab-pane>
        </el-tabs>
      </div>
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input
            class="my-login-input"
            v-model="form.name"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="用户密码" :label-width="formLabelWidth">
          <el-input
            class="my-login-input"
            v-model="form.pwd1"
            type="password"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item
          label="再次输入用户密码"
          :label-width="formLabelWidth"
          v-if="show === false"
        >
          <el-input
            class="my-login-input"
            v-model="form.pwd2"
            type="password"
            autocomplete="off"
            clearable
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="mylogin" class="my-login-bt-new" v-if="show === true"
          >LOGIN</el-button
        >
        <el-button @click="myreg" class="my-login-bt-new" v-if="show === false"
          >REG</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import * as myapi from "../assets/js/Myapi.js";
import { mapState, mapMutations } from "vuex";
export default {
  data() {
    return {
      musictype: [],
      showtype: "",
      showlist: [],
      mysingerlist: [],
      mymvlist: [],
      dialogFormVisible: false, //登录窗口
      form: {
        name: "",
        pwd1: "",
        pwd2: "",
      }, //登录表单
      formLabelWidth: "120px",
      activeName: "login", //标签页激活
      show: true, //优先显示登录窗口
    };
  },
  created() {
    this.initType();
    this.initsongs();
  },
  mounted() {
    this.initsingers();
  },
  computed: {
    ...mapState("Songs", ["singerlist", "mvlist"]),
    ...mapState("User", ["isReg","isLogin","Username"]),
  },
  methods: {
    initType() {
      this.$axios({
        method: "get",
        url: "/Music/SearchmusicType",
      })
        .then((res) => {
          console.log(res.data.musictype);
          this.musictype = res.data.musictype;
          this.$store.commit('Songs/getsongstype',this.musictype)
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    initsongs() {
      //传递到路由组件中，显示不同类型的音乐
      console.log("初始化音乐列表");
      var showtype = this.showtype;
      this.$router.push({ path: "songslist", query: { showtype } }); //初始值为空或点击触发该事件
    },
    changetype(item) {
      this.showtype = item;
      console.log(this.showtype);
      this.initsongs();
    },
    //访问网易云接口查找歌手信息/Mv排行
    initsingers() {
      myapi.getsingers();
      myapi.getmv();
    },
    //跳转到singer路由.
    getsinger(singer) {
      this.$router.push({ path: "/singer", query: { singer } });
    },
    handleClick(tab) {
      console.log(tab.index);
      if (tab.index === "0") {
        this.activeName = "login";
        this.show = true;
      } else if (tab.index === "1") {
        this.activeName = "reg";
        this.show = false;
        (this.form.name = ""), (this.form.pwd2 = "");
      }
    },
    //登录
    mylogin() {
      this.dialogFormVisible = false;
      this.$store.commit("User/login", this.form);
      this.clearinput();
    },
    //注册
    myreg() {
      this.dialogFormVisible = false;
      this.$store.commit("User/reg", this.form);
      this.clearinput();
    },
    //注销
    myexit(){
      this.exit()
    },
    clearinput() {
      (this.form.name = ""), (this.form.pwd1 = ""), (this.form.pwd2 = "");
    },
    ...mapMutations("User", ["login", "reg","exit"]),
  },
  watch: {
    singerlist(newvalue) {
      console.log("change");
      this.mysingerlist = newvalue;
    },
    mvlist(newvalue) {
      console.log("mvchange");
      this.mymvlist = newvalue;
    },
    isReg(newvalue) {
      console.log("注册成功");
      if (newvalue) {
        this.dialogFormVisible = true;
        this.activeName = "login";
        this.show = true;
      }
    }
  },
};
</script>

<style>
@import "../assets/css/normal.css";
@import "../assets/css/Myshowlist.css";
</style>

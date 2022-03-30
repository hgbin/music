<template>
  <div>
    <el-container>
      <el-main style="padding: 0 !important">
        <div id="music-box">
          <div id="music-back-box">
            <el-image :src="currentsong.musicbackurl" id="rotate-img">
            </el-image>
          </div>
          <div id="music-control-box">
            <span id="small-span" class="music-show-span"
              >NAME: {{ currentsong.musicname }}</span
            >
            <span id="small-span" class="music-show-span"
              >SINGER: {{ currentsong.musicsinger }}</span
            >
            <span id="small-span" class="music-show-span"
              >TIME: {{ currentsong.musicDatetime }}</span
            >
            <span id="small-span" class="music-show-span"
              >TYPE: {{ currentsong.musicType }}</span
            >
            <span id="small-span" class="music-show-span">JOIN LOVE</span>
            <div class="listen-box">
              <el-button
                id="my-love-bt"
                icon="el-icon-d-arrow-left"
                circle
                @click="changePlayingsong(-1)"
              >
              </el-button>
              <el-button
                id="my-love-bt"
                icon="el-icon-star-off"
                circle
                @click="AddJoin"
              >
              </el-button>
              <el-button
                id="my-love-bt"
                icon="el-icon-d-arrow-right"
                circle
                @click="changePlayingsong(1)"
              >
              </el-button>
              <el-popover placement="right" width="400" trigger="click">
                <div class="show-ALLMusic-box">
                  <template v-for="(item, index) in getALLMusicList">
                    <div :key="index" class="show-ALLMusic-small-box">
                      <router-link :to="{ path: '/listen', query: { item } }">
                        <el-image
                          :src="item.musicbackurl"
                          fit="cover"
                          class="show-ALL-image"
                        >
                        </el-image>
                      </router-link>
                      <span id="small-span">{{ item.musicname }}</span
                      >---
                      <span id="small-span">{{ item.musicsinger }}</span>
                    </div>
                  </template>
                </div>
                <el-button
                  slot="reference"
                  icon="el-icon-s-data"
                  id="my-love-bt"
                  circle
                ></el-button>
              </el-popover>
            </div>
          </div>

          <div v-loading="loading" id="music-iframe-box">
            <iframe
              id="iframe"
              frameborder="no"
              border="0"
              marginwidth="0"
              marginheight="0"
              width="100%"
              height="150"
              :src="currentsong.musicoutlink"
            ></iframe>
          </div>
        </div>
      </el-main>
    </el-container>
    <el-button
      @click="drawer = true"
      id="my-comment-bt"
      icon="el-icon-edit"
      round
    >
      评论
    </el-button>

    <el-drawer title="热门评论" :visible.sync="drawer">
      <template v-for="(item, index) in mycomment">
        <div :key="index" id="comment-box">
          <el-image
            :src="item.user.avatarUrl"
            style="width: 30px; height: 30px; border-radius: 50%"
          ></el-image>
          <div>
            <span id="small-span" class="comment-span love-span"
              >loveCount:{{ item.likedCount }} Times</span
            >
          </div>
          <div>
            <span id="small-span" class="comment-span name-span"
              >{{ item.user.nickname }}:</span
            >
            <span id="small-span" class="comment-span">{{ item.content }}</span>
          </div>
        </div>
      </template>
    </el-drawer>

    <el-button @click="edit" id="my-update-bt" icon="el-icon-edit" round>
      编辑
    </el-button>

    <el-drawer
      title="编辑修改"
      :visible.sync="drawer1"
      direction="ttb"
      class="my-update-drawer"
    >
      <div class="my-upload-main">
        <el-form
          ref="form"
          :model="currentsong"
          label-width="80px"
          class="my-upload-form"
        >
          <el-form-item class="title-span my-upload-form-item" label="SONG-ID">
            <el-input
              v-model="currentsong.musicid"
              class="my-upload-input"
              clearable
              :disabled="true"
            ></el-input>
          </el-form-item>
          <el-form-item
            class="title-span my-upload-form-item"
            label="SONG-NAME"
          >
            <el-input
              v-model="currentsong.musicname"
              class="my-upload-input"
              clearable
              placeholder="例如: 水星记"
            ></el-input>
          </el-form-item>
          <el-form-item
            class="title-span my-upload-form-item"
            label="SONG-SINGER"
          >
            <el-input
              v-model="currentsong.musicsinger"
              class="my-upload-input"
              clearable
              placeholder="例如：郭顶"
            ></el-input>
          </el-form-item>
          <el-form-item
            class="title-span my-upload-form-item"
            label="SONG-DATETIME"
          >
            <el-date-picker
              v-model="currentsong.musicDatetime"
              type="datetime"
              placeholder="选择日期时间"
              align="right"
              :picker-options="pickerOptions"
              class="my-form-date"
            >
            </el-date-picker>
          </el-form-item>
          <el-form-item
            class="title-span my-upload-form-item"
            label="SONG-MUSICTYPE"
          >
            <el-select
              v-model="currentsong.musicType"
              placeholder="请选择音乐类型"
              class="my-form-select"
            >
              <el-option
                v-for="(item, index) in musictype"
                :key="index"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            class="title-span my-upload-form-item"
            label="MUSIC-BACKGROUND"
          >
            <el-input
              v-model="currentsong.musicbackurl"
              class="my-upload-input"
              :disabled="true"
              clearable
            ></el-input>
            <el-upload
              class="upload-demo"
              action="/Music/Upload"
              ref="update"
              list-type="picture-card"
              :on-preview="handlePictureCardPreview"
              :on-change="change"
              :on-success="uploadsuccess"
              :on-error="uploaderror"
              :auto-upload="false"
              enctype="multipart/form-data"
              :limit="1"
            >
              <i class="el-icon-plus"></i>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="" />
            </el-dialog>
          </el-form-item>
          <el-form-item
            class="title-span my-upload-form-item"
            label="SONG-OUTLINK"
          >
            <el-input
              v-model="currentsong.musicoutlink"
              class="my-upload-input"
              clearable
              :disabled="true"
            ></el-input>
          </el-form-item>
          <el-button
            style="warning"
            icon="el-icon-success"
            circle
            @click="sureupdate"
          ></el-button>
        </el-form>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import * as myapi from "../assets/js/Myapi.js";
import { mapState, mapMutations } from "vuex";
export default {
  data() {
    return {
      playmusic: {},
      mycomment: [],
      musictype: [],
      drawer: false,
      drawer1: false,
      dialogVisible: false, //图片展示
      dialogImageUrl: "", //预览图片
      liked: false, //喜欢状态
      loading:false,
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            },
          },
          {
            text: "昨天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit("pick", date);
            },
          },
          {
            text: "一周前",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            },
          },
        ],
      },
    };
  },
  created() {
    this.initgetmusic();
    this.initcommpent();
    this.IsJoin();
    this.getcurrentindex(this.currentsong);
    this.initloading()
  },
  methods: {
    initgetmusic() {
      this.musictype = this.songstype;
      var get = this.$route.query.item;
      console.log(get.length);
      if (get.length == 15) {
        this.$router.push("/home");
      }
      if (get != "undefined" && get != null && get != "") {
        this.playmusic = Object.assign({}, get);
        console.log(this.playmusic);
        this.getmycurrentsong(this.playmusic);
      }
    },
    initcommpent() {
      //获取当前歌曲的评论
      myapi.getcomment(this.playmusic.musicid);
    },
    //----图片预览-----------------------
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    //------文件改变时
    change(file) {
      console.log(file);
    },
    //--------文件上传成功时
    uploadsuccess(response, file, fileList) {
      this.currentsong.musicbackurl = myapi.returnpost() + file.name;
      console.log("上传成功", response, file, file.name, fileList);
      //接着进行上传
      //调用另一个上传函数把form内容上传到后台.
    },
    //--------文件上传失败时
    uploaderror(file) {
      console.log(file);
      this.$message.error("上传失败");
    },
    //更新所有内容
    updateAll() {
      var _this = this;
      console.log("?????");
      var data = this.$qs.stringify({
        music: JSON.stringify(this.currentsong),
      });
      this.$axios({
        method: "post",
        url: "/Music/UpdateMusic",
        data: data,
      })
        .then(function (respone) {
          console.log(respone.data.tip);
          if (respone.data.tip === true) {
            setTimeout(() => {
              _this.$message.success("编辑成功");
            }, 1000);
          } else {
            setTimeout(() => {
              _this.$message.warning("上传失败");
            }, 1000);
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    edit() {
      if (
        this.Userid === null ||
        this.Userid === "" ||
        sessionStorage.user == null
      ) {
        this.$message.error("请先登录");
        setTimeout(() => {
          this.$router.push("/home");
        }, 1000);
        return;
      }
      this.drawer1 = true;
    },
    //确定更新
    sureupdate() {
      this.$confirm("确定上传信息无误嘛, 是否继续?", "上传提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then((action) => {
          if (action === "confirm") {
            this.drawer1 = false;
            this.$refs.update.submit(); //上传照片先...
            setTimeout(() => {
              this.updateAll();
            }, 1000);
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消编辑",
          });
        });
    },
    //需要定义一个方法（为了在初始化的时候知道该用户是否已经存在该歌曲
    IsJoin() {
      if (
        this.Userid === null ||
        this.Userid === "" ||
        sessionStorage.user == null
      ) {
        this.liked = false;
        return;
      } else {
        var data = this.$qs.stringify({
          musicid: this.currentsong.musicid,
          userid: this.Userid,
        });
        this.$axios({
          method: "post",
          url: "/Music/CheckLoveMusic",
          data: data,
        })
          .then((respone) => {
            console.log("是否存在该歌曲", respone.data.tip);
            if (respone.data.tip) {
              this.liked = true;
            } else {
              this.liked = false;
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    //加入收藏
    AddJoin() {
      if (
        this.Userid === null ||
        this.Userid === "" ||
        sessionStorage.user == null
      ) {
        this.$message.error("请先登录");
        setTimeout(() => {
          this.$router.push("/home");
        }, 1000);
        return;
      }
      //
      var data = this.$qs.stringify({
        musicid: this.currentsong.musicid,
        userid: this.Userid,
      });
      if (this.liked) {
        //如果是取消收藏状态
        //访问后端接口//取消收藏
        this.liked = false;
        this.$axios({
          method: "post",
          url: "/Music/QuitLoveMusic",
          data: data,
        })
          .then((respone) => {
            console.log(respone.data.tip);
            if (respone.data.tip) {
              this.$message.success("取消收藏咯");
              this.removeUserLove(this.currentsong);
            } else {
              this.$message.error("取消失败咯");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      } else {
        //如果是加入收藏状态
        //访问后端接口//加入收藏
        this.liked = true;
        this.$axios({
          method: "post",
          url: "/Music/AddLoveMusic",
          data: data,
        })
          .then((respone) => {
            console.log(respone.data.tip);
            if (respone.data.tip) {
              this.$message.success("收藏成功咯");
              this.addUserLove(this.currentsong);
            } else {
              this.$message.error("收藏失败咯");
            }
          })
          .catch(function (error) {
            console.log(error);
          });
      }
    },
    change1(newvalue) {
      this.currentsong.musicDatetime = newvalue.musicDatetime;
    },
    //改变播放盒子对应的颜色
    initColor(index) {
      for (var i = 0; i < this.getALLMusicList.length; i++) {
        document.getElementsByClassName("show-ALLMusic-small-box")[
          i
        ].style.cssText = " background-color:white;";
      }
      document.getElementsByClassName("show-ALLMusic-small-box")[
        index
      ].style.cssText =
        " background-color: rgba(240, 172, 178, 0.64) !important;";
    },
    //进行上/下播放
    changePlayingsong(type) {
      var newindex = -1;
      var end = this.getALLMusicList.length;
      if (type == -1) {
        //上一首播放
        if (this.currentindex === 0) {
          newindex = end - 1;
        } else {
          //同时更新全局状态管理中的currentindex
          this.$store.commit("Songs/updatecurrentindex", -1);
          newindex = this.currentindex;
        }
      } else if (type == 1) {
        //下一首播放
        if (this.currentindex === end - 1) {
          newindex = 0;
        } else {
          //同时更新全局状态管理中的currentindex
          this.$store.commit("Songs/updatecurrentindex", 1);
          newindex = this.currentindex;
        }
      }
      //更新播放歌曲
      var item = this.getALLMusicList[newindex];
      console.log("改变时新的Lisetn", item);
      this.$router.push({ path: "/Listen", query: { item } });
    },
    //加载效果
    initloading(){
      this.loading = true
      setTimeout(() => {
        this.loading = false
      }, 500);
    },
    ...mapMutations("Songs", [
      "getmycurrentsong",
      "getcurrentindex",
    ]),
    ...mapMutations("UserLove", ["addUserLove", "removeUserLove"]),
  },
  computed: {
    ...mapState("Songs", [
      "currentsong",
      "currentcomment",
      "getALLMusicList",
      "currentindex",
      "songstype"
    ]),
    ...mapState("User", ["Userid"]),
    ...mapState("UserLove", ["UserLove"]),
  },
  watch: {
    $route() {
      this.initgetmusic();
      this.initcommpent();
      this.IsJoin();
      this.getcurrentindex(this.currentsong);
      this.initloading()
    },
    currentcomment(newvalue) {
      this.mycomment = newvalue;
    },
    songstype(newvalue) {
      this.musictype = newvalue;
      console.log('??????????',this.musictype);
    },
    "currentsong.musicbackurl"(newvalue) {
      this.currentsong.musicbackurl = newvalue;
    },
    UserLove(newvalue) {
      console.log("我的收藏列表在变化", newvalue);
    },
    currentsong: {
      handler: "change1",
      deep: true,
    },
    currentindex(newvalue) {
      this.initColor(newvalue);
    }
  },
};
</script>

<style>
@import "../assets/css/Listen.css";
@import "../assets/css/normal.css";
</style>

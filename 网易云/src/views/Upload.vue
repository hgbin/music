<template>
  <div>
    <el-container>
      <el-main class="my-upload-main">
        <div id="my-upload-title">网抑云上传</div>
        <el-form
          ref="form"
          :model="form"
          label-width="80px"
          class="my-upload-form"
        >
          <el-form-item class="title-span my-upload-form-item" label="SONG-ID">
            <el-input
              v-model="form.musicid"
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
              v-model="form.musicname"
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
              v-model="form.musicsinger"
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
              v-model="form.musicDatetime"
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
              v-model="form.musicType"
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
              v-model="form.musicbackurl"
              class="my-upload-input"
              :disabled="true"
              clearable
            ></el-input>
            <el-upload
              class="upload-demo"
              action="/Music/Upload"
              ref="upload"
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
              v-model="form.musicoutlink"
              class="my-upload-input"
              clearable
              :disabled="true"
            ></el-input>
          </el-form-item>
          <el-button
            style="warning"
            icon="el-icon-success"
            circle
            @click="sureupload"
          ></el-button>
        </el-form>
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
      form: {
        musicid: "",
        musicname: "",
        musicsinger: "",
        musicDatetime: "",
        musicType: "",
        musicurl: "",
        musicbackurl: "",
        musicoutlink: "",
      },
      musictype: [],
      dialogVisible: false, //图片展示
      dialogImageUrl: "", //预览图片
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
    this.init();
  },
  methods: {
    init() {
      this.musictype = this.songstype;
      console.log(this.musictype);
    },
    //----图片预览-----------------------
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    //------文件改变时
    change(file) {
      this.form.musicbackurl = file.name;
      console.log(file);
    },
    //--------文件上传成功时
    uploadsuccess(response, file, fileList) {
      this.form.musicbackurl = file.name;
      console.log("上传成功", response, file, file.name, fileList);
      //接着进行上传
      //调用另一个上传函数把form内容上传到后台.
      this.uploadAll()
    },
    //--------文件上传失败时
    uploaderror(file) {
      console.log(file);
      this.$message.error("上传失败");
    },
    //上传所有内容
    uploadAll() {
      var _this = this
      var data = this.$qs.stringify({
        music: JSON.stringify(this.form),
      });
      this.$axios({
        method: "post",
        url: "/Music/AddMusic",
        data: data,
      })
        .then(function (respone) {
          console.log(respone.data.tip);
          if(respone.data.tip===true){
            setTimeout(()=>{
              _this.$message.success("上传成功");
            },1000)
          }else if(respone.data.tip.indexOf('上传失败')>=0){
            setTimeout(()=>{
              _this.$message.warning("上传失败,该歌曲id已存在");
            },1000)
          }else{
            setTimeout(()=>{
              _this.$message.warning("上传失败");
            },1000)
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    //确定上传...
    sureupload() {
      this.$confirm("确定上传信息无误嘛, 是否继续?", "上传提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then((action) => {
          if (action === "confirm") {
            this.$refs.upload.submit(); //上传照片先...
          }
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消上传",
          });
        });
    },
    change1(newvalue) {
      this.form.musicDatetime = newvalue.musicDatetime;
    },
  },
  computed: {
    ...mapState("Songs", ["songstype", "getAddid"]),
  },
  watch: {
    songstype(newvalue) {
      this.musictype = newvalue;
      console.log('???????上传',this.musictype);
    },
    "form.musicsinger"(newvalue) {
      console.log(newvalue);
      this.$store.commit('Songs/getAddid','')//为了保持id实时更新
      if (newvalue != null && this.form.musicname) {
        myapi.getAddsongid(this.form.musicname, newvalue);
      }
    },
    getAddid(newvalue) {
      console.log('getid!!!!!')
      this.form.musicid = newvalue;
      this.form.musicoutlink =
        "https://music.163.com/outchain/player?type=2&id=" +
        newvalue +
        "&auto=1&height=66";
    },
    form:{
      handler: "change1",
      deep:true
    }
  },
};
</script>

<style>
@import "../assets/css/Upload.css";
@import "../assets/css/normal.css";
</style>

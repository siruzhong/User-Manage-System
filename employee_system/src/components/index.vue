<template>
  <div id="index">
    <!--走马灯组件-->
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="img in imgs" :key="item">
        <!--图片组件-->
        <el-image style="height: 600px" :src="img" :fit="contain"></el-image>
      </el-carousel-item>
    </el-carousel>
    <!--日历组件-->
    <el-calendar v-model="value"></el-calendar>
  </div>

</template>

<script>
  import img1 from "../assets/index_img/1.png"
  import img2 from "../assets/index_img/2.png"
  import img3 from "../assets/index_img/3.png"
  import img4 from "../assets/index_img/4.png"
  import img5 from "../assets/index_img/5.png"

  export default {
    name: "index",
    data() {
      return {
        imgs: [img1, img2, img3, img4, img5],
        value: new Date()
      }
    }
  }
</script>

<style scoped>
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
  }
</style>

<template>
  <div id="app">
    <el-container>
      <!--顶栏容器-->
      <el-header>
        <!--导航顶栏-->
        <el-menu
          :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"
          background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="/index">首页</el-menu-item>
          <el-menu-item index="/list">用户管理</el-menu-item>
        </el-menu>
      </el-header>
      <!--主要区域容器-->
      <el-main>
        <router-view/>
      </el-main>
    </el-container>

  </div>
</template>

<script>
  export default {
    name: 'App',
    data() {
      return {
        activeIndex: '/index'
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
        this.$router.push(key);//路由切换
      }
    }
  }
</script>

<style>
  #app {

  }
</style>

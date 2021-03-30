<template>
  <div id="list">
    <!--添加按钮-->
    <el-button style="margin-top: 10px" size="mini" type="success" @click="show=!show">添加</el-button>
    <!--员工信息列表-->
    <el-table :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
              style="width: 100%" height="300">
      <el-table-column label="编号" width="200" prop="id"/>
      <el-table-column label="姓名" width="200" prop="name"/>
      <el-table-column label="性别" width="200" prop="sex"/>
      <el-table-column label="出生日期" width="200" prop="date"/>
      <el-table-column label="家庭地址" width="200">
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>姓名: {{ scope.row.name }}</p>
            <p>住址: {{ scope.row.address }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">{{ scope.row.address }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column align="right">
        <template slot="header" slot-scope="scope">
          <el-input v-model="search" size="mini" style="width: auto" placeholder="输入姓名关键字搜索"/>
        </template>
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.$index,scope.row)">编辑</el-button>
          <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red"
            title="确定删除该用户吗？"
            @confirm="handleDelete(scope.$index, scope.row)">
            <el-button size="mini" type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--分页组件-->
    <el-row>
      <el-col :span="12" offset="12">
        <el-pagination
          style="margin: 15px 0px"
          background
          layout="prev, pager, next, jumper, total, sizes"
          :page-size="size"
          :current-page="now"
          :page-sizes="[2,4,6,8,10,12]"
          @current-change="findPage"
          @size-change="findSize"
          :total="total">
        </el-pagination>
      </el-col>
    </el-row>
    <!--Steps步骤条-->
    <el-steps :active="active" finish-status="success">
      <el-step title="添加用户"></el-step>
      <el-step title="修改用户"></el-step>
    </el-steps>
    <!--表单布局-->
    <el-row>
      <el-col :span="12">
        <!--添加表单-->
        <div style="margin-top: 0px; height: 400px;">
          <el-collapse-transition>
            <div v-show="show" class="transition-box">
              <el-form ref="form" :model="form" label-suffix=":" label-width="80px">
                <el-form-item label="用户编号">
                  <el-input v-model="form.id"></el-input>
                </el-form-item>
                <el-form-item label="用户姓名">
                  <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="用户性别">
                  <el-radio-group v-model="form.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="出生日期">
                  <el-date-picker value-format="yyyy-MM-dd" type="date" placeholder="选择日期" v-model="form.date"
                                  style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="详细地址">
                  <el-input type="textarea" v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit">添加用户</el-button>
                  <el-button @click="clearInfo">重置</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-collapse-transition>
        </div>
      </el-col>
      <el-col :span="12">
        <!--修改表单-->
        <div style="margin-top: 0px; height: 400px;">
          <el-collapse-transition>
            <div v-show="show2" class="transition-box">
              <el-form ref="form" :model="form" label-suffix=":" label-width="80px">
                <el-form-item label="用户编号">
                  <el-input v-model="form.id"></el-input>
                </el-form-item>
                <el-form-item label="修改姓名">
                  <el-input v-model="form.name"></el-input>
                </el-form-item>
                <el-form-item label="修改性别">
                  <el-radio-group v-model="form.sex">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="出生日期">
                  <el-date-picker type="date" placeholder="选择日期" v-model="form.date"
                                  style="width: 100%;"></el-date-picker>
                </el-form-item>
                <el-form-item label="详细地址">
                  <el-input type="textarea" v-model="form.address"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit2">修改用户</el-button>
                  <el-button @click="clearInfo">重置</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-collapse-transition>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  export default {
    name: "list",
    data() {
      return {
        tableData: [],
        search: '',
        show: false,
        show2: false,
        form: {
          id: '',
          name: '',
          sex: '男',
          date: '',
          address: ''
        },
        total: 0,
        now: 1,
        size: 4
      }
    },
    methods: {
      findPage(now_page) {//用来处理当前页的方法,page为当前页
        this.now = now_page;
        this.showAllUsers(now_page, this.size);
      },
      findSize(now_size) {//用来处理每页条数的方法,size为当前页条数
        this.size = now_size;
        this.showAllUsers(this.now, now_size);
      },
      clearInfo() {
        this.form = {sex: "男"};
      },
      handleEdit(index, row) {
        console.log(index, row);
        this.show2 = !this.show2;//展示编辑表单
        this.form = row;
      },
      handleDelete(index, row) {
        //发送axios异步请求到删除用户的controller
        this.axios.get("http://localhost:8082/user/deleteByID?id=" + row.id).then(res => {
          //判断执行状态
          if (res.data.status) {
            this.$message({
              message: res.data.msg,
              type: 'success'
            });
          } else
            this.$message.error(res.data.msg);
          this.showAllUsers();//重新渲染数据
        });
      },
      onSubmit() {
        //发送axios异步请求到添加用户的controller
        this.axios.post("http://localhost:8082/user/add", this.form).then(res => {
          //判断执行状态
          if (res.data.status) {
            this.$message({
              message: '恭喜你,' + res.data.msg,
              type: 'success'
            });
            // this.form = {sex: '男'};//清空表单信息
            this.show = false;//隐藏表单
          } else
            this.$message.error(res.data.msg);
          this.showAllUsers();//重新渲染数据
        });
      },
      onSubmit2() {
        //发送axios异步请求到修改用户的controller
        this.axios.post("http://localhost:8082/user/update", this.form).then(res => {
          //判断执行状态
          if (res.data.status) {
            this.$message({
              message: '恭喜你,' + res.data.msg,
              type: 'success'
            });
            this.show2 = false;//隐藏表单
          } else
            this.$message.error(res.data.msg);
          this.showAllUsers();//重新渲染数据
        });
      },
      showAllUsers(currentPage, pageSize) {  //异步请求显示所有数据
        currentPage = currentPage ? currentPage : this.now;
        pageSize = pageSize ? pageSize : this.size;
        this.axios.get("http://localhost:8082/user/findByPage?nowPage=" + currentPage + "&rows=" + pageSize).then(res => {
          this.tableData = res.data.users;
          this.total = res.data.total;
        });
      }
    },
    created() {
      this.showAllUsers();
    }
  }
</script>

<style scoped>
  .transition-box {
    margin-bottom: 10px;
    width: 100%;
    height: 400px;
    border-radius: 4px;
    background-color: #ffffff;
    text-align: left;
    color: #fff;
    padding: 0px 10px;
    box-sizing: border-box;
    margin-right: 20px;
  }
</style>

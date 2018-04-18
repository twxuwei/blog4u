<style type="text/css">
  .blog_table_footer {
    display: flex;
    box-sizing: content-box;
    padding-top: 10px;
    padding-bottom: 0px;
    margin-bottom: 0px;
    justify-content: space-between;
  }
</style>
<template>
  <div>
    <div style="display: flow;justify-content: flex-start">
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="loginClick">登录
      </el-button>
    </div>
    <div style="display: flex;justify-content: flex-start">
      <el-input
        placeholder="通过标题搜索该分类下的博客..."
        prefix-icon="el-icon-search"
        v-model="keywords" style="width: 400px" size="mini">
      </el-input>
      <el-button type="primary" icon="el-icon-search" size="mini" style="margin-left: 3px" @click="searchClick">搜索
      </el-button>
    </div>
    <!--<div style="width: 100%;height: 1px;background-color: #20a0ff;margin-top: 8px;margin-bottom: 0px"></div>-->
    <el-table
      ref="multipleTable"
      :data="articles"
      tooltip-effect="dark"
      style="width: 100%;overflow-x: hidden; overflow-y: hidden;"
      max-height="390"
      @selection-change="handleSelectionChange" v-loading="loading">
      <el-table-column
        type="selection"
        width="35" align="left" v-if="showEdit || showDelete">
      </el-table-column>
      <el-table-column
        label="标题"
        width="400" align="left">
        <template slot-scope="scope"><span style="color: #409eff;cursor: pointer" @click="itemClick(scope.row)">{{ scope.row.title}}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="最近编辑时间" width="140" align="left">
        <template slot-scope="scope">{{ scope.row.editTime | formatDateTime}}</template>
      </el-table-column>
      <el-table-column
        prop="nickname"
        label="作者"
        width="120" align="left">
      </el-table-column>
      <el-table-column
        prop="cateName"
        label="所属分类"
        width="120" align="left">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  import {putRequest} from '../utils/api'
  import VisitorDetail from '@/components/VisitorDetail'
  import {getRequest} from '../utils/api'
  //  import Vue from 'vue'
  //  var bus = new Vue()

  export default {
    data() {
      return {
        articles: [],
        loading: false,
        currentPage: 1,
        totalCount: -1,
        pageSize: 6,
        keywords: '',
        dustbinData: []
      }
    },
    mounted: function () {
      this.loading = true;
      this.loadBlogs(1, this.pageSize);
      var _this = this;
      window.bus.$on('blogTableReload', function () {
        _this.loading = true;
        _this.loadBlogs(_this.currentPage, _this.pageSize);
      })
    },
    methods: {
      searchClick() {
        this.loadBlogs(1, this.pageSize);
      },
      loginClick(){
        this.$router.replace({path: '/login'});
      },
      itemClick(row) {
        this.$router.push({path: '/visitorDetail', query: {aid: row.id}})
      },
      //翻页
      currentChange(currentPage) {
        this.currentPage = currentPage;
        this.loading = true;
        this.loadBlogs(currentPage, this.pageSize);
      },
      //获取博客列表
      loadBlogs(page, count) {
        var _this = this;
        var url = '';
        url = "/articles/view";
        getRequest(url).then(resp => {
          _this.loading = false;
          if (resp.status == 200) {
            var json = resp.data;
            _this.articles = json.result;
            _this.totalCount = json.result.length;
          } else {
            _this.$message({type: 'error', message: '数据加载失败1!'});
          }
        }, resp => {
          _this.loading = false;
          if (resp.status == 302) {
            _this.$router.replace({path: '/'});
            _this.$message({type: 'error', message: resp.response.data});
          } else {
            _this.$router.replace({path: '/'});
          }
        })
      },
      handleSelectionChange(val) {
        this.selItems = val;
      }
    },
    props: ['state', 'showEdit', 'showDelete', 'activeName']
  }
</script>

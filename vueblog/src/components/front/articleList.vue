<template lang="html">
<div class="article-list">
  <article class="block post wysiwyg" v-for="item in list">
    <div class="ui ribbon label red">
      <a href="">{{item.cateName}}</a>
    </div>
    <h2>{{item.title}}</h2>
    <p class="article-meta">{{item.nickname}}发布于 {{item.publishDate}} 阅读数{{item.pageView}}</p>
    <div class="abstract" v-html="item.summary">
    </div>
    <p class="more"><router-link :to="{ path:'/article', query:{articleId:item.id}}">阅读全文</router-link></p>
  </article>
  <div class="pages">
    <a href="javascript:;" @click="go(page-=1)" style="float: left;">上一页</a>
    <a href="javascript:;" @click="go(page+=1)" style="float: right;">下一页</a>
  </div>
</div>
</template>

<script>
import axios from 'axios'
export default {
  props:[
    'tagSelect'
  ],
  data () {
    return {
      list: [],
      page: 1,
      pageSize: 10,
      count: 0
    }
  },
  watch: {
    tagSelect () {
      this.getTagList()
    }
  },
  mounted () {
    this.getlist()
  },
  methods: {
    //获取博客列表
    getlist () {
      axios.get("/blog/articles/"+this.page+"/"+this.pageSize).then((result)=>{
        let res = result.data
        if (res.status == "OK") {
          if (res.result.count == 0) {
            this.page -= 1
            return
          } else {
            this.list = res.result
          }
        } else {
          this.list = []
        }
      })
    },
    //根据栏目名获取所有博客
    getTagList () {
      axios.get("/blog/articles/"+this.page+"/"+this.pageSize+"/"+this.tagSelect).then((result)=>{
        let res = result.data
        if (res.status == "OK") {
          if (res.result.count == 0) {
            this.page -= 1
            return
          } else {
            this.list = res.result
          }
        } else {
          this.list = []
        }
      })
    },
    go () {
      if (this.page<1) {
        this.page = 1
        return
      } else {
        this.getlist()
      }
    }
  }
}
</script>

<style media="screen">
.article-list {
  padding: 20px;
  background: #fff;
  border-radius: 10px;
  /*box-shadow: 1px 1px 2px rgba(0,0,0,0.08)*/
  box-shadow: 0 16px 24px 1px rgba(0, 0, 0, 0.14),
              0 6px 50px 1px rgba(0, 0, 0, 0.12),
              0 6px 10px -5px rgba(0, 0, 0, 0.2);
}
</style>

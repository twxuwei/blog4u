<template lang="html">
  <div class="about">
    <nav-header></nav-header>
    <div class="headpic">
      <div class="container headtitle full">
        <div class="title">
          <h1 href="/blog">Blog4U</h1>
        </div>
      </div>
    </div>
    <div class="container full">
      <div class="list">
        <div>
          <div class="full-content">
            <div class="messages-text post-content">
              <div style="height: 600px;">
                <h2 style="margin-bottom: 20px;">About</h2>
                <strong>爸爸的个人博客</strong>
                <br>
                <p style="margin-bottom: 20px;padding-top: 20px;">
                  github地址 : <a href="https://github.com/cylover945/blog4u">https://github.com/cylover945/blog4u</a>
                </p>
                <img src="/blog/articles/img?filename=totoro.jpg" alt="多多咯">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import NavHeader from '@/components/front/Header.vue'
import NavFooter from '@/components/front/Footer.vue'
export default {
  components: {
    NavHeader,
    NavFooter,
  }
}
</script>

<style lang="css">
.about {
  background-color: #fff;
}
@media (max-width: 800px) {
  .about img {
    width: 100%;
  }
}
</style>

<template>
  <div id="app" style="background-color: rgba(235, 235, 235, 0.08)">
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'app'
  }
</script>

<style>
  body, div, dl, dt, dd, ul, ol, li,
  h1, h2, h3, h4, h5, h6, pre, code,
  form, fieldset, legend, input,
  textarea, p, blockquote, th, td, button, span {
    margin: 0;
    padding: 0;
  }

  ul, li {
    list-style: none;
  }

  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>

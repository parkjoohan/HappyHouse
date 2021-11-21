<template>
  <div id="app">
    <Header />
    <router-view /><br />
    <Footer />
  </div>
</template>
<script>
import Header from "./components/common/Header.vue";
import Footer from "./components/common/Footer.vue";
export default {
  name: "App",
  components: {
    Header,
    Footer,
  },
};
</script>

<style>
a:hover {
  text-decoration: none;
  font-weight: bold;
}
a.router-link-exact-active {
  color: #42b983;
}
a.adver {
  display: inline-block;
  position: sticky;
  top: 30px;
  width: 80px;
  height: 80px;
  background: blue;
}
</style>

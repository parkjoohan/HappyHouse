<template>
  <div id="app">
    <Header />
    <Home />
    <Footer />
  </div>
</template>

<script>
import Header from "./components/common/Header.vue";
import Home from "./views/Home.vue";
import Footer from "./components/common/Footer.vue";

export default {
  name: "App",
  components: {
    Header,
    Home,
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
</style>

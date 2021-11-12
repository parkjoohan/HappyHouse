<template>
  <div id="app">
    <!-- 추가된 헤더 컴포넌트 사용 -->
    <Header />
    <Nav />
    <KakaoMap />
    <!-- 추가된 풋터 컴포넌트 사용 -->
    <Footer />
  </div>
</template>

<script>
import Header from "./components/common/Header"; //import 헤더 추가
import Nav from "./components/common/Nav"; //import 헤더 추가
import KakaoMap from "./components/KakaoMap";
import Footer from "./components/common/Footer"; //import 풋터 추가

export default {
  name: "App",
  components: {
    Header, //헤더 컴포넌트 추가
    Nav,
    KakaoMap,
    Footer, //풋터 컴포넌트 추가
  },
};
</script>
<style></style>

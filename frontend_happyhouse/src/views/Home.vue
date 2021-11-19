<template>
  <div id="app">
    <Header />

    <div class="bg">
      <video muted autoplay loop>
        <source src="@/assets/City.mp4" type="video/mp4" />
      </video>
      <div class="text">
        <p style="font-size: 30px">
          Nothing is more relaxing than chilling at home.<br />
          There is nothing like staying at home for real comfort.
        </p>
      </div>
    </div>

    <!-- <router-view /><br /> -->

    <div class="container">
      <section class="item item-a">
        <!-- <h1>Section a</h1>
        <p>hello woreld</p> -->
        <img src="@/assets/seoul.jpg" width="100%" alt="" />
      </section>
      <section class="item item-b" width="30%" align="center">
        <h1>서울</h1>
        <h4>I · SEOUL · U</h4>
      </section>
    </div>

    <div class="container">
      <section class="item item-b" width="30%" align="center">
        <h1>광주</h1>
        <h4>SSAFY의 중심지!!</h4>
      </section>
      <section class="item item-a">
        <!-- <h1>Section a</h1>
        <p>hello woreld</p> -->
        <img src="@/assets/gwangju.jpg" width="100%" alt="" />
      </section>
    </div>

    <div class="container">
      <section class="item item-a">
        <!-- <h1>Section a</h1>
        <p>hello woreld</p> -->
        <img src="@/assets/pusan.jpg" width="100%" alt="" />
      </section>
      <section class="item item-b" width="30%" align="center">
        <h1>부산</h1>
        <h4>바다와 먹거리의 중심!</h4>
      </section>
    </div>

    <Footer />
  </div>
</template>

<script>
export default {};
</script>

<style>
body {
  padding: 0px;
  margin: 0px;
}
.bg {
  width: 100%;
  height: 500px;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
}
video {
  width: 100%;
}
.text {
  position: absolute;
  width: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.text p {
  text-align: center;
  font-size: 60px;
  color: #ffffff;
}
.item {
  display: inline-block;
  padding: 2%;
}
.item-a {
  width: 67%;
}
.item-b {
  width: 33%;
}
</style>

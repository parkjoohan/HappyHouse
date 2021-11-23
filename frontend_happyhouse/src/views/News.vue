<template lang="">
  <div class="container">
    <div class="bg">
      <video muted autoplay loop>
        <source src="@/assets/City.mp4" type="video/mp4" />
      </video>
      <div class="text">
        <p style="color: white">
          <b-icon icon="cloud-arrow-up"></b-icon> Board Service
          <b-icon icon="cloud-download"></b-icon>
        </p>
      </div>
    </div>
    <div id="news_title">오늘의 뉴스</div>
    <router-view></router-view>
  </div>
</template>
<script>
export default {
  name: "news",
};
</script>
<style scoped>
#news_title {
  font-size: 28px;
  font-weight: bold;
  font-family: Verdana, Geneva, Tahoma, sans-serif;
  margin: 20px;
}
.bg {
  width: 100%;
  height: 200px;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
}
.video {
  width: 100%;
}
.text {
  position: absolute;
  width: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>

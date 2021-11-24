<template>
  <div>
    <div class="bg">
      <video muted autoplay loop>
        <source src="@/assets/City.mp4" type="video/mp4" />
      </video>
      <div class="text" style="font-family: '함초롬돋움'">
        <p style="color: white">
          <b-icon icon="cloud-arrow-up"></b-icon><b> Board Service</b>
          <b-icon icon="cloud-download"></b-icon>
        </p>
      </div>
    </div>
    <b-container class="bv-example-row mt-3 text-center height:600px;">
      <router-view></router-view>
    </b-container>
  </div>
</template>
<script>
export default {
  name: "Board",
};
</script>
<style>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
.bg {
  width: 100%;
  height: 200px;
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
  font-size: 30px;
  color: #ffffff;
}
</style>

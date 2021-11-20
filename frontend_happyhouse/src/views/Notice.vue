<template>
  <b-container class="bv-example-row mt-3 text-center">
    <div class="bg">
      <video muted autoplay loop>
        <source src="@/assets/City.mp4" type="video/mp4" />
      </video>
      <div class="text">
        <h3 style="color: white">
          <b-icon icon="house-fill"></b-icon> Board Service
          <b-icon icon="house-fill"></b-icon>
        </h3>
      </div>
    </div>
    <br />

    <router-view></router-view>
  </b-container>
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
</style>

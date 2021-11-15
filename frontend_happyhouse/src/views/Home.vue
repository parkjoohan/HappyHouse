<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h2 class="underline-steelblue">
      <b-icon icon="house"></b-icon> MY SWEET HOME
      <b-icon icon="house"></b-icon>
    </h2>

    <div class="carousel-item">
      <img src="..." alt="..." />
      <div class="carousel-caption d-none d-md-block">
        <h5>...</h5>
        <p>...</p>
      </div>
    </div>
  </b-container>
</template>

<script>
export default {
  name: "Main",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>

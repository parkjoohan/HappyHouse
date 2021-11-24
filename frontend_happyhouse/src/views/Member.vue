<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
      <router-view></router-view>
    </b-container>
  </div>
</template>

<script>
export default {
  name: "Member",
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
video {
  width: 50%;
}
.container,
.container-sm,
.container-md,
.container-lg,
.container-xl {
  max-width: 1140px;
  height: 1000px;
}
</style>

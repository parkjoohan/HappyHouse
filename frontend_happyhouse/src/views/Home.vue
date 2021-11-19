<template>
  <b-container class="bv-example-row mt-3">
    <b-carousel
      id="carousel-fade"
      style="text-shadow: 0px 0px 2px #000"
      fade
      indicators
      img-width="1024"
      img-height="480"
    >
      <!-- Text slides with image -->
      <b-carousel-slide
        caption="서울"
        text="I · SEOUL · U"
        :img-src="require(`@/assets/seoul.jpg`)"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="광주"
        text="SSAFY의 중심지!!"
        :img-src="require(`@/assets/gwangju.jpg`)"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="부산"
        text="바다와 먹거리의 중심!"
        :img-src="require(`@/assets/pusan.jpg`)"
      ></b-carousel-slide>
    </b-carousel>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
};
</script>

<style></style>

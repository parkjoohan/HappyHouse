<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >
      <!-- Text slides with image -->
      <b-carousel-slide
        caption="서울"
        text="I · SEOUL · U"
        :img-src="require(`@/assets/1.jpg`)"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="광주"
        text="SSAFY의 중심지!!"
        :img-src="require(`@/assets/2.jpg`)"
      ></b-carousel-slide>
      <b-carousel-slide
        caption="부산"
        text="바다와 먹거리의 중심!"
        :img-src="require(`@/assets/3.jpg`)"
      ></b-carousel-slide>
    </b-carousel>
  </div>
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

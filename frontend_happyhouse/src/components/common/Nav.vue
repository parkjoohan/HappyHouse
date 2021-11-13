<template>
  <img class="slide-image" :src="getImgUrl(images[0])" />
</template>

<script>
export default {
  data() {
    return {
      images: ["/assets/1.jpg", "/assets/2.jpg", "/assets/3.jpg"],
    };
  },
  watch: {
    getImgUrl() {},
  },
  mounted() {
    window.setInterval(() => {
      this.slide();
    }, 5000);
  },
  methods: {
    getImgUrl(im) {
      return require("../assets" + im);
    },
    slide() {
      let first = this.images.shift();
      this.images = this.images.concat(first);
    },
  },
};
</script>

<style></style>

<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
      <div class="bg">
        <video muted autoplay loop>
          <source src="@/assets/City.mp4" type="video/mp4" />
        </video>
        <div class="text">
          <h3 style="color: white">
            <b-icon icon="house-fill"></b-icon> House Service
            <b-icon icon="house-fill"></b-icon>
          </h3>
        </div>
      </div>
      <br />

      <kakao-map></kakao-map><br />
      <b-row>
        <b-col>
          <house-search-bar></house-search-bar>
        </b-col>
      </b-row>
      <b-row>
        <b-col cols="6" align="left">
          <house-list />
        </b-col>
        <b-col cols="6">
          <house-detail />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import KakaoMap from "@/components/map/KakaoMap.vue";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";

export default {
  name: "House",
  components: {
    KakaoMap,
    HouseSearchBar,
    HouseList,
    HouseDetail,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}
</style>

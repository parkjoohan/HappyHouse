<template>
  <div>
    <div class="bg">
      <video muted autoplay loop>
        <source src="@/assets/City.mp4" type="video/mp4" />
      </video>
      <div class="text">
        <p style="color: white">
          <b-icon icon="building"></b-icon> 부동산 서비스
          <b-icon icon="house-fill"></b-icon>
        </p>
      </div>
    </div>
    <b-container class="bv-example-row mt-3 text-center">
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
      <b-row>
        <b-col cols="4" align="center">
          <h4>{{ dongName }} 거주인구요약정보</h4>
          <pie-chart :chart-data="chartData"></pie-chart>
        </b-col>
        <b-col cols="4" align="center">
          <h4>{{ dongName }} 거주인구요약정보</h4>
          <pie-chart :chart-data="chartData"></pie-chart>
        </b-col>
        <b-col cols="4" align="center">
          <h4>{{ dongName }} 거주인구요약정보</h4>
          <pie-chart :chart-data="chartData"></pie-chart>
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
import PieChart from "@/components/house/js/PieChart.js";

import { mapGetters } from "vuex";
const houseStore = "houseStore";

import { API_BASE_URL, POPULATION_URL } from "@/config";
import axios from "axios";

export default {
  name: "House",
  components: {
    KakaoMap,
    HouseSearchBar,
    HouseList,
    HouseDetail,
    PieChart,
  },
  data() {
    return {
      chartData: null,
      dongName: null,
      hCode: [],
      pInfo: null,
    };
  },
  mounted() {},
  computed: {
    ...mapGetters(houseStore, ["dongChange"]),
  },
  watch: {
    dongChange() {
      //houseStorage의 dongChange이 변경되면 차트 바꾸기
      console.log("검색(법정동)", this.dongChange);
      this.makeChart();
    },
  },
  methods: {
    async makeChart() {
      this.hCode = await axios.get(API_BASE_URL + "/map/convert", {
        params: { dong: this.dongChange },
      });
      console.log("행정동 코드", this.hCode.data);

      let response = await axios.get(POPULATION_URL, {
        params: {
          //https://sgis.kostat.go.kr/developer/html/develop/dvp.html
          //링크 > 실직적 예제 > 인증 루프코딩
          accessToken: "544cff05-2d7f-4cc0-ab6c-912af0438f72", //4시간 마다 갱신ㅠㅠ
          adm_cd: this.hCode.data[0],
        },
      });
      this.pInfo = response.data.result[0]; //동 인구 정보
      this.dongName = this.pInfo.adm_nm;
      this.drowChart();
    },
    drowChart() {
      let newArray = [];
      newArray.push(this.pInfo.teenage_less_than_cnt);
      newArray.push(this.pInfo.teenage_cnt);
      newArray.push(this.pInfo.twenty_cnt);
      newArray.push(this.pInfo.thirty_cnt);
      newArray.push(this.pInfo.forty_cnt);
      newArray.push(this.pInfo.fifty_cnt);
      newArray.push(this.pInfo.sixty_cnt);
      newArray.push(this.pInfo.seventy_more_than_cnt);

      //console.log(newArray);
      this.chartData = {
        hoverBackgroundColor: "red",
        borderWidth: "10px",
        hoverBorderWidth: "10px",
        labels: [
          "10대 미만",
          "10대",
          "20대",
          "30대",
          "40대",
          "50대",
          "60대",
          "70대 이상",
        ],
        datasets: [
          {
            label: "Data One",
            backgroundColor: [
              "#F072A7",
              "#FF9E3D",
              "#FCDF4F ",
              "#8ED058",
              "#716FFF",
              "#41B883",
              "#E46651",
              "#5CA4F6",
            ],
            data: newArray,
          },
        ],
      };
    },
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

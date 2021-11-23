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
    </b-container>
    <b-container class="bv-example-row mt-5 text-center">
      <b-row class="mb-2 text-center">
        <b-col
          ><h2>{{ dongName }}</h2></b-col
        >
      </b-row>
      <b-row class="mt-4 mb-2 text-center">
        <b-col cols="4">
          <h4>거주인구 요약정보</h4>
          <pie-chart1 :chart-data="chartData1"></pie-chart1>
        </b-col>
        <b-col cols="4">
          <h4>거처종류 요약정보</h4>
          <pie-chart2 :chart-data="chartData2"></pie-chart2>
        </b-col>
        <b-col cols="4">
          <h4>점유형태 요약정보</h4>
          <pie-chart3 :chart-data="chartData3"></pie-chart3>
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
import PieChart1 from "@/components/house/js/PieChart.js";
import PieChart2 from "@/components/house/js/PieChart2.js";
import PieChart3 from "@/components/house/js/PieChart3.js";

import { mapGetters } from "vuex";
const houseStore = "houseStore";

import { API_BASE_URL, POPULATION_URL, HOUSE_URL, OCPTN_URL } from "@/config";
import axios from "axios";

export default {
  name: "House",
  components: {
    KakaoMap,
    HouseSearchBar,
    HouseList,
    HouseDetail,
    PieChart1,
    PieChart2,
    PieChart3,
  },
  data() {
    return {
      dongName: null,
      hCode: [],
      chartData1: null,
      chartData2: null,
      chartData3: null,
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

      let pplSummary = await axios.get(POPULATION_URL, {
        params: {
          //https://sgis.kostat.go.kr/developer/html/develop/dvp.html
          //링크 > 실직적 예제 > 인증 루프코딩
          accessToken: "b83910b2-5c7e-4afb-8a1c-658e92f9f639", //4시간 마다 갱신ㅠㅠ
          adm_cd: this.hCode.data[0],
        },
      });
      console.log("거주인구", pplSummary.data.result);
      this.dongName = pplSummary.data.result[0].adm_nm;
      this.drowChart1(pplSummary.data.result[0]);

      let houseSummary = await axios.get(HOUSE_URL, {
        params: {
          accessToken: "b83910b2-5c7e-4afb-8a1c-658e92f9f639",
          adm_cd: this.hCode.data[0],
        },
      });
      console.log("거처종류", houseSummary.data.result);
      this.drowChart2(houseSummary.data.result[0]);

      let ocptnSummary = await axios.get(OCPTN_URL, {
        params: {
          accessToken: "b83910b2-5c7e-4afb-8a1c-658e92f9f639",
          adm_cd: this.hCode.data[0],
        },
      });
      console.log("점유형태", ocptnSummary.data.result);
      this.drowChart3(ocptnSummary.data.result[0]);
    },
    drowChart1(data) {
      let newArray = [
        data.teenage_less_than_cnt,
        data.teenage_cnt,
        data.twenty_cnt,
        data.thirty_cnt,
        data.forty_cnt,
        data.fifty_cnt,
        data.sixty_cnt,
        data.seventy_more_than_cnt,
      ];

      this.chartData1 = {
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
    drowChart2(data) {
      let newArray = [
        data.apart_per,
        data.row_house_per,
        data.detach_house_per,
        data.dom_soc_fac_per,
        data.officetel_per,
        data.etc_per,
      ];

      this.chartData2 = {
        labels: [
          "아파트",
          "연립/다세대",
          "단독주택",
          "기숙사 및 사회시설",
          "오피스텔",
          "기타",
        ],
        datasets: [
          {
            backgroundColor: [
              "#F072A7",
              "#FF9E3D",
              "#FCDF4F ",
              "#8ED058",
              "#41B883",
              "#716FFF",
            ],
            data: newArray,
          },
        ],
      };
    },
    drowChart3(data) {
      let newArray = [data.self_per, data.lease_per, data.mrp_per];

      this.chartData3 = {
        labels: ["자가비율", "전세비율", "월세비율"],
        datasets: [
          {
            backgroundColor: ["#FCDF4F ", "#8ED058", "#716FFF", "#5CA4F6"],
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

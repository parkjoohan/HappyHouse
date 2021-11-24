<template>
  <div>
    <b-row class="mt-4 mb-2 text-center">
      <b-col cols="2">
        <b-form-select
          v-model="year"
          :options="years"
          @change="searchApt"
        ></b-form-select>
      </b-col>
      <b-col cols="2">
        <b-form-select
          v-model="month"
          :options="months"
          @change="searchApt"
        ></b-form-select> </b-col
      ><b> {{ year }}년 {{ month }}월 선택</b>
      <b-col cols="8"> </b-col>
    </b-row>
    <b-row class="mt-2 mb-4 text-center">
      <b-col class="sm-3">
        <b-form-select
          v-model="sidoCode"
          :options="sidos"
          @change="gugunList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="gugunCode"
          :options="guguns"
          @change="dongList"
        ></b-form-select>
      </b-col>
      <b-col class="sm-3">
        <b-form-select
          v-model="dongCode"
          :options="dongs"
          @change="searchApt"
        ></b-form-select>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

/*
  namespaced: true를 사용했기 때문에 선언해줍니다.
  index.js 에서 modules 객체의 '키' 이름입니다.

modules: {
    키: 값
    memberStore: memberStore,
    houseStore: houseStore
  }
*/
const houseStore = "houseStore";

export default {
  name: "HouseSearchBar",
  data() {
    return {
      year: "2021",
      month: "11",
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
      years: [
        { value: "2021", text: "2021년" },
        { value: "2020", text: "2020년" },
        { value: "2019", text: "2019년" },
        { value: "2018", text: "2018년" },
        { value: "2017", text: "2017년" },
        { value: "2016", text: "2016년" },
        { value: "2015", text: "2015년" },
        { value: "2014", text: "2014년" },
        { value: "2013", text: "2013년" },
        { value: "2012", text: "2012년" },
        { value: "2011", text: "2011년" },
        { value: "2010", text: "2010년" },
      ],
      months: [
        { value: "01", text: "1월" },
        { value: "02", text: "2월" },
        { value: "03", text: "3월" },
        { value: "04", text: "4월" },
        { value: "05", text: "5월" },
        { value: "06", text: "6월" },
        { value: "07", text: "7월" },
        { value: "08", text: "8월" },
        { value: "09", text: "9월" },
        { value: "10", text: "10월" },
        { value: "11", text: "11월" },
        { value: "12", text: "12월" },
      ],
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns", "dongs"]),
  },
  created() {
    this.CLEAR_HOUSE_LIST();
    this.CLEAR_DETAIL_HOUSE();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, [
      "getSido",
      "getGugun",
      "getDong",
      "getHouseList",
    ]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "CLEAR_HOUSE_LIST",
      "CLEAR_DETAIL_HOUSE",
    ]),
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },
    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) this.getDong(this.gugunCode);
    },
    searchApt() {
      this.CLEAR_HOUSE_LIST();
      this.CLEAR_DETAIL_HOUSE();
      if (this.dongCode) {
        this.getHouseList({
          dongCode: this.dongCode,
          dealYMD: this.year + this.month,
        });
      }
    },
  },
};
</script>

<style></style>

<template>
  <b-container v-if="house" class="bv-example-row">
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          ><b>{{ house.아파트 }} </b></b-alert
        >
      </b-col>
    </b-row>
    <b-row class="mb-2 mt-1">
      <b-col
        ><b-img
          :src="require('@/assets/apt.png')"
          width="50px"
          height="50px"
          fluid-grow
        ></b-img
      ></b-col>
    </b-row>
    <table class="info-table">
      <thead>
        <tr>
          <th scope="cols">구분</th>
          <th scope="cols">정보</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row" class="even">거래일자</th>
          <td class="even">
            {{ house.년 }}년 {{ house.월 }}월 {{ house.일 }}일
          </td>
        </tr>
        <tr>
          <th scope="row">주소</th>
          <td>{{ house.법정동 }} {{ house.지번 }}</td>
        </tr>
        <tr>
          <th scope="row" class="even">층수</th>
          <td class="even">{{ house.층 }}층</td>
        </tr>
        <tr>
          <th scope="row">전용면적</th>
          <td>{{ house.전용면적 }}m<sup>2</sup></td>
        </tr>
        <tr>
          <th scope="row" class="even">거래금액</th>
          <td class="even">
            {{ (parseInt(house.거래금액.replace(",", "")) * 10000) | price }}원
          </td>
        </tr>
      </tbody>
    </table>
    <!-- <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >일련번호 : {{ house.일련번호 }}</b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="primary"
          >거래일자 : {{ house.년 }}년 {{ house.월 }}월 {{ house.일 }}일
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="info"
          >주소 : {{ house.법정동 }} {{ house.지번 }}
        </b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="warning">층수 : {{ house.층 }}층</b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="secondary"
          >전용면적 : {{ house.전용면적 }}m<sup>2</sup></b-alert
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-alert show variant="danger"
          >거래금액 :
          {{
            (parseInt(house.거래금액.replace(",", "")) * 10000) | price
          }}원</b-alert
        >
      </b-col>
    </b-row> -->
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseDetail",
  computed: {
    ...mapState(houseStore, ["house"]),
    // house() {
    //   return this.$store.state.house;
    // },
  },
  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style>
#field {
  margin-top: 2%;
  margin-bottom: 5%;
}
img.img-fluid {
  max-width: 50%;
  height: auto;
}
table.info-table {
  border-collapse: collapse;
  text-align: center;
  line-height: 3;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  width: 100%;
  height: 30%;
}
table.info-table thead th {
  width: 45%;
  padding: 0px;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #6c757d;
  margin: 20px 10px;
}
table.info-table .even {
  background: #c5c5c5;
}
</style>

<template>
  <b-tr>
    <b-td>{{ articleNo }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'NoticeView', params: { no: articleNo } }">{{
        subject
      }}</router-link>
    </b-th>
    <b-td>{{ userId }}</b-td>
    <b-td>{{ regTime }}</b-td>
    <!--<b-td>{{ changeDateFormat }}</b-td>-->
  </b-tr>
</template>

<script>
//import moment from "moment";

export default {
  name: "BoardListRow",
  props: {
    articleNo: Number,
    subject: String,
    userId: String,
    regTime: String,
  },
  computed: {
    // changeDateFormat() {
    //   return moment(new Date(this.regTime)).format("YY.MM.DD hh:mm:ss");
    // },
  },
};
</script>

<style></style>

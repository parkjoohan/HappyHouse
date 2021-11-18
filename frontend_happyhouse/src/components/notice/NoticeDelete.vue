<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";

export default {
  name: "NoticeDelete",
  created() {
    axios.defaults.headers.common["access-token"] =
      this.$store.state.accessToken;
    axios
      .delete(`http://localhost:9999/notice/${this.$route.params.no}`)
      .then(({ data }) => {
        let msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          msg = "삭제가 완료되었습니다.";
        }
        alert(msg);
        this.$router.push({ name: "NoticeList" });
      })
      .catch(() => {
        alert("접근 불가");
        this.$router.push({ name: "NoticeList" });
      });
  },
};
</script>

<style></style>

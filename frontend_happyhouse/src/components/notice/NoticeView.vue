<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show
          ><h3><b>글보기</b></h3></b-alert
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="secondary" @click="listArticle"
          ><b-icon icon="clipboard"></b-icon><b> 목록</b></b-button
        >
      </b-col>
      <b-col
        class="text-right"
        v-if="this.userInfo.userId === this.article.userId"
      >
        <b-button variant="info" @click="moveModifyArticle" class="m-1">
          <b-icon icon="scissors"></b-icon><b> 글수정</b>
        </b-button>
        <b-button class="m-1" variant="danger" @click="deleteArticle"
          ><b-icon icon="trash"></b-icon><b> 글삭제</b></b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.articleNo}.
          ${article.subject}</h3><div><h6>${article.userId}</div><div>${article.regTime}</h6></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-left">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
//import moment from "moment";
import { mapState } from "vuex";
import { getArticle, deleteArticle } from "@/api/notice";

const memberStore = "memberStore";

export default {
  name: "NoticeView",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    getArticle(
      this.$route.params.no,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("에러발생!!", error);
      }
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "NoticeList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "NoticeUpdate",
        params: { no: this.article.articleNo },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        deleteArticle(this.article.articleNo, () => {
          alert("삭제가 완료됐습니다.");
          this.$router.push({ name: "NoticeList" });
        });
      }
    },
  },
};
</script>

<style></style>

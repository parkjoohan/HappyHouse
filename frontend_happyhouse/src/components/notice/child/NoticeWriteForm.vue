<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="writer-group" label="작성자:" label-for="writer">
          <b-form-input
            id="writer"
            :disabled="isWriter"
            v-model="article.userId"
            type="text"
            required
            placeholder="작성자 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group
          id="subject-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            required
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <b-button
          type="submit"
          variant="primary"
          class="m-1"
          v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else
          >글수정</b-button
        >
        <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { mapState } from "vuex";
import { writeArticle, getArticle, modifyArticle } from "@/api/notice";

const memberStore = "memberStore";

export default {
  name: "NoticeWriteForm",
  data() {
    return {
      article: {
        articleNo: 0,
        userId: "",
        subject: "",
        content: "",
        regTime: "",
      },
      isWriter: true,
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      getArticle(
        this.$route.params.no,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
    this.article.userId = this.userInfo.userId;
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();
      this.article.subject = "";
      this.article.content = "";
    },
    registArticle() {
      writeArticle(
        {
          userId: this.article.userId,
          subject: this.article.subject,
          content: this.article.content,
        },
        ({ data }) => {
          let msg = "글 등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "글 등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      modifyArticle(
        {
          articleNo: this.article.articleNo,
          userId: this.article.userId,
          subject: this.article.subject,
          content: this.article.content,
        },
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.$router.push({ name: "NoticeList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "NoticeList" });
    },
  },
};
</script>

<style></style>

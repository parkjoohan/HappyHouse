<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <!-- <form id="searchform" class="form-inline">
          <select id="key" name="key" class="form-control">
            <option value="userid">아이디</option>
            <option value="articleno">글번호</option>
            <option value="subject">제목</option>
          </select>

          <input
            type="text"
            v-model="word"
            class="ml-1 form-control"
            id="sword"
            name="word"
            value="${word}"
          />
          <button
            type="button"
            id="searchBtn"
            class="ml-1 btn btn-outline-primary"
          >
            검색
          </button>
        </form> -->

        <b-nav-form>
          <b-form-select v-model="noticeParameter.key" :options="keys">
          </b-form-select>
          <b-form-input
            v-model="noticeParameter.word"
            class="mr-sm-2"
          ></b-form-input>
          <b-button
            variant="outline-primary"
            class="my-2 my-sm-0"
            @click="searchBtn"
            ><b-icon icon="search"></b-icon><b> 검색</b></b-button
          >
        </b-nav-form>
      </b-col>
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          ><b-icon icon="vector-pen"></b-icon><b> 글 쓰기</b></b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col id="여기야">
        <b-table
          id="noticeTable"
          class="text-center"
          :items="noticeList"
          :per-page="perPage"
          :current-page="currentPage"
          :fields="fields"
          @row-clicked="rowClick"
          hover
        >
          <template #cell(subject)="data">
            <span v-html="data.value"></span>
          </template>
        </b-table>
        <b-pagination
          v-model="currentPage"
          :per-page="perPage"
          pills
          :total-rows="rows"
          :limit="6"
          align="center"
          aria-controls="noticeTable"
        ></b-pagination>
      </b-col>
      <!-- <b-col v-else class="text-center">작성된 글이 없습니다.</b-col> -->
    </b-row>
  </b-container>
</template>

<script>
//import NoticeListRow from "@/components/notice/child/NoticeListRow";
import { listArticle } from "@/api/notice.js";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "NoticeList",
  components: {
    //NoticeListRow,
  },
  data() {
    return {
      noticeList: [],
      currentPage: 1,
      perPage: 10,
      fields: [
        { key: "articleNo", label: "글번호", tdClass: "articleNo" },
        { key: "subject", label: "제목", tdClass: "subject" },
        { key: "userId", label: "작성자", tdClass: "userId" },
        { key: "regTime", label: "등록일", tdClass: "regTime" },
      ],
      noticeParameter: {
        key: null,
        word: null,
      },

      keys: [
        { value: "userid", text: "작성자" },
        { value: "subject", text: "제목" },
        { value: "articleno", text: "글번호" },
      ],
    };
  },
  created() {
    listArticle(
      this.noticeParameter,
      (response) => {
        this.noticeList = response.data;
        //console.log(this.noticeList);
      },
      (error) => {
        console.log(error);
      }
    );
    this.currentPage = this.getCurrentPage;
  },
  computed: {
    ...mapGetters("noticeStore", ["getCurrentPage"]),
    rows() {
      return this.noticeList.length;
    },
  },
  methods: {
    ...mapActions("noticeStore", ["setCurrentPage"]),
    moveWrite() {
      this.$router.push({ name: "NoticeWrite" });
    },
    rowClick(item) {
      this.setCurrentPage(this.currentPage);
      this.$router.push({ name: "NoticeView", params: { no: item.articleNo } });
    },
    searchBtn() {
      listArticle(
        this.noticeParameter,
        (response) => {
          console.log(response);
          this.noticeList = response.data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.currentPage = this.getCurrentPage;
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
#여기야 {
  height: 600px;
}
</style>

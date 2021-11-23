<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()"
          ><b-icon icon="vector-pen"></b-icon> 글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
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
    };
  },
  created() {
    listArticle(
      (response) => {
        this.noticeList = response.data;
        console.log(this.noticeList);
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
    ...mapActions("noticeStore", ["setNotice", "setCurrentPage"]),
    moveWrite() {
      this.$router.push({ name: "NoticeWrite" });
    },
    rowClick(item) {
      this.setCurrentPage(this.currentPage);
      this.$router.push({ name: "NoticeView", params: { no: item.articleNo } });
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
</style>

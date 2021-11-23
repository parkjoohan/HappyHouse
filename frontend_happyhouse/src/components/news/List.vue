<template>
  <div class="container">
    <b-navbar>
      <b-navbar-nav>
        <b-nav-form>
          <b-form-input v-model="word" class="mr-sm-2"></b-form-input>
          <b-button variant="success" class="my-2 my-sm-0" @click="searchBtn"
            ><b-icon icon="search"></b-icon> 검색</b-button
          >
        </b-nav-form>
      </b-navbar-nav>
      <b-navbar-nav class="ml-auto">
        <b-nav-form>
          <b-form-group label="정렬:" label-for="sort">
            <b-form-select
              class="ml-3"
              id="sort"
              v-model="sortkey"
              :options="options"
              @change="searchBtn"
            ></b-form-select>
          </b-form-group>
        </b-nav-form>
      </b-navbar-nav>
    </b-navbar>
    <b-table
      id="listTable"
      class="text-center"
      :items="newsList"
      :per-page="perPage"
      :current-page="currentPage"
      :fields="fields"
      @row-clicked="rowClick"
      hover
    >
      <template #cell(title)="data">
        <span v-html="data.value"></span>
      </template>
    </b-table>
    <b-pagination
      v-model="currentPage"
      :per-page="perPage"
      :total-rows="dispaly"
      :limit="6"
      align="center"
      aria-controls="listTable"
    ></b-pagination>
  </div>
</template>

<script>
import http from "@/util/http-common";
import moment from "moment";
import { mapActions, mapGetters } from "vuex";

export default {
  name: "newslist",
  data() {
    return {
      currentPage: 1,
      perPage: 10,
      word: "",
      sortkey: "date",
      options: [
        { value: "date", text: "날짜" },
        { value: "sim", text: "유사도" },
      ],
      fields: [
        { key: "title", label: "제목", tdClass: "news_title" },
        { key: "pubDate", label: "등록일", tdClass: "pubDate" },
      ],
      newsList: [],
      dispaly: 0,
    };
  },
  created() {
    this.getNewsList(`/news/list/부동산/` + this.sortkey);
    this.currentPage = this.getCurrentPage;
  },
  computed: {
    ...mapGetters("news", ["getCurrentPage"]),
  },
  methods: {
    ...mapActions("news", ["setNews", "setCurrentPage"]),
    searchBtn() {
      if (this.word == "") {
        let path = `/news/list/부동산/${this.sortkey}`;
        this.getNewsList(path);
      } else {
        let path = `/news/list/${this.word}/${this.sortkey}`;
        this.getNewsList(path);
      }
    },
    rowClick(item) {
      this.setNews(item);
      this.setCurrentPage(this.currentPage);
      this.$router.push("read");
    },
    getNewsList(path) {
      console.log(path);
      http
        .get(path)
        .then(({ data }) => {
          data.items.forEach((element) => {
            element.pubDate = moment(new Date(element.pubDate)).format(
              "YYYY.MM.DD"
            );
          });
          this.newsList = data.items;
          this.dispaly = data.display;
        })
        .catch(() => {
          alert("에러가 발생했습니다.");
        });
    },
  },
};
</script>

<style scoped>
#listTable {
  border-top: 1px solid silver;
  border-bottom: 1px solid silver;
  margin-bottom: 50px;
}
</style>

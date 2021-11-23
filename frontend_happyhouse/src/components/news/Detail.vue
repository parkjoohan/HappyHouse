<template>
  <div class="container">
    <b-card class="mx-auto" max-width="800">
      <b-card-text>
        <p class="display-1 text--primary" v-html="news.title"></p>
        <p>{{ news.pubDate }}</p>
        <div class="subtitle-1 text--primary" v-html="news.description"></div>
      </b-card-text>
      <b-card-action>
        <div>
          <b-btn class="ma-2" color="success" @click="moveLink">
            네이버 뉴스 본문
          </b-btn>
          <b-btn class="ma-2" color="info" @click="moveOriginaLlink">
            언론사 본문
          </b-btn>
        </div>
      </b-card-action>
    </b-card>
    <div class="text-right">
      <b-btn class="ma-2" color="secondary" @click="moveList"> 목록 </b-btn>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "detail",
  computed: {
    ...mapState({
      news: (state) => state.news.news,
    }),
  },
  methods: {
    moveOriginaLlink() {
      window.open(this.news.originallink, "_blank");
    },
    moveLink() {
      window.open(this.news.link, "_blank");
    },
    moveList() {
      this.$router.push("list");
    },
  },
};
</script>

<style scoped></style>

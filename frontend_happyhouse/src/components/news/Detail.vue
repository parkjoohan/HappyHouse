<template>
  <div class="container">
    <b-card class="mx-auto" max-width="800">
      <b-card-text>
        <p class="display-4 text--primary" v-html="news.title"></p>
        <p>{{ news.pubDate }}</p>
        <div class="subtitle-1 text--primary" v-html="news.description"></div>
      </b-card-text>
      <div>
        <b-button type="button" class="m-1" variant="dark" @click="moveLink">
          <b-icon icon="newspaper"></b-icon><b> 네이버 뉴스 본문</b>
        </b-button>
        <b-button
          type="button"
          class="m-1"
          variant="dark"
          @click="moveOriginaLlink"
        >
          <b-icon icon="newspaper"></b-icon><b> 언론사 본문</b>
        </b-button>
      </div>
    </b-card>
    <div class="text-right">
      <b-btn class="m-1" color="secondary" @click="moveList"
        ><b-icon icon="clipboard"></b-icon><b> 목록</b>
      </b-btn>
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

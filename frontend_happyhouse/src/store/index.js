import Vue from "vue";
import Vuex from "vuex";
import news from "./modules/news";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import memberStore from "@/store/modules/memberStore.js";
import noticeStore from "@/store/modules/noticeStore.js";
import houseStore from "@/store/modules/houseStore.js";

const store = new Vuex.Store({
  modules: {
    memberStore,
    noticeStore,
    houseStore,
    news,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

export default store;

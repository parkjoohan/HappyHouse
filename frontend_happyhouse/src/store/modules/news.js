export default {
  namespaced: true,
  state: {
    news: Object,
    currentPage: Number,
  },
  getters: {
    getNews(state) {
      return state.news;
    },
    getCurrentPage(state) {
      if (state.currentPage == Number) return 1;
      else return state.currentPage;
    },
  },
  mutations: {
    SET_NEWS(state, news) {
      state.news = news;
    },
    SET_CURRENT_PAGE(state, page) {
      state.currentPage = page;
    },
  },
  actions: {
    setNews({ commit }, news) {
      commit("SET_NEWS", news);
    },
    setCurrentPage({ commit }, page) {
      commit("SET_CURRENT_PAGE", page);
    },
  },
};

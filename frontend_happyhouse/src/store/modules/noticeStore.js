const noticeStore = {
  namespaced: true,
  state: {
    notice: Object,
    currentPage: Number,
  },
  getters: {
    getNotice(state) {
      return state.notice;
    },
    getCurrentPage(state) {
      if (state.currentPage == Number) return 1;
      else return state.currentPage;
    },
  },
  mutations: {
    SET_NOTICE(state, notice) {
      state.notice = notice;
    },
    SET_CURRENT_PAGE(state, page) {
      state.currentPage = page;
    },
  },
  actions: {
    setNews({ commit }, noitce) {
      commit("SET_NOTICE", noitce);
    },
    setCurrentPage({ commit }, page) {
      commit("SET_CURRENT_PAGE", page);
    },
  },
};

export default noticeStore;

const noticeStore = {
  namespaced: true,
  state: {
    notice: Object,
    currentPage: Number,
  },
  getters: {
    getCurrentPage(state) {
      if (state.currentPage == Number) return 1;
      else return state.currentPage;
    },
  },
  mutations: {
    SET_CURRENT_PAGE(state, page) {
      state.currentPage = page;
    },
  },
  actions: {
    setCurrentPage({ commit }, page) {
      commit("SET_CURRENT_PAGE", page);
    },
  },
};

export default noticeStore;

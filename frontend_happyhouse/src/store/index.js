import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: null,
    userId: null,
  },
  mutations: {
    LOGIN(state, data) {
      state.accessToken = data.accessToken;
      state.userId = data.userId;
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userId = null;
    },
  },
  actions: {
    login({ commit }, data) {
      commit("LOGIN", data);
    },
    logout({ commit }) {
      commit("LOGOUT");
      axios.defaults.headers.common["access-token"] = undefined;
    },
  },
  getters: {
    accessToken(state) {
      return state.accessToken;
    },
    userId(state) {
      return state.userId;
    },
  },
  modules: {},
});

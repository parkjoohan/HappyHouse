import { sidoList, gugunList, dongList, houseList } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [{ value: null, text: "선택하세요" }],
    guguns: [{ value: null, text: "선택하세요" }],
    dongs: [{ value: null, text: "선택하세요" }],
    houses: [],
    house: null,
  },

  getters: {},

  mutations: {
    SET_SIDO_LIST: (state, sidos) => {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
      });
    },
    SET_GUGUN_LIST: (state, guguns) => {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
      });
    },
    SET_DONG_LIST: (state, dongs) => {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.dongCode, text: dong.dongName });
      });
    },
    SET_HOUSE_LIST: (state, data) => {
      // console.log(data.aptList);
      data.aptList.forEach(function (item) {
        if (item.법정동읍면동코드 == data.dongCode) {
          //console.log(item);
          state.houses.push(item);
        }
      });
    },
    SET_DETAIL_HOUSE: (state, house) => {
      state.house = house;
    },
    CLEAR_SIDO_LIST: (state) => {
      state.sidos = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_GUGUN_LIST: (state) => {
      state.guguns = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_DONG_LIST: (state) => {
      state.dongs = [{ value: null, text: "선택하세요" }];
    },
    CLEAR_HOUSE_LIST: (state) => {
      state.houses = [];
    },
  },

  actions: {
    getSido: ({ commit }) => {
      sidoList(
        ({ data }) => {
          // console.log(data);
          commit("SET_SIDO_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugun: ({ commit }, sidoCode) => {
      const params = {
        sido: sidoCode,
      };
      gugunList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_GUGUN_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDong: ({ commit }, gugunCode) => {
      const params = {
        gugun: gugunCode,
      };
      dongList(
        params,
        ({ data }) => {
          // console.log(commit, response);
          commit("SET_DONG_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    getHouseList: ({ commit }, dongCode) => {
      // vue cli enviroment variables 검색
      //.env.local file 생성.
      // 반드시 VUE_APP으로 시작해야 한다.
      // const SERVICE_KEY = process.env.VUE_APP_APT_DEAL_API_KEY;
      //console.log(process.env.VUE_APP_APT_DEAL_API_KEY);
      const SERVICE_KEY =
        "DJcJ%2FHa%2BkQ1Rm6BZhfMhPQr30Xfftl2I0uDMFe%2B8xgH1NAD78o1ZK7Du38nI8TEVGAvGjz16PGYMVKerF0RLlA%3D%3D";
      const params = {
        LAWD_CD: dongCode.substring(0, 5), //요청변수 : 지역코드(법정동시군구코드)
        pageNo: encodeURIComponent("1"),
        numOfRows: encodeURIComponent("3000"),
        DEAL_YMD: "202110",
        serviceKey: decodeURIComponent(SERVICE_KEY),
      };
      console.log(dongCode.slice(-5) + "동 검색");
      houseList(
        params,
        (response) => {
          //console.log(response.data.response.body.items.item);

          commit("SET_HOUSE_LIST", {
            aptList: response.data.response.body.items.item,
            dongCode: dongCode.slice(-5),
          });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    detailHouse: ({ commit }, house) => {
      // 나중에 house.일련번호를 이용하여 API 호출
      commit("SET_DETAIL_HOUSE", house);
    },
  },
};

export default houseStore;

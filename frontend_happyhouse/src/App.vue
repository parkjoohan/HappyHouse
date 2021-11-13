<template>
  <div>
    <!-- 추가된 헤더 컴포넌트 사용 -->
    <Header />
    <div class="controll">
      <button @click="zoom(-1)">
        <span class="material-icons"> zoom_in </span>
      </button>
      <button @click="zoom(1)">
        <span class="material-icons"> zoom_out </span>
      </button>
    </div>
    <div class="map-area">
      <div class="searchbox">
        <div>
          <input type="text" value="여객 터미널" @keyup.enter="searchPlace" />
        </div>
        <div class="results">
          <div class="place" v-for="rs in search.results" :key="rs.id">
            <h4>{{ rs.place_name }}</h4>
            <div class="addr">{{ rs.address_name }}</div>
          </div>
        </div>
      </div>
      <KakaoMap class="kmap" :options="mapOption" />
    </div>
    <!-- 추가된 풋터 컴포넌트 사용 -->
    <Footer />
  </div>
</template>

<script>
import Header from "./components/common/Header"; //import 헤더 추가
import KakaoMap from "./components/map/KakaoMap";
import Footer from "./components/common/Footer"; //import 풋터 추가

export default {
  components: {
    Header, //헤더 컴포넌트 추가
    KakaoMap,
    Footer, //풋터 컴포넌트 추가
  },
  data() {
    return {
      mapOption: {
        center: {
          lat: 33.450701,
          lng: 126.570667,
        },
        level: 8,
      },
      search: {
        keyword: null,
        pgn: null,
        results: [],
      },
    };
  },

  mounted() {},

  methods: {
    searchPlace(e) {
      const keyword = e.target.value.trim();
      if (keyword.length === 0) {
        return;
      }

      const ps = new window.kakao.maps.services.Places();
      ps.keywordSearch(keyword, (data, status, pgn) => {
        this.search.keyword = keyword;
        this.search.pgn = pgn;
        this.search.results = data;
      });
    },

    zoom(delta) {
      // delta : 1 or -1
      console.log("[delta]", delta);
      const level = Math.max(3, this.mapOption.level + delta); // min is level 3
      this.mapOption.level = level;
      // console.log(this.mapOption.level);
    },
  },
};
</script>

<style lang="scss">
button {
  border: 1px solid transparent;
  padding: 6px;
  background-color: #efefefdd;
  border-radius: 6px;

  &:hover {
    background-color: #ddd;
    border-color: #ddd;
    cursor: pointer;
  }
  &:active {
    background-color: #aaa;
    border-color: #aaa;
  }
  .map-area {
    display: flex;
    position: relative;
    .searchbox {
      overflow-y: auto;
      position: absolute;
      top: 0;
      left: 0;
      height: 600 px;
      z-index: 10000;
      background-color: #ffffffaa;
      width: 300 px;
      display: flex;
      flex-direction: column;

      .results {
        flex: 1 1 auto;
        overflow-y: auto;
      }
      .place {
        padding: 8px;
        cursor: pointer;

        &:hover {
          background-color: aliceblue;
        }
        h4 {
          margin: 0;
        }
      }
    }
    .kmap {
      flex: 1 1 auto;
      height: 600px;
    }
  }
}
</style>

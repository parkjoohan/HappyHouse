<template>
  <div>
    <!-- 카카오맵 Vue -->
    <div class="map_wrap">
      <div
        id="map"
        class="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      ></div>
      <div id="menu_wrap">
        <input type="text" id="keyword" v-model="keyword" />
        <button v-on:click="searching">검색</button>
        <ul
          id="menu"
          style="
            margin-left: -1px;
            float: left;
            padding: 12px 9px;
            list-style: none;
            font-size: 15px;
            line-height: 1.5;
            position: absolute;
          "
        >
          <li id="search.tab1">
            <a href="#"></a>
          </li>
          <li id="search.tab2"><a href="#"></a></li>
        </ul>
        <ul
          id="placesList"
          style="
            margin-left: -1px;
            float: left;
            padding: 12px 9px;
            list-style: none;
          "
        ></ul>
        <div id="pagination"></div>
      </div>

      <div hidden>
        {{ aptlist }}
      </div>
      <p>
        <input
          type="checkbox"
          id="chkUseDistrict"
          @click="setOverlayMapTypeId()"
        />
        지적편집도 정보 보기
        <input type="checkbox" id="chkTerrain" @click="setOverlayMapTypeId()" />
        지형정보 보기
        <input type="checkbox" id="chkTraffic" @click="setOverlayMapTypeId()" />
        교통정보 보기
        <input type="checkbox" id="chkBicycle" @click="setOverlayMapTypeId()" />
        자전거도로 정보 보기
      </p>
    </div>
  </div>
</template>

<script>
let map;
export default {
  name: "KakaoMap",
  data() {
    return {
      container: Document,
    };
  },
  props: {
    aptlist: Array,
    si: String,
    gugun: String,
    dong: String,
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  updated() {
    this.addMarker();
  },
  methods: {
    initMap() {
      this.container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(35.16019989313708, 126.85155252952333),
        level: 8,
      };

      map = new kakao.maps.Map(this.container, options);
      //마커추가하려면 객체를 아래와 같이 하나 만든다.
      var marker = new kakao.maps.Marker({
        position: map.getCenter(),
      });
      marker.setMap(map);
      ////////////////////////////////////////////확대 / 축소 막대////////////////////////////////////////////////////
      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();
      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    },
    addScript() {
      const script = document.createElement("script"); /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=b433b7b8f851f9709d26a15fb4fc0660&libraries=services";
      document.head.appendChild(script);
    },
    // 지도 변환
    setOverlayMapTypeId() {
      var mapTypes = {
        terrain: kakao.maps.MapTypeId.TERRAIN,
        traffic: kakao.maps.MapTypeId.TRAFFIC,
        bicycle: kakao.maps.MapTypeId.BICYCLE,
        useDistrict: kakao.maps.MapTypeId.USE_DISTRICT,
      };
      var chkTerrain = document.getElementById("chkTerrain"),
        chkTraffic = document.getElementById("chkTraffic"),
        chkBicycle = document.getElementById("chkBicycle"),
        chkUseDistrict = document.getElementById("chkUseDistrict");
      // 지도 타입을 제거합니다
      for (var type in mapTypes) {
        map.removeOverlayMapTypeId(mapTypes[type]);
      }
      // 지적편집도정보 체크박스가 체크되어있으면 지도에 지적편집도정보 지도타입을 추가합니다
      if (chkUseDistrict.checked) {
        map.addOverlayMapTypeId(mapTypes.useDistrict);
      }
      // 지형정보 체크박스가 체크되어있으면 지도에 지형정보 지도타입을 추가합니다
      if (chkTerrain.checked) {
        map.addOverlayMapTypeId(mapTypes.terrain);
      }
      // 교통정보 체크박스가 체크되어있으면 지도에 교통정보 지도타입을 추가합니다
      if (chkTraffic.checked) {
        map.addOverlayMapTypeId(mapTypes.traffic);
      }
      // 자전거도로정보 체크박스가 체크되어있으면 지도에 자전거도로정보 지도타입을 추가합니다
      if (chkBicycle.checked) {
        map.addOverlayMapTypeId(mapTypes.bicycle);
      }
    },
    addMarker() {
      var mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 4, // 지도의 확대 레벨
      }; // 지도를 생성합니다
      var map = new kakao.maps.Map(this.container, mapOption); // 주소-좌표 변환 객체를 생성합니다
      var geocoder = new kakao.maps.services.Geocoder(); // 주소로 좌표를 검색합니다
      var imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
      // 해당 동에 아파트 거래내역이 없으면 해당 동으로 위치 조정
      if (this.aptlist.length == 0) {
        var addrFullName = this.si + " " + this.gugun + " ";
        geocoder.addressSearch(addrFullName, function (result, status) {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: map,
              position: coords,
            }); // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">' +
                "해당 도시에 거래 내역이 없습니다." +
                "</div>",
            });
            infowindow.open(map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            marker.setMap(map);
            map.setCenter(coords);
          }
        });
      }
      this.aptlist.forEach((element) => {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);
        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
        var addrFullName =
          this.si +
          " " +
          this.gugun +
          " " +
          element.법정동 +
          " " +
          element.도로명;
        geocoder.addressSearch(addrFullName, function (result, status) {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new kakao.maps.Marker({
              map: map,
              position: coords,
              image: markerImage, // 마커 이미지
              title: element.아파트,
            }); // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
              content:
                '<div style="width:150px;text-align:center;padding:6px 0;">' +
                addrFullName +
                " " +
                element.아파트 +
                "</div>",
            });
            infowindow.open(map, marker); // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            marker.setMap(map);
            map.setCenter(coords);
          }
        });
      });
    },

    // 키워드 검색
    addKakaoMapScript() {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?appkey=e1618b6825cc4783bc5f5e882244b42b&libraries=services";
      document.head.appendChild(script);
    },
    //keyword로 장소 검색하기
    searching() {
      var ps = new kakao.maps.services.Places();
      ps.keywordSearch(this.keyword, this.placeSearchCB);
    },
    placeSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        //  display place and marker
        this.displayPlaces(data);
        this.displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert("검색결과가 없습니다.");
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 중 오류가 발생했습니다.");
      }
    },
    displayPlaces(data) {
      var listEl = document.getElementById("placesList"),
        menuEl = document.getElementById("menu_wrap"),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds();
      this.removeAllChildNode(listEl);
      for (let i = 0; i < data.length; i++) {
        var itemEl = this.getListItem(i, data[i]);
        this.displayMarker(data[i], i);
        bounds.extend(new kakao.maps.LatLng(data[i].y, data[i].x));
        fragment.appendChild(itemEl);
      }
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;
      map.setBounds(bounds);
    },
    getListItem(index, places) {
      const el = document.createElement("id");
      let itemStr = "";
      itemStr =
        '<span class="markerbg marker_' +
        (index + 1) +
        '"></span>' +
        '<div class="info">' +
        "   <h5>" +
        places.place_name +
        "</h5>";
      if (places.road_address_name) {
        itemStr +=
          "    <span>" +
          places.road_address_name +
          "</span>" +
          '   <span class="jibun gray">' +
          places.address_name +
          "</span>";
      } else {
        itemStr += "    <span>" + places.address_name + "</span>";
      }
      itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";
      el.innerHTML = itemStr;
      el.className = "item";
      return el;
    },
    displayMarker(place) {
      // var let 으로 하면 인포윈도우 안됨
      const position = new kakao.maps.LatLng(place.y, place.x);
      const marker = new kakao.maps.Marker({
        map: map,
        position,
      });
      kakao.maps.event.addListener(marker, "click", () => {
        this.displayInfoWindow(marker, place, position);
        this.map.panTo(position);
      });
    },
    displayPagination() {},
    displayInfoWindow(marker, place, position) {
      const content =
        '<div id="infowindow", style="width:180px;height:100px;padding:15px 10px;">' +
        '<span id="placeinfo">' +
        place.place_name +
        "<p>" +
        place.phone +
        "</p>" +
        "<p>" +
        place.address_name +
        "</p>" +
        "</span>" +
        "</div>";
      let infowindow = new kakao.maps.InfoWindow({
        position,
        content,
        removable: true,
      });
      infowindow.setMap(this.map);
    },
    removeAllChildNode(el) {
      // console.log(el)
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    },
  },
};
</script>

<style scoped>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
.dot {
  overflow: hidden;
  float: left;
  width: 12px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/mini_circle.png");
}
.dotOverlay {
  position: relative;
  bottom: 10px;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  float: left;
  font-size: 12px;
  padding: 5px;
  background: #fff;
}
.dotOverlay:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.number {
  font-weight: bold;
  color: #ee6152;
}
.dotOverlay:after {
  content: "";
  position: absolute;
  margin-left: -6px;
  left: 50%;
  bottom: -8px;
  width: 11px;
  height: 8px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white_small.png");
}
.distanceInfo {
  position: relative;
  top: 5px;
  left: 5px;
  list-style: none;
  margin: 0;
}
.distanceInfo .label {
  display: inline-block;
  width: 50px;
}
.distanceInfo:after {
  content: none;
}
</style>

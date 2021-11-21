<template>
  <div>
    <!-- 카카오맵 Vue -->
    <div class="map_wrap">
      <div
        id="map"
        class="map"
        style="width: 100%; height: 100%; position: relative; overflow: hidden"
      ></div>
      <div id="menu_wrap" class="bg_white">
        <input type="text" id="keyword" v-model="keyword" />
        <button v-on:click="searchPlaces">검색</button>
        <ul id="placesList"></ul>
        <div id="pagination"></div>
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
import { mapGetters } from "vuex";
const houseStore = "houseStore";

// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
var customOverlay;
var geocoder = new kakao.maps.services.Geocoder();
var map;
var markers = [];
export default {
  name: "KakaoMap",
  data() {
    return {
      // map: null,
      // markers: [],
      ps: null,
      keyword: "",
    };
  },
  computed: {
    ...mapGetters(houseStore, ["housesChange"]),
  },
  watch: {
    housesChange() {
      //houseStorage의 houses가 변경되면 마커 새로 찍기
      this.displayMarkersWithAddress(this.housesChange);
    },
  },
  props: {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
    this.ps = new kakao.maps.services.Places();
  },
  created() {},
  methods: {
    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(35.16019989313708, 126.85155252952333),
        level: 8,
      };
      map = new kakao.maps.Map(container, options);

      var marker = new kakao.maps.Marker({
        position: map.getCenter(),
      });
      marker.setMap(map);
      markers.push(marker);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();
      map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
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

    //////////////////////////////////////////아파트 마킹//////////////////////////////////////////
    displayMarkersWithAddress(placeList) {
      var places = {};
      for (let i = 0; i < placeList.length; i++) {
        if (!places[placeList[i].아파트]) {
          //console.log(places[placeList[i].아파트]);
          places[placeList[i].아파트] = placeList[i];
        }
      }
      var bounds = new kakao.maps.LatLngBounds();
      this.removeMarker();

      let idx = 0;
      for (let [key, value] of Object.entries(places)) {
        var placePosition;
        //console.log("geocoding 전: " + places[i].법정동 + " " + places[i].지번);
        geocoder.addressSearch(
          value.법정동 + " " + value.지번,
          function (result, status) {
            // 정상적으로 검색이 완료됐으면
            if (status === kakao.maps.services.Status.OK) {
              placePosition = new kakao.maps.LatLng(result[0].y, result[0].x);
              console.log(placePosition, key, value);
              //addMarker()
              var imageSrc =
                  "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png",
                // 마커 이미지 url, 스프라이트 이미지를 씁니다
                imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
                imgOptions = {
                  spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
                  spriteOrigin: new kakao.maps.Point(0, idx++ * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
                  offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
                },
                markerImage = new kakao.maps.MarkerImage(
                  imageSrc,
                  imageSize,
                  imgOptions
                ),
                marker = new kakao.maps.Marker({
                  position: placePosition, // 마커의 위치
                  image: markerImage,
                });

              marker.setMap(map); // 지도 위에 마커를 표출합니다
              markers.push(marker); // 배열에 생성된 마커를 추가합니다

              //검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
              //LatLngBounds 객체에 좌표를 추가합니다
              bounds.extend(placePosition);
              (function (marker, place) {
                kakao.maps.event.addListener(marker, "click", function () {
                  if (customOverlay) {
                    customOverlay.setMap(null);
                    //customOverlay = null;
                  }

                  //인포윈도우에 장소명을 표시합니다 : getListItem()
                  var content = `
                    <div class="overlaybox">
                      <div class="boxtitle">${place.아파트}
                        <div class="close" onclick="closeOverlay()" title="닫기"></div>
                      </div>
                      
                      <div class="first"><img src="" position:relative; style="width:247px; height:136px;" alt=""></div>
                      <ul>
                        <li class="up">
                          <span class="title">건축년도</span>
                          <span class="count">${place.건축년도}</span>
                        </li>
                        <li>
                          <span class="title">주소</span>
                          <span class="count">${place.지번}</span>
                        </li>
                        <li>
                          <span class="title">거래금액</span>
                          <span class="count">${place.거래금액}</span>
                        </li>
                        <li>
                          <span class="last" id="recenthistor" data-toggle="modal" data-target="#myModal">아파트정보 update</span>
                        </li>
                      </ul>
                    </div>
                  `;
                  var position = new kakao.maps.LatLng(
                    marker.getPosition().getLat() + 0.00033,
                    marker.getPosition().getLng() - 0.00003
                  );
                  customOverlay = new kakao.maps.CustomOverlay({
                    position: position,
                    content: content,
                    xAnchor: 0.3,
                    yAnchor: 0.91,
                  });
                  customOverlay.setMap(map);
                });
                kakao.maps.event.addListener(map, "click", function () {
                  customOverlay.setMap(null);
                });
              })(marker, value);

              //검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
              map.setBounds(bounds);
            }
          }
        );
      }
    },

    //////////////////////////////////////////키워드 검색//////////////////////////////////////////
    // 키워드 검색을 요청하는 함수입니다
    searchPlaces() {
      var keyword = document.getElementById("keyword").value;

      if (!keyword.replace(/^\s+|\s+$/g, "")) {
        alert("키워드를 입력해주세요!");
        return false;
      }
      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      this.ps.keywordSearch(keyword, this.placesSearchCB);
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        this.displayPlaces(data);
        // 페이지 번호를 표출합니다
        this.displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert("검색 결과가 존재하지 않습니다.");
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        return;
      }
    },

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    displayPlaces(places) {
      var listEl = document.getElementById("placesList"),
        menuEl = document.getElementById("menu_wrap"),
        fragment = document.createDocumentFragment(),
        bounds = new kakao.maps.LatLngBounds();
      // 검색 결과 목록에 추가된 항목들을 제거합니다
      this.removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();
      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
          marker = this.addMarker(placePosition, i),
          itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function (marker, place) {
          kakao.maps.event.addListener(marker, "mouseover", function () {
            // 인포윈도우에 장소명을 표시합니다 : displayInfowindow()
            var content =
              ' <div style="padding:5px;z-index:1;"> ' +
              place.place_name +
              " </div> ";
            infowindow.setContent(content);
            infowindow.open(map, marker);
          });

          kakao.maps.event.addListener(marker, "mouseout", function () {
            infowindow.close();
          });

          itemEl.onmouseover = function () {
            // 인포윈도우에 장소명을 표시합니다 : displayInfowindow()
            var content =
              ' <div style="padding:5px;z-index:1;"> ' +
              place.place_name +
              " </div> ";
            infowindow.setContent(content);
            infowindow.open(map, marker);
          };

          itemEl.onmouseout = function () {
            infowindow.close();
          };
        })(marker, places[i]);

        fragment.appendChild(itemEl);
      }

      // 검색결과 항목들을 검색결과 목록 Elemnet에 추가합니다
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);
    },

    // 검색결과 항목을 Element로 반환하는 함수입니다
    getListItem(index, places) {
      var el = document.createElement("li");
      let itemStr = "";
      itemStr =
        '<span class="markerbg marker_' +
        (index + 1) +
        '"></span>' +
        '<div class="info">' +
        "   <h6><b>" +
        places.place_name +
        "</b></h6>";
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

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, idx) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png",
        // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(map); // 지도 위에 마커를 표출합니다
      markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(null);
      }
      markers = [];
    },

    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    displayPagination(pagination) {
      var paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = "on";
        } else {
          el.onclick = (function (i) {
            return function () {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    removeAllChildNods(el) {
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
</style>

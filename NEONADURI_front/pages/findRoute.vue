<template>
  <div class="findRoute">
    <div class="container">
        <div class="banner">
            <img src="/banner/banner.png" style="width: 50%;">
        </div>
        <div class="search">
            <div class="input-group mb-3 box">
                <input id="searchKeyword" v-model="startPoint"  type="text" class="form-control input-form fontSize"  aria-label="Default" aria-describedby="inputGroup-sizing-default" placeholder="출발지 입력">
                <button id="btn_select">클릭!</button>
                <ul id="searchResult" name="searchResult">
                  <li>검색결과</li>
                </ul>
            </div>
            <div class="input-group mb-3 box">
                <input v-model="endPoint" type="text" class="form-control input-form fontSize" aria-label="Default" aria-describedby="inputGroup-sizing-default" placeholder="도착지 입력">
            </div>
        </div>
        <div class="my-button">
            <div class="types reInput">

                <div class="buttons fontSize" @click="resetBoxes()">
                    다시입력
                </div>
            </div>
            <div class="types roadFinding">
                <div class="buttons fontSize">
                    길찾기     >
                </div>

            </div>
        </div>
        <div class="roadMap">

          <div class="map">
            <div id="map_wrap" class="map_wrap">
              <div id="map_div"></div>
            </div>
          </div>

          <div class="stopover">
            <p id="result"></p>
            <select id="selectLevel">
              <option value="0" selected="selected">교통최적+추천</option>
              <option value="1" >교통최적+무료우선</option>
              <option value="2" >교통최적+최소시간</option>
              <option value="3" >교통최적+초보</option>
            </select>
            <button id="btn_select" type="button"  class="btn btn-primary" >적용하기</button>
            <button @click="findRouteTmap()">눌러주세용ㅇㅇㅇㅇ</button>
            <hr>

          </div>


        </div>


    </div>
  </div>

</template>
<script	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xx9b31967c4bc2496f8dde1d66747658c9"></script>
<script>
import $ from 'jquery';
// import {mapState} from 'vuex'


export default {
  head:{
    script:[
      {src:"https://apis.openapi.sk.com/tmap/jsv2?version=1&appKey=l7xx9b31967c4bc2496f8dde1d66747658c9"}
    ]
  },data(){
    return{
      startPoint:'',
      endPoint:'',
      map:Object,
      marker_s: Object,
      marker_e: Object,
      markerArr :[],
      labelArr : [],
    }
  },mounted(){
    this.makeMap();
    this.initTmap();
  },
  methods:{
    resetBoxes(){
      this.startPoint='';
      this.endPoint='';
    },
    makeMap(){
      var map;
            // 1. 지도 띄우기
      map = new Tmapv2.Map("map_div", {
        center: new Tmapv2.LatLng(37.56701114710962, 126.9973611831669),
        width : "100%",
        height : "400px",
        zoom : 15,
        zoomControl : true,
        scrollwheel : true
      });

      this.map=map;
    },
    initTmap() {

				var map=this.map;
        var markerArr=this.markerArr;
        var labelArr=this.labelArr;

				// 2. POI 통합 검색 API 요청
				$("#btn_select").click(
					function() {
						var searchKeyword = $('#searchKeyword').val(); // 검색 키워드

						$.ajax({
							method : "GET", // 요청 방식
							url : "https://apis.openapi.sk.com/tmap/pois?version=1&format=json&callback=result", // url 주소
							async : false, // 동기설정
							data : { // 요청 데이터 정보
								"appKey" : "l7xx9b31967c4bc2496f8dde1d66747658c9", // 발급받은 Appkey
								"searchKeyword" : searchKeyword, // 검색 키워드
								"resCoordType" : "EPSG3857", // 요청 좌표계
								"reqCoordType" : "WGS84GEO", // 응답 좌표계
								"count" : 10 // 가져올 갯수
							},
							success : function(response) {
								var resultpoisData = response.searchPoiInfo.pois.poi;

								// 2. 기존 마커, 팝업 제거
								if (markerArr.length > 0) {
									for(var i in markerArr) {
										markerArr[i].setMap(null);
									}
									markerArr = [];
								}

								if (labelArr.length > 0) {
									for (var i in labelArr) {
										labelArr[i].setMap(null);
									}
									labelArr = [];
								}

								var innerHtml = ""; // Search Reulsts 결과값 노출 위한 변수
								//맵에 결과물 확인 하기 위한 LatLngBounds객체 생성
								var positionBounds = new Tmapv2.LatLngBounds();
                var marker;
								// 3. POI 마커 표시
								for (var k in resultpoisData) {
									// POI 마커 정보 저장
									var noorLat = Number(resultpoisData[k].noorLat);
									var noorLon = Number(resultpoisData[k].noorLon);
									var name = resultpoisData[k].name;

									// POI 정보의 ID
									var id = resultpoisData[k].id;

									// 좌표 객체 생성
									var pointCng = new Tmapv2.Point(
											noorLon, noorLat);

									// EPSG3857좌표계를 WGS84GEO좌표계로 변환
									var projectionCng = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
											pointCng);

									var lat = projectionCng._lat;
									var lon = projectionCng._lng;

									// 좌표 설정
									var markerPosition = new Tmapv2.LatLng(
											lat, lon);

									// Marker 설정
									marker = new Tmapv2.Marker(
										{
											position : markerPosition, // 마커가 표시될 좌표
											//icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_a.png",
											icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_"
													+ k
													+ ".png", // 아이콘 등록
											iconSize : new Tmapv2.Size(
													24, 38), // 아이콘 크기 설정
											title : name, // 마커 타이틀
											map : map // 마커가 등록될 지도 객체
										});

									// 결과창에 나타날 검색 결과 html
									innerHtml += "<li><div><img src='http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_" + k + ".png' style='vertical-align:middle;'/><span>"
											+ name
											+ "</span>  <button type='button' name='sendBtn' @click='poiDetail("
											+ id
											+ ");'>상세보기</button></div></li>";
                  console.log(id)
									// 마커들을 담을 배열에 마커 저장
									markerArr.push(marker);
									positionBounds.extend(markerPosition); // LatLngBounds의 객체 확장
								}

								$("#searchResult").html(innerHtml); //searchResult 결과값 노출
								map.panToBounds(positionBounds); // 확장된 bounds의 중심으로 이동시키기
								map.zoomOut();
							},
							error : function(request, status, error) {
								console.log("code:"
										+ request.status + "\n"
										+ "message:"
										+ request.responseText
										+ "\n" + "error:" + error);
							}
						});
					});
		},
    poiDetail(poiId) {
				console.log(poiId);

				$.ajax({
					method : "GET", // 요청 방식
					url : "https://apis.openapi.sk.com/tmap/pois/"
							+ poiId // 상세보기를 누른 아이템의 POI ID
							+ "?version=1&resCoordType=EPSG3857&format=json&callback=result&appKey="
							+ "l7xx9b31967c4bc2496f8dde1d66747658c9", // 발급받은 Appkey
					async : false, // 동기 설정
					success : function(response) {
						console.log(response);

						// 응답받은 POI 정보
						var detailInfo = response.poiDetailInfo;
						var name = detailInfo.name;
						var address = detailInfo.address;

						var noorLat = Number(detailInfo.frontLat);
						var noorLon = Number(detailInfo.frontLon);

						var pointCng = new Tmapv2.Point(noorLon, noorLat);
						var projectionCng = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(
								pointCng);

						var lat = projectionCng._lat;
						var lon = projectionCng._lng;

            console.log('lat : '+lat)
            console.log('lon : '+lon)

						var labelPosition = new Tmapv2.LatLng(lat, lon);

						// 상세보기 클릭 시 지도에 표출할 popup창
						var content = "<div style=' border-radius:10px 10px 10px 10px;background-color:#2f4f4f; position: relative;"
								+ "line-height: 15px; padding: 5 5px 2px 4; right:65px;'>"
								+ "<div style='font-size: 11px; font-weight: bold ; line-height: 15px; color : white'>"
								+ "name : "
								+ name
								+ "</br>"
								+ "address : "
								+ address + "</div>" + "</div>";

						var labelInfo = new Tmapv2.Label({
							position : labelPosition,
							content : content,
							map : map
						});
						//popup 생성

						// popup들을 담을 배열에 팝업 저장
						labelArr.push(labelInfo);
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n"
								+ "message:" + request.responseText + "\n"
								+ "error:" + error);
					}
				});
			},
    findRouteTmap(){
      var marker;
      var drawInfoArr = [];

      var map=this.map;

      // 시작, 도착 심볼찍기
      // 시작
      this.marker_s = new Tmapv2.Marker({
        position : new Tmapv2.LatLng(37.568085523663385, 126.98605733268329),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_s.png",
        iconSize : new Tmapv2.Size(24, 38),
        map:map
      });

      // 도착
      this.marker_e = new Tmapv2.Marker({
        position : new Tmapv2.LatLng(37.56445848334345, 127.00973587385866),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_r_m_e.png",
        iconSize : new Tmapv2.Size(24, 38),
        map:map
      });

      marker = new Tmapv2.Marker({
        position : new Tmapv2.LatLng(37.56626352138058, 126.98735015742581),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_1.png",
        iconSize : new Tmapv2.Size(24, 38),
        map:map
      });

      marker = new Tmapv2.Marker({
        position : new Tmapv2.LatLng(37.56568310756034, 127.00221495976581),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_2.png",
        iconSize : new Tmapv2.Size(24, 38),
        map:map
      });

      marker = new Tmapv2.Marker({
        position : new Tmapv2.LatLng(37.570369, 126.992153),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_3.png",
        iconSize : new Tmapv2.Size(24, 38),
        map:map
      });

      marker = new Tmapv2.Marker({
        position : new Tmapv2.LatLng(37.56335290252303, 127.00352387777271),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_4.png",
        iconSize : new Tmapv2.Size(24, 38),
        map:map
      });

      marker = new Tmapv2.Marker({
        position : new Tmapv2.LatLng(37.570721714117965, 127.00186090818215),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_5.png",
        iconSize : new Tmapv2.Size(24, 38),
        map:map
      });

      marker = new Tmapv2.Marker({
        position : new Tmapv2.LatLng(37.56515390827723, 126.99066536776698),
        icon : "http://tmapapi.sktelecom.com/upload/tmap/marker/pin_b_m_6.png",
        iconSize : new Tmapv2.Size(24, 38),
        map:map
      });

      var headers = {};
      headers["appKey"]="	l7xx9b31967c4bc2496f8dde1d66747658c9";

      $.ajax({
        type:"POST",
        headers : headers,
        url:"https://apis.openapi.sk.com/tmap/routes/routeOptimization30?version=1&format=json",//
        async:false,
        contentType: "application/json",
        data: JSON.stringify({
              "reqCoordType": "WGS84GEO",
              "resCoordType" : "EPSG3857",
              "startName": "출발",
              "startX": "126.98605733268329",
              "startY": "37.568085523663385",
              "startTime": "201711121314",
              "endName": "도착",
              "endX": "127.00973587385866",
              "endY": "37.56445848334345",
              "searchOption" : "0",
              "viaPoints": [
                {
                  "viaPointId": "test01",
                  "viaPointName": "test01",
                  "viaX": "126.98735015742581",
                  "viaY": "37.56626352138058",
                },
                {
                  "viaPointId": "test02",
                  "viaPointName": "test02",
                  "viaX": "127.00221495976581",
                  "viaY": "37.56568310756034",
                },
                {
                  "viaPointId": "test03",
                  "viaPointName": "test03",
                  "viaX": "126.992153",
                  "viaY": "37.570369",
                },
                {
                  "viaPointId": "test04",
                  "viaPointName": "test04",
                  "viaX": "127.00352387777271",
                  "viaY": "37.56335290252303",
                },
                {
                  "viaPointId": "test05",
                  "viaPointName": "test05",
                  "viaX": "127.00186090818215",
                  "viaY": "37.570721714117965",
                },
                {
                  "viaPointId": "test06",
                  "viaPointName": "test06",
                  "viaX": "126.99066536776698",
                  "viaY": "37.56515390827723",
                }
              ]
        }),
        success:function(response){
          var resultData = response.properties;
          var resultFeatures = response.features;

          // 결과 출력
          var tDistance = "총 거리 : " + (resultData.totalDistance/1000).toFixed(1) + "km,  ";
          var tTime = "총 시간 : " + (resultData.totalTime/60).toFixed(0) + "분,  ";
          var tFare = "총 요금 : " + resultData.totalFare + "원";

          $("#result").text(tDistance+tTime+tFare);

          for(var i in resultFeatures) {
            var geometry = resultFeatures[i].geometry;
            var properties = resultFeatures[i].properties;
            var polyline_;

            drawInfoArr = [];

            if(geometry.type == "LineString") {
              for(var j in geometry.coordinates){
                // 경로들의 결과값(구간)들을 포인트 객체로 변환
                var latlng = new Tmapv2.Point(geometry.coordinates[j][0], geometry.coordinates[j][1]);
                // 포인트 객체를 받아 좌표값으로 변환
                var convertPoint = new Tmapv2.Projection.convertEPSG3857ToWGS84GEO(latlng);
                // 포인트객체의 정보로 좌표값 변환 객체로 저장
                var convertChange = new Tmapv2.LatLng(convertPoint._lat, convertPoint._lng);

                drawInfoArr.push(convertChange);
              }

              polyline_ = new Tmapv2.Polyline({
                path : drawInfoArr,
                strokeColor : "#FF0000",
                strokeWeight: 6,
                map : map
              });
            }
          }
      },
      error:function(request,status,error){
        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
      }
	});
    },
    addComma(num) {
      var regexp = /\B(?=(\d{3})+(?!\d))/g;
      return num.toString().replace(regexp, ',');
		}
  }

}
</script>

<style>
    .findRoute{
        background-color: #eaf2f9;
        width: 100%;
        height: 100vh;
    }

.container {
  display: inline;
}

.banner {
  display: flex;
  align-items: center;
  justify-content: center;
}

    .input-form{
        width: 18vw;
        height: 6vh;
        font-size: larger;
        margin-top: 3vh;
        margin-bottom: 3vh;
        margin-left: 4vw;
        margin-right: 4vw;
    }

    .search{
        display: flex;
        align-items: center;
        justify-content: center;
    }

.search {
  display: flex;
  align-items: center;
  justify-content: center;

}

.box{
  width: 30%;
}

.my-button {
  display: flex;
  align-items: center;
  justify-content: center;
}

.types {
  width: 18vw;
  height: 6vh;
  margin-top: 1vh;
  margin-bottom: 3vh;
  margin-left: 4vw;
  margin-right: 4vw;
  font-size: 2vh;
}

.reInput {
  display: flex;
  justify-content: flex-start;
}

.roadFinding {
  display: flex;
  justify-content: flex-end;
}

.buttons {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 5vw;
  border-radius: 20px;
  border: 1px solid white;
  float: left;
  box-shadow: 3px 3px 3px black;

  transition-duration: 0.3s;

  font-size: 100%;
}

    .roadMap{
      display: flex;
      align-items: center;
      justify-content: center;
      height: 40vh;
    }

    .map{
      width: 35%;
      height: 100%;
    }

    .stopover{
      width: 30%;
      height: 100%;
      background-color: white;
    }


.buttons:active {
  margin-left: 5px;
  margin-top: 5px;
  box-shadow: none;
}

.fontSize{
  font-size: 2vh;
}
</style>

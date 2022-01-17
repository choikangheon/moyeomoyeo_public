/* global kakao */
import React, {useEffect, useState} from "react";
import './Main_map.css';
import Main_place from "../component/Session/main_place/Main_place";

const Main_map = (params) => {



    useEffect(() => {
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(37.566826, 126.9786765),
            level: 3
        };
        var map = new kakao.maps.Map(container, options);


    }, [])


    useEffect(() => {


            var container = document.getElementById('map');
            var options = {
                center: new kakao.maps.LatLng( params.coordinate.Y_POSITION,params.coordinate.X_POSITION),
                level: 3
            };
            var map = new kakao.maps.Map(container, options);


            var places = new kakao.maps.services.Places();

            //검색어 서치 부분
           // places.keywordSearch(params.coordinate.searchPlace, placesSearchCB);
            places.keywordSearch(params.coordinate.searchPlaceCode, placesTextSearchCB,{
                location: new kakao.maps.LatLng(params.coordinate.Y_POSITION,params.coordinate.X_POSITION)
            });

            places.categorySearch(params.coordinate.CATEGORY, placesSearchCB, {
                location: new kakao.maps.LatLng(params.coordinate.Y_POSITION,params.coordinate.X_POSITION)
            });

            // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
            function placesTextSearchCB(data, status, pagination) {
                if (status === kakao.maps.services.Status.OK) {

                    // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
                    console.log("placesTextSearchCB_function")
                    console.log(data);

                   params.setplaceData(data);
                    displayPlaces(data);

                } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
                    // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
                    console.log("없음");
                } else if (status === kakao.maps.services.Status.ERROR) {
                    // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요

                }
            }



// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
            function placesSearchCB(data, status, pagination) {
                if (status === kakao.maps.services.Status.OK) {

                    // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
                    console.log(data);
                    displayPlaces(data);

                } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
                    // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
                    console.log("없음");
                } else if (status === kakao.maps.services.Status.ERROR) {
                    // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요

                }
            }

            //data[0].place_name place name
            //data[0].x
            //data[0].y
            function displayPlaces(places) {
                //
                places.map(place => {
                    var marker = addMarker(new kakao.maps.LatLng(place.y, place.x,), place);
                })

            }

            function addMarker(position, place) { // 마커 생성성
                var marker = new kakao.maps.Marker({
                    position: position,
                    clickable: true
                });

                marker.setMap(map);

                // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
                var iwContent = '<div class="placeinfo">' +
                    '   <a class="title" href="' + place.place_url + '" target="_blank" title="' + place.place_name + '">' + place.place_name + '</a><hr>'

                if (place.road_address_name) {
                    iwContent += '    <span title="' + place.road_address_name + '">' + place.road_address_name + '</span><hr>' +
                        '  <span class="jibun" title="' + place.address_name + '">(지번 : ' + place.address_name + ')</span><hr>';
                } else {
                    iwContent += '    <span title="' + place.address_name + '">' + place.address_name + '</span><hr>';
                }

                iwContent += '    <span class="tel">' + place.phone + '</span><hr>' +
                    '</div>' +
                    '<div class="after"></div>';


                // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다

                var iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

                // 인포윈도우를 생성합니다
                var infowindow = new kakao.maps.InfoWindow({
                    content: iwContent,
                    removable: iwRemoveable
                });

                kakao.maps.event.addListener(marker, 'click', () => {
                    infowindow.open(map, marker);
                })

            }
        }

        ,
        [params.coordinate]
    )


    return (
        <div>
            <div id="map">
            </div>



        </div>
    );
}

export default Main_map;
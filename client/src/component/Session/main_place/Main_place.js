/* global kakao */
import React, {useState} from "react";
import Main_map from "../../../api/Main_map";


const Main_place = (params) => {

    const [searchPlaceStr, setsearchPlaceStr] = useState();


    const place_li_onclick = (x, y) => {
        console.log(x, y);
        params.setCoordinate({
            ...params.coordinate,
            X_POSITION: x,
            Y_POSITION: y
        })
        console.log(params.coordinate.X_POSITION);
    }

    const input_onchange = (e) => {
        setsearchPlaceStr(e.target.value);

    }

    const changeCategory = (categoryCode) => {
        if (params.coordinate.CATEGORY !== categoryCode)
            params.setCoordinate({
                ...params.coordinate, CATEGORY: categoryCode
            })
    }

    const searchPalce = () => {

        if (searchPlaceStr != null)
            params.setCoordinate({
                ...params.coordinate, searchPlaceCode: searchPlaceStr
            })
        else {
            window.alert("검색어를 입력하세요")
        }
    }
    return (

        <div className="Main_place_container">

            <div className="Main_place_List">

                <div className="place_head">
                    <h1>검색</h1>
                    <div className="search_title">
                        <input onChange={input_onchange} type="text" className="input_search"/>
                        <button onClick={searchPalce}>검색</button>
                    </div>

                </div>
                <div className="place_list">
                    {params.placeData.map(
                        place =>

                            <ul onClick={(e) => {
                                place_li_onclick(place.x, place.y)
                            }}>
                                {place.place_name}</ul>
                    )}
                </div>
            </div>

            <div className="Main_place_navLink">
                <li onClick={() => changeCategory("FD6")}>음식점</li>
                <li onClick={() => changeCategory("CE7")}>카페</li>
                <li onClick={() => changeCategory("SW8")}>지하철역</li>
                <li onClick={() => changeCategory("CS2")}>편의점</li>
                <li onClick={() => changeCategory("AD5")}>숙박</li>
                <li onClick={() => changeCategory("BK9")}>은행</li>
            </div>
        </div>

    );


}

export default Main_place
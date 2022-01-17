/* global kakao */
import React, {useEffect, useState} from "react";
import Title from "../../component/Header/Title"
import '../../component/Header/Title.css'
import Main_map from "../../api/Main_map";
import Main_user from "../../component/Session/main_user/Main_user";
import './MainPages.css'
import Main_place from "../../component/Session/main_place/Main_place";
import '../../component/Session/main_place/Main_place.css';
import axios from "axios";

const MainPages = () => {

    const [placeData, setplaceData] = useState([{}]);
    const [coordinate, setCoordinate] = useState({
        Y_POSITION: 37.5169347576857,
        X_POSITION: 126.722403434486,
        CATEGORY: "",
        searchPlaceCode: ""
    }, []);




    const hello = (e) => {
        const token = window.localStorage.getItem("token")
        console.log(token);
        axios.get("/api/member", {
            headers: {
                'Authorization': token,
            }
        });
    }


    return (
        <div className="mainContainer">
            <div className="headContainer">
                <Title/>
            </div>
            <div className='sessionContainer'>
                <Main_user setCoordinate={setCoordinate} coordinate={coordinate}/>
                <Main_map setCoordinate={setCoordinate} coordinate={coordinate} setplaceData={setplaceData}/>
                <Main_place setCoordinate={setCoordinate} coordinate={coordinate} placeData={placeData}/>
            </div>


            <div>

            </div>
        </div>
    );

}

export default MainPages
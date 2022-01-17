import React, {useEffect, useState} from "react";
import FreindLsit from "./FreindList";
import './Main_user.css';


const Main_user = (params) => {
    const [gps, setGps] = useState({
        y: 37.5169347576857,
        x: 126.722403434486
    });


    useEffect(() => {

            console.log("useeffect gps ");
            console.log(gps.y);
            console.log(gps.x);
            params.setCoordinate({
                ...params.coordinate,
                Y_POSITION: gps.y,
                X_POSITION: gps.x

            })
        }
        , [gps])


    return (

        <div>
            <h1 class="freindHeader">중간목록</h1>
                    {/*친구추가 버튼*/}
           
            
            <FreindLsit setGps={setGps} gps={gps}/>


            {/*<ul>
                <li>
                    친구목록 옆에 체크박스
                </li>
            </ul>
            */}
            
        </div>
    );


}


export default Main_user
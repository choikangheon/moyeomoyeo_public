/* global kakao */

import React,{useState} from "react";
import {useEffect} from "react";


const AddressSearch = (props) => {
    const[address,setAddress] = useState(props.address);
    const [place,setPlace] = useState([]);

    var geocoder = new kakao.maps.services.Geocoder();

    useEffect(()=>{
            geocoder.addressSearch(address, (result, status)=>{
                if (status === kakao.maps.services.Status.OK) {
                    setPlace([...result]);
                };
            } );
        },[]
    );


    return (
        <div>
            {place.map(pla=>
                <ul>
                    <li>{pla.x}</li>
                    <li>{pla.y}</li>
                </ul>
            )}
        </div>
    );


}
export default AddressSearch;

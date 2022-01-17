/* global kakao */
import './App.css';
import React, {useEffect} from "react";
import AddressSearch from "./api/AddressSearch";
import MainPages from "./pages/main/MainPages"
import {Router, Link, Route} from "react-router-dom";
import LoginPages from "./pages/login/LoginPages";
import AccountPages from "./pages/account/AccountPages";
import TestCompo from "./component/Session/test/TestCompo";
import Address from "./component/Session/main_address/Address";
const App = () => {

    return (
        <div>
            <Route path="/" exact={true} component={MainPages}/>
            <Route path="/loginpage" exact={true} component={LoginPages}/>
            <Route path="/account" exact={true} component={AccountPages}/>
                {/*<AddressSearch address="인천시 부평구 평천로305번길 15"/>*/}
            <Route path="/test" exact={true} component={TestCompo}/>
            <Route path="/address" exact={true} component={Address}/>
         </div>
    );

}


export default App;

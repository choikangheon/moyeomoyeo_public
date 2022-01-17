/* global kakao */
import React from 'react';
import Title from "../../component/Header/Title";
import Login from "../../component/Session/login_form/Login";
import "./LoginPages.css";

const LoginPages = () => {
    return (
        <div>
            <div className="Login_headContainer">
                <Title/>
            </div>


            <div className="Loing_mainContainer">
                <Login/>
            </div>

            <div className="Loing_footerContainer">
                <h1>footer</h1>

            </div>
        </div>
    );
};

export default LoginPages;
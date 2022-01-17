/* global kakao */
import React from 'react';
import Title from "../../component/Header/Title";
import Account from "../../component/Session/account_form/Account";
import './AccountPages.css';

const AccountPages = () => {
    return (
        <div>
            <div className="Account_headContainer">
                <Title/>
            </div>


            <div className="Account_mainContainer">
                <Account/>
            </div>

            <div className="Account_footerContainer">
                <h1>footer</h1>

            </div>
        </div>
    );
};

export default AccountPages;
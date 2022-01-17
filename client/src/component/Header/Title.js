import React, { useEffect } from "react";
import './Title.css';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMapMarkedAlt} from '@fortawesome/free-solid-svg-icons'
import {NavLink, useHistory} from "react-router-dom";
import {MainPages} from "../../pages/main/MainPages"

const Title = (props) => {

    const history = useHistory();

    const logout = ()=>{
        window.localStorage.clear();
        window.location.replace("/")
    }
    console.log("title 리랜더링");
    return(
        <div className="Title">
            
            <div className="main_logo">
                <div className="logo">
                    <div><FontAwesomeIcon icon={faMapMarkedAlt}/></div>
                </div>
                <div className="name">
                    <NavLink to="/" className="Main_link">
                        <span>MoyeoMoyeo</span>
                    </NavLink>
                </div>
            </div>

            <div className="nav_link">
                <NavLink to="/loginpage" className="Main_login_Link">
                {
                    window.localStorage.getItem("token") === null ?
                    <NavLink to="/loginpage" className="Main_login_Link">
                          <li>로그인</li>
                    </NavLink>
                        :
                        <NavLink to="/" className="Main_login_Link" onClick={logout}>
                            <li>로그아웃</li>
                        </NavLink>
                }
                </NavLink>
                <NavLink to="/address" className="Main_login_Link">
                    <li>주소지</li>
                </NavLink>
                <NavLink to="/account" className="Main_login_Link">
                    <li>회원가입</li>
                </NavLink>


            </div>
            </div>
    );
    
}
export default React.memo(Title);
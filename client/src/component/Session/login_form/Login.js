import React, {useState} from 'react';
import './Login.css';
import axios from "axios";
import {MainPages} from '../../../pages/main/MainPages'
import {useHistory} from "react-router-dom";

const Login = () => {
    const [member, setMember] = useState({
        memId: '',
        memPw: ''
    });

    const history = useHistory();

    const onChange = (e) => {

        setMember({
            ...member,
            [e.target.name]: e.target.value
        });
        console.log(member);
    }

    let body = {
        'memId': member.memId,
        'memPw': member.memPw
    }

    const onLogin = () => {
        history.push("/");
    };


    const handleSubmit = (e) => {
        e.preventDefault();
        axios
            .post("/login", body, {
                headers: {
                    "Content-Type": `application/json`,
                },
            })
            .then((res) => {
                const token = res.headers.authorization;

                // API 요청하는 콜마다 헤더에 accessToken 담아 보내도록 설정
                /* axios.defaults.headers.common['Authorization'] = `${token}`;*/
                console.log("===================")
                window.localStorage.setItem("token", token);
                /*   console.log(axios.defaults.headers.zcommon);*/


                if (token != null)
                    history.push("/")


            });
    }

    return (
        <div className="Login_container">
            <div className="Login_title">
                <h1>LOGIN</h1>
            </div>
            <div className="memberList">
                <li>회원</li>
            </div>

            <form onSubmit={handleSubmit} className="Login_inputContainer">
                <input name="memId" type="text" placeholder="아이디" onChange={onChange}/>
                <input name="memPw" type="password" placeholder="비밀번호" onChange={onChange}/>
                <button type="submit">로그인</button>
            </form>

            <div className="Login_navLink">
                <li>회원가입</li>
                <li>아이디찾기</li>
                <li>비밀번호찾기</li>
            </div>
        </div>
    );
}
export default Login

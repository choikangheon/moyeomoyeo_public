import React, {useState} from 'react';
import './Account.css';
import axios from "axios";

const Account = () => {
    const [member, setMember] = useState({
        mem_id: '',
        mem_pw: '',
        mem_name: '',
        mem_birth: '',
        mem_phone: ''
    });

    const onChange = (e) => {

        setMember({
            ...member,
            [e.target.name]: e.target.value
        });
        console.log(member);
    }

    const send = (e) => {
        e.preventDefault();
           axios.post('/member',{
               memId: member.mem_id,
               memPw:member.mem_pw,
               memName:member.mem_name,
               memBirth:member.mem_birth,
               memPhone:member.mem_phone,
           })
               .then((res) =>{
                   console.log(res);
                   window.alert("회원가입 성공");
               })
    }


    return (
        <div className="Account_container">
            <div className="Account_title">
                <h1>회원가입</h1>
            </div>
            <form className="Account_inputContainer">
                <input name="mem_id" type="text" onChange={onChange} placeholder="아이디"/>
                <input name="mem_pw" type="password" onChange={onChange} placeholder="비밀번호"/>
                <input name="mem_name" type="text" onChange={onChange} placeholder="이름"/>
                <input name="mem_birth" type="text" onChange={onChange} placeholder="생년월일"/>
                <input name="mem_phone" type="text" onChange={onChange} placeholder="전화번호"/>
                <button type="submit" onClick={send}>회원가입</button>
            </form>

        </div>
    );
};

export default Account

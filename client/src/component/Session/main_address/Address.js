import React, {useEffect, useState} from 'react';
import './Address.css';
import Title from '../../Header/Title';
import Modal from './Modal';
import axios from "axios";
import {useHistory} from "react-router-dom";

const Address = () => {
        const [modalVisible, setModalVisible] = useState(false);
        const [data, setData] = useState([]);
        const [address, setAddress] = useState();
        const [query, setQuery] = useState("react");
        const history = useHistory();


        const onChange = (e) => {

            setAddress(
                e.target.value
            );
            console.log(address);
        }


        const setMyPlace = async () => {
            const token = window.localStorage.getItem("token")
            console.log(token);

            try {
                const res = axios.post('api/member/place',
                        {
                            address
                        },
                        {
                            headers: {
                                'Authorization':
                                token
                            }

                        }
                    )
                ;

                window.alert("저장되었습니다");
            } catch
                (e) {
                console.log(e);
            }

        }

        const getMyPlace = async () => {
            const token = window.localStorage.getItem("token")
            console.log(token);
            try {
                const res = axios.get("/api/member/place", {
                    'address': address
                }, {
                    headers: {
                        'Authorization': token,
                    }
                });
                setData((await res).data);
                console.log(data);
            } catch (e) {
                console.log(e);
            }

        }

        useEffect(async () => {
            const token = window.localStorage.getItem("token")
            console.log(token);
            try {
                const res = axios.get("/api/member/place", {
                    headers: {
                        'Authorization': token,
                    }
                });
                setData((await res).data);
                console.log(res);

            } catch (e) {
                window.alert("로그인이 필요한 서비스입니다");
                history.push("/loginpage");
            }
        }, []);


        const test = () => {
            console.log(data);
        }
        const openModal = () => {
            setModalVisible(true);
        };
        const closeModal = () => {
            setModalVisible(false);
        };

        return (
            <div>
                <div className='headContainer'>
                    <Title/>
                </div>
                <div class='address_container'>
                    <div class='address_header'>
                        <h1>주소지 변경</h1>
                        <span class='new_address' onClick={openModal}>
            신규 주소지 등록
          </span>

                        <i class="line-plus">

                        </i>
                    </div>
                    {modalVisible && (
                        <Modal visible={modalVisible} closable={true} maskClosable={true} onClose={closeModal}>
                            <div>
                                <div class='pop-address'>
                                    <h1 class='popup-tit'>신규 배송지</h1>
                                    <table class='pop-table'></table>
                                    <tbody>
                                    <tr>
                                        <th scope='row'>주소지</th>
                                    </tr>
                                    </tbody>
                                    <div class='popup-btn'>
                                        <button type='button' class='n-btn btn-lighter' onClick='self.close();'>
                                            취소
                                        </button>
                                        <button type='button' class='n-btn btn-accent' onClick='chk_submit();return false;'>
                                            등록
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </Modal>
                    )}
                    <div class='address_name'>
                        <span class='name'>{data.member_name}</span>


                    </div>

                    <div class='txt_address'>{data.address_name}</div>

                    <div className='btn_group'>
                        <div className='btn_left'>
                            <button>수정</button>
                        </div>
                        <div className='btn_right'>
                            <button>삭제</button>
                        </div>
                    </div>


                </div>
                {modalVisible && (
                    <Modal visible={modalVisible} closable={true} maskClosable={true} onClose={closeModal}>
                        <div>
                            <div class='pop-address'>
                                <h1 class='popup-tit'>신규 배송지</h1>
                                <table class='pop-table'></table>
                                <tbody>
                                <tr>
                                    <th scope='row'>주소지</th>
                                    <input name="address" type="text" onChange={onChange} className="address_txt"/>
                                </tr>
                                </tbody>
                                <div class='popup-btn'>

                                    <button type='button' className='n-btn btn-lighter' onClick='self.close();'>
                                        취소
                                    </button>
                                    <button type='button' className='n-btn btn-accent'
                                            onClick={setMyPlace}>
                                        등록
                                    </button>
                                </div>
                            </div>
                        </div>
                    </Modal>
                )}


                <div>





                </div>
            </div>

        );
    }
;

export default Address;
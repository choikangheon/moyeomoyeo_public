import React, {useEffect, useState} from "react";
import NewFreindsList from './NewFreindsList';
import axios from "axios";
import qs from "qs";
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContent from '@material-ui/core/DialogContent';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import {useHistory} from "react-router-dom";


const FreindLsit = (params) => {

    const [friendList, setFriendList] = useState([]);
    const [freinds, setFreinds] = useState([]);
    const [newFreind, setNewFreind] = useState();
    const [dialog, setDialog] = useState(false);


    const getFrinedList = async () => {
        const token = window.localStorage.getItem("token")
        console.log(token);
        let url = "/api/member/friend";

        try {

            const res = await axios.get(url, {
                headers: {
                    'Authorization': token
                },
                paramsSerializer: params => {
                    return qs.stringify(params)
                }
            });


            const string = res.data;
            const split = string.toString().split(',');
            //setFriendList([...friendList,res.data]);
            console.log(split);
            setFriendList([...friendList, ...split]);
            console.log("===========")
            console.log(friendList);


        } catch (e) {
            console.log(e);
        }


    }

    const getMiddlePlace = async () => {


        console.log(freinds);
        const token = window.localStorage.getItem("token")
        console.log(token);
        let url = "/api/member/place/multiple/";
        freinds.map((friend) => {
                url += friend + ",";
            }
        )

        console.log(url);

        try {

            const res = axios.get(url, {
                headers: {
                    'Authorization': token
                },
                paramsSerializer: params => {
                    return qs.stringify(params)
                }
            });
            params.setGps({
                ...params.gps,
                x: (await res).data.x,
                y: (await res).data.y
            })
            console.log(params.gps.x);

            console.log(res);
        } catch (e) {
            console.log(e);
        }

    }

    const checkFriendState = () => {
        let flag = 0;
        for (let i = 0; i < freinds.length; i++) {
            for (let j = 0; j < friendList.length; j++) {
                if (freinds[i].toString() === friendList[j].toString())
                    flag = 1
            }
            if (flag === 0) {
                window.alert("친구가 아닙니다");
                window.location.replace("/");
                return;
            }
        }
        if (flag === 1)
            getMiddlePlace();

    }

    useEffect(() => {
        getFrinedList();
    }, [])

    const test = (e) => {
        console.log(friendList);
    }

    const changeInputData = (e) => {
        setNewFreind(e.target.value);
    }


    const addFreind = (e) => {
        e.preventDefault();
        setFreinds([...freinds,

            newFreind
        ]);
    }

    const handleClickOpen = () => {
        setDialog(true);
    }

    const handleClickClose = () => {

        setDialog(false)
        console.log(dialog);
    }


    return (
        <>

            <div class= "addbtn">
            <Button variant="contained" color="primary" onClick={/*addFreind*/handleClickOpen}>목록 추가</Button>
            </div>

            <Dialog open={dialog} onClose={handleClickClose}>
                <DialogTitle>목록 추가</DialogTitle>
                <DialogContent>
                    <TextField label="친구ID" type="text" name="" onChange={changeInputData}/>

                    {
                        /*<button onClick={addFreind}>친구 추가</button>
                        <button onclick={handleClickOpen}>닫기</button>
                        */
                    }
                </DialogContent>


                <DialogActions>
                    <Button variant="contained" color="primary" onClick={addFreind}>목록 추가</Button>
                    <Button variant="outined" color="primary" onClick={handleClickClose}>닫기</Button>
                </DialogActions>
            </Dialog>

            {
                freinds.map(friend => (
                    <ul>{friend}</ul>
                ))
            }

            <h2 class="myFreindsList">나의 친구 목록</h2>

            {/*<NewFreindsList params={friendList}/>*/}

            {
                friendList.map(friend => (
                    <ul>{friend}</ul>
                ))
            }
            <div class="moyeobtn">
            <Button variant="contained" color="primary" onClick={checkFriendState}>모여모여!</Button>
            </div>
            </>
    );
}

export default FreindLsit; 
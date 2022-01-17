/*
import React from 'react';
import {post} from 'axios';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogContent from '@material-ui/core/DialogContent';
import TextField from '@material-ui/core/TextField';
import  Button from '@material-ui/core/Button';
import { withStyles } from '@material-ui/core/styles';

const styles = theme => ({
    hidden: {
        display: 'none'
    }
});

class UserAdd extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            userId: '',
            userName: '',
            userAddress: '',
            open:'false'
        }
    }

    handleClickOpen = () => {
        this.setState({
            open:true
        });
    }

    handleClose = () => {
        this.setState({
            userId: '',
            userName: '',
            userAddress: '',
            open: false
        });
    }

    addUserAdd = () =>{
        const url = '/api/userInfos';
        const formData = new  FormData();
        formData.append('userId',this.state.userId);
        formData.append('name',this.state.userName);
        formData.append('Address',this.state.userAddress);
        const config ={
            headers: {
                'content-type': 'multipart/form-data'
            }
        }
        return post(url,formData,config);
    }

    handleFormSubmit = (e) => {
        e.preventDefault()
        this.addUserAdd()
            .then((response) => {
                console.log(response.data);
                this.props.stateRefresh();
            })
        this.setState({
            userId: '',
            userName: '',
            userAddress: '',
            open:false
        })

    }

    handleValueChange = (e) => {
        let nextState = {};
        nextState[e.target.name] = e.target.value;
        this.setState(nextState);
    }
    render() {
        const { classses } =this.props;
        return (
            <div>
                <Button variant="contained" color="primary" onclick={this.handleClickOpen}>
                    사용자 추가
                </Button>
                <Dialog open={this.state.open} onClose={this.handleClose}>
                    <DialogTitle>사용자 추가</DialogTitle>
                    <DialogContent>

                        <TextField label="이름" type="text" name="userName" value={this.state.userName} onChange={this.handleValueChange}/><br/>
                        <TextField label="주소" type="text" name="userAddress" value={this.state.userAddress} onChange={this.handleValueChange}/><br/>
                    </DialogContent>
                    <DialogActions>
                        <Button variant="contained" color="primary" onclick={this.handleFormSubmit}>추가</Button>
                        <Button variant="outlined" color="primary" onclick={this.handleFormSubmit}>닫기</Button>
                    </DialogActions>
                </Dialog>
            </div>


        );
    }
}

export default withStyles(styles)(UserAdd);*/

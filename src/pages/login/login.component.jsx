import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { isAdminUser, loginApiCall, saveLoggedInUser, storeToken } from '../../services/AuthService';
import { Button, Modal } from 'react-bootstrap';

const defaultLoginFields = {
    userIdOrEmail: '',
    password: ''
}

const Login = () => {

    const [ loginFields , setLoginFields ] = useState(defaultLoginFields);
    const { userIdOrEmail, password } = loginFields;

    //Modal Helpers
    const [show, setShow] = useState(false);
    const [message, setMessage ] = useState('');

    const handleClose = () => setShow(false);
    // Modal Helpers ends

    const handleChange = (event) => {
        const {name, value} = event.target;

        setLoginFields({ ...loginFields, [name]: value });
    }

    const navigator = useNavigate();

    const handleLoginForm =async (e) => {
        e.preventDefault();

        const login = {...loginFields};

        if(userIdOrEmail.length === 0 && password.length === 0){
            setShow(true);
            setMessage("UserId and Password Cannot Be Empty");
            setLoginFields(defaultLoginFields);
            return;
        }
        else if(userIdOrEmail.length === 0){
            setShow(true);
            setMessage("UserId Cannot Be Empty");
            setLoginFields(defaultLoginFields);
            return;
        }
        else if(password.length === 0){
            setShow(true);
            setMessage("Password Cannot Be Empty");
            setLoginFields(defaultLoginFields);
            return;
        }

        // console.log(login);

        await loginApiCall(login).then( (response) => {

            const token  = 'Bearer ' + response.data.accessToken;
            const role = response.data.role;

            storeToken(token);

            saveLoggedInUser(userIdOrEmail, role);

            if(isAdminUser()) navigator("/admin-home")
            else navigator("/merchant-home");

            window.location.reload(false);

        }).catch( error => {
            setShow(true);
            setMessage("Invalid Credentials");
            setLoginFields(defaultLoginFields);
            console.log(error);
        });
    }

  return (
    <div className='container'>
        <br></br>
        <div className='row'>
            <div className='col-md-6 offset-md-3'>
                <div className='card'>
                    <div className='card-header'>
                        <h2 className='text-center'> Login </h2>
                    </div>

                    <div className='card-body'>
                        <form>
                           
                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> userId Or Email </label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='userIdOrEmail'
                                        className='form-control'
                                        placeholder='Enter UserId Or Email'
                                        required
                                        value={userIdOrEmail}
                                        onChange={ handleChange }
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Password </label>
                                <div className='col-md-9'>
                                    <input
                                        type='password'
                                        name='password'
                                        className='form-control'
                                        placeholder='Enter Password'
                                        required
                                        value={password}
                                        onChange={handleChange}
                                    >
                                    </input>
                                </div>
                            </div>


                            <div className='form-group mb-3'>
                                <button className='btn btn-primary col-md-6 offset-md-3' onClick={ handleLoginForm }>Submit</button>
                                <Link className='nav-link col-md-9 offset-md-4' to="/register">
                                    New to Inventory? Register
                                 </Link>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

        <Modal
            show={show}
            onHide={handleClose}
            backdrop="static"
            keyboard={false}
        >
            <Modal.Header closeButton>
            <Modal.Title></Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {message}
            </Modal.Body>
            <Modal.Footer>
            <Button variant="secondary" onClick={handleClose}>
                Understood 🫡
            </Button>
            </Modal.Footer>
        </Modal>

    </div>
  )
}

export default Login;

import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { isAdminUser, loginApiCall, saveLoggedInUser, storeToken } from '../../services/AuthService';

const defaultLoginFields = {
    userIdOrEmail: '',
    password: ''
}

const Login = () => {

    const [ loginFields , setLoginFields ] = useState(defaultLoginFields);
    const { userIdOrEmail, password } = loginFields;

    const handleChange = (event) => {
        const {name, value} = event.target;

        setLoginFields({ ...loginFields, [name]: value });
    }

    const navigator = useNavigate();

    const handleLoginForm =async (e) => {
        e.preventDefault();

        const login = {...loginFields};

        console.log(login);

        await loginApiCall(login).then( (response) => {

            // const token = 'Basic '+ window.btoa(userIdOrEmail + ":" + password);

            const token  = 'Bearer ' + response.data.accessToken;
            const role = response.data.role;

            storeToken(token);

            saveLoggedInUser(userIdOrEmail, role);

            if(isAdminUser())    navigator("/admin-home")
            else navigator("/merchant-home");

            window.location.reload(false);
        }).catch( error => {
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

    </div>
  )
}

export default Login;

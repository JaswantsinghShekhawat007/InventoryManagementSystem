import React, { useState } from 'react';
import { registerAdminCall } from '../../services/AdminAuthService';
import { Box, Snackbar } from '@mui/material';
import { Button, Modal } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const defaultFormFields = {
    userId: '',
    email: '',
    password: '',
    admin: {
        name: '',
        contactNo: ''
    }
};

const defaultAdminFields = {
    name: '',
    contactNo: ''
}

const RegisterAdmin = () => {

    const [formFields, setFormFields] = useState(defaultFormFields);
    const { userId, email, password } = formFields;

    const [ updatedAdmin, setUpdatedAdmin ] = useState(defaultAdminFields)
    const { name, contactNo } = updatedAdmin;

    const [confirmPassword, setConfirmPassword] = useState('');

    //Modal Helpers
    const [show, setShow] = useState(false);
    const [message, setMessage ] = useState('');

    const navigator = useNavigate();

    const handleCloseModal = () => setShow(false);
    // Modal Helpers ends

    //PopOver
    const [open, setOpen] = React.useState(false);

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
        return;
        }

        setOpen(false);
    };
    //pop Over end
    

    const handleUpdateAdmin = (event) => {
        const {name, value} = event.target;

        setUpdatedAdmin( (prev) => ({ ...prev, [name]:value }))
    }

    const handleChange = (event) => {
        const { name, value } = event.target;

        setFormFields( (prevState) => ({ ...prevState, [name]: value}) );
        
    }   

    const handleRegistrationForm = ( e ) => {
        e.preventDefault()
        
        if(password!==confirmPassword){
            setMessage("Passwords Do Not Match");
            setShow(true);
            return;
        }

        const register = {...formFields, admin: updatedAdmin };

        // console.log(register);

        registerAdminCall(register).then( (response) => {
            console.log(response.data);
            setOpen(true);
            navigator("/admin-home")
        }).catch( (error) => {
            setMessage("Registration Failed Try Again");
            setShow(true);
            console.log(error);
        });

        
        setFormFields(defaultFormFields);
    }
    

  return (
    <div className='container'>
        <br></br>
        <div className='row'>
            <div className='col-md-6 offset-md-3'>
                <div className='card'>
                    <div className='card-header'>
                        <h2 className='text-center'> Admin Registration </h2>
                    </div>

                    <div className='card-body'>
                        <form>
                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Name </label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='name'
                                        className='form-control'
                                        placeholder='Enter Name'
                                        value={name}
                                        onChange={ handleUpdateAdmin }
                                        required
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Contact Number </label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='contactNo'
                                        className='form-control'
                                        placeholder='Enter Mobile Number'
                                        value={contactNo}
                                        onChange={ handleUpdateAdmin }
                                        required
                                    >
                                    </input>
                                </div>
                            </div>
                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> UserId </label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='userId'
                                        className='form-control'
                                        placeholder='Enter UserId'
                                        value={userId}
                                        onChange={ handleChange }
                                        required
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Email </label>
                                <div className='col-md-9'>
                                    <input
                                        type='email'
                                        name='email'
                                        className='form-control'
                                        placeholder='Enter Your Email'
                                        value={email}
                                        onChange={ handleChange }
                                        required
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
                                        value={password}
                                        onChange={handleChange}
                                        required
                                    >
                                    </input>
                                </div>
                            </div>
                            

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Confirm Password </label>
                                <div className='col-md-9'>
                                    <input
                                        type='password'
                                        name='confirmPassword'
                                        className='form-control'
                                        placeholder='Confirm Password'
                                        value={confirmPassword}
                                        onChange={ (e) => setConfirmPassword(e.target.value) }
                                        required
                                    >
                                    </input>
                                </div>
                            </div>


                            <div className='form-group mb-3'>
                                <button className='btn btn-primary' onClick={ handleRegistrationForm }>Submit</button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

        <Box sx={{ display: 'flex', justifyContent: 'center' }}>            
            <Snackbar
              anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
              open={open}
              autoHideDuration={2000}
              onClose={handleClose}
              message="Admin Registered Successfully"
          />
        </Box>

        <Modal
            show={show}
            onHide={handleCloseModal}
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
            <Button variant="secondary" onClick={handleCloseModal}>
                Understood 🫡
            </Button>
            </Modal.Footer>
        </Modal>

    </div>
  )
}

export default RegisterAdmin;

import React, { useState } from 'react'
import { registerMerchantCall } from '../../services/AuthService';
import { Box, Snackbar } from '@mui/material';


const defaultFormFields = {
    userId: '',
    email: '',
    password: '',
    merchant: {
        name: '',
        contactNo: '',
        address: {
            doorNo: '',
            area: '',
            city: '',
            state: '',
            pincode: ''
        }
    }
};

const defaultMerchantFields = {
    name: '',
    contactNo: '',
    address: {
        doorNo: '',
        area: '',
        city: '',
        state: '',
        pincode: ''
    }
}

const defaultAddressFields = {
    doorNo: '',
    area: '',
    city: '',
    state: '',
    pincode: ''
}

const RegisterMerchant = () => {

    const [formFields, setFormFields] = useState(defaultFormFields);
    const { userId, email, password } = formFields;

    const [ updatedMerchant, setUpdatedMerchant ] = useState(defaultMerchantFields)
    const { name, contactNo } = updatedMerchant;

    const [updatedAddress, setUpdatedAddress] = useState( defaultAddressFields );
    const { doorNo, area, city, state, pincode } = updatedAddress;

    const [confirmPassword, setConfirmPassword] = useState('');

    //PopOver
  const [open, setOpen] = React.useState(false);

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setOpen(false);
  };
  //pop Over end
    
    const handleUpdatedAddress = (event) => {
        const { name, value } = event.target;
        
        setUpdatedAddress( {...updatedAddress, [name]: value} );
        setUpdatedMerchant( (prev) => ({ ...prev, address: { ...prev.address, [name]:value } }))
        
    }

    const handleUpdateMerchant = (event) => {
        const {name, value} = event.target;

        setUpdatedMerchant( (prev) => ({ ...prev, [name]:value }))
    }

    const handleChange = (event) => {
        const { name, value } = event.target;

        setFormFields( (prevState) => ({ ...prevState, [name]: value}) );
    }   

    const handleRegistrationForm = ( e ) => {
        e.preventDefault()
        
        if(password!==confirmPassword){     
            alert("Passwords Do Not Match");
            return;
        }

        const register = {...formFields, merchant: updatedMerchant };

        console.log(register);

        registerMerchantCall(register).then( (response) => {
            console.log(response.data);
        }).catch( error => {
            console.log(error);
        });
        setOpen(true);

        setFormFields(defaultFormFields);
    }
    

  return (
    <div className='container'>
        <br></br>
        <div className='row'>
            <div className='col-md-6 offset-md-3'>
                <div className='card'>
                    <div className='card-header'>
                        <h2 className='text-center'> Merchant Registration </h2>
                    </div>

                    <div className='card-body'>
                        <form>
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
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Name </label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='name'
                                        className='form-control'
                                        placeholder='Enter Name'
                                        value={name}
                                        onChange={ handleUpdateMerchant }
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
                                        onChange={ handleUpdateMerchant }
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> Door Number </label>
                                <div className='col-md-3'>
                                    <input
                                        type='text'
                                        name='doorNo'
                                        className='form-control'
                                        placeholder='Enter Door Number'
                                        value={doorNo}
                                        onChange={ handleUpdatedAddress }
                                    >
                                    </input>
                                </div>

                                <label className='col-md-2 control-label'> Area </label>
                                <div className='col-md-4'>
                                    <input
                                        type='text'
                                        name='area'
                                        className='form-control'
                                        placeholder='Enter Area'
                                        value={area}
                                        onChange={ handleUpdatedAddress }
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> City </label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='city'
                                        className='form-control'
                                        placeholder='Enter City'
                                        value={city}
                                        onChange={ handleUpdatedAddress }
                                    >
                                    </input>
                                </div>
                            </div>

                            <div className='row mb-3'>
                                <label className='col-md-3 control-label'> State </label>
                                <div className='col-md-3'>
                                    <input
                                        type='text'
                                        name='state'
                                        className='form-control'
                                        placeholder='Enter State'
                                        value={state}
                                        onChange={ handleUpdatedAddress }
                                    >
                                    </input>
                                </div>

                                <label className='col-md-2 control-label'> Pincode </label>
                                <div className='col-md-4'>
                                    <input
                                        type='text'
                                        name='pincode'
                                        className='form-control'
                                        placeholder='Enter Pincode'
                                        value={pincode}
                                        onChange={ handleUpdatedAddress }
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
              message="Merchant Registered Successfully. Proceed To Sign In.."
          />
        </Box>

    </div>
  )

}

export default RegisterMerchant;

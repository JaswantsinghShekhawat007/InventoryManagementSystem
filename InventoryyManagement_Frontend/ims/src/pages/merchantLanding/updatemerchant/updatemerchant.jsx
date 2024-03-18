import React, { useState } from 'react';
import { updateMerchantCall } from '../../../services/MerchantService';

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

const UpdateMerchant = () => {

  const [ updatedMerchant, setUpdatedMerchant ] = useState(defaultMerchantFields)
  const { name, contactNo } = updatedMerchant;

  const [updatedAddress, setUpdatedAddress] = useState( defaultAddressFields );
  const { doorNo, area, city, state, pincode } = updatedAddress;

  
  const handleUpdatedAddress = (event) => {
      const { name, value } = event.target;
      
      setUpdatedAddress( {...updatedAddress, [name]: value} );
      setUpdatedMerchant( (prev) => ({ ...prev, address: { ...prev.address, [name]:value } }))
      
  }

  const handleUpdateMerchant = (event) => {
      const {name, value} = event.target;

      setUpdatedMerchant( (prev) => ({ ...prev, [name]:value }))
  }
  

  const handleRegistrationForm = ( e ) => {
      e.preventDefault()

      const register = {...updatedMerchant, address: updatedAddress };

      console.log(register);

      updateMerchantCall(register).then( (response) => {
          console.log(response.data);
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
                      <h2 className='text-center'> Merchant Registration </h2>
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

  </div>
)

}

export default UpdateMerchant;

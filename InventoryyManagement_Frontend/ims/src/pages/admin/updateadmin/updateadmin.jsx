import React, { useState } from 'react';
import { updateAdminCall } from '../../../services/AdminServices';


const defaultAdminFields = {
  name: '',
  contactNo: ''
}

const UpdateAdmin = () => {

  const [ updatedAdmin, setUpdatedAdmin ] = useState(defaultAdminFields)
  const { name, contactNo } = updatedAdmin;

  

  const handleUpdateAdmin = (event) => {
      const {name, value} = event.target;

      setUpdatedAdmin( (prev) => ({ ...prev, [name]:value }))
  }

  const handleRegistrationForm = ( e ) => {
      e.preventDefault()

      const register = {...updatedAdmin };

      console.log(register);

      updateAdminCall(register).then( (response) => {
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

export default UpdateAdmin;

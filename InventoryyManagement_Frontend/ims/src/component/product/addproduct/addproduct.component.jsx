import React, { useState } from 'react';
import { addProductCall } from '../../../services/ProductServices';
import { Box, Snackbar } from '@mui/material';

const defaultFormFields = {
  name: '',
  description: '',
  tag: '',
  price: 0,
  discount: 0,
  quantity: 0,
  urls: []
};

const AddProduct = () => {

  const [ formFields, setFormFields ] = useState(defaultFormFields);
  const { name, description, tag, price, discount, quantity, urls } = formFields
    //PopOver
  const [open, setOpen] = React.useState(false);

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setOpen(false);
  };
  //pop Over end

  const handleChange = (event) => {
    const { name, value } = event.target;

    setFormFields( (prevState) => ({ ...prevState, [name]: value}) );
  }



  const handleAddProductForm = (e) => {
    e.preventDefault();

    const products = { ...formFields, urls: urls.split(/,|\n/).map(url => url.trim()) }
    
    // console.log(products);

    addProductCall(products).then( (response) => {
        console.log(response)
    }).catch( (error) =>{
        console.log(error)
    });

    setOpen(true);

    setFormFields(defaultFormFields);
  };

  return (
    <div className='container'>
      <br></br>
      <div className='row'>
          <div className='col-md-6 offset-md-3'>
              <div className='card'>
                  <div className='card-header'>
                      <h2 className='text-center'> Add Product </h2>
                  </div>

                  <div className='card-body'>
                      <form>
                          <div className='row mb-3'>
                              <label className='col-md-3 control-label'> Product Name </label>
                              <div className='col-md-9'>
                                  <input
                                      type='text'
                                      name='name'
                                      className='form-control'
                                      placeholder='Enter Product Name'
                                      value={name}
                                      onChange={ handleChange }
                                      required
                                  >
                                  </input>
                              </div>
                          </div>

                          <div className='row mb-3'>
                              <label className='col-md-3 control-label'> Product Description </label>
                              <div className='col-md-9'>
                                  <input
                                      type='text'
                                      name='description'
                                      className='form-control'
                                      placeholder='Enter Product Description'
                                      value={description}
                                      onChange={ handleChange }
                                      required
                                  >
                                  </input>
                              </div>
                          </div>
                          <div className='row mb-3'>
                              <label className='col-md-3 control-label'> Tag </label>
                              <div className='col-md-9'>
                                  <input
                                      type='text'
                                      name='tag'
                                      className='form-control'
                                      placeholder='Enter Tag'
                                      value={tag}
                                      onChange={ handleChange }
                                      required
                                  >
                                  </input>
                              </div>
                          </div>

                          <div className='row mb-3'>
                              <label className='col-md-3 control-label'> Price </label>
                              <div className='col-md-9'>
                                  <input
                                      type='number'
                                      name='price'
                                      className='form-control'
                                      placeholder='Enter Price'
                                      value={price}
                                      onChange={ handleChange }
                                      required
                                  >
                                  </input>
                              </div>
                          </div>

                          <div className='row mb-3'>
                              <label className='col-md-3 control-label'> Discount </label>
                              <div className='col-md-9'>
                                  <input
                                      type='number'
                                      name='discount'
                                      className='form-control'
                                      placeholder='How Much Discount To Offer'
                                      value={discount}
                                      onChange={handleChange}
                                      required
                                  >
                                  </input>
                              </div>
                          </div>
                          

                          <div className='row mb-3'>
                              <label className='col-md-3 control-label'> Quantity </label>
                              <div className='col-md-9'>
                                  <input
                                      type='number'
                                      step='1'
                                      name='quantity'
                                      className='form-control'
                                      placeholder='Enter The Number Of Quantity'
                                      value={quantity}
                                      onChange={ handleChange }
                                      required
                                  >
                                  </input>
                              </div>
                          </div>

                          <div className='row mb-3'>
                              <label className='col-md-3 control-label'> Image URLs </label>
                              <div className='col-md-9'>
                                  <input
                                      type='text'
                                      name='urls'
                                      className='form-control'
                                      placeholder='Enter URLs Seperated By Commas'
                                      value={urls}
                                      onChange={ handleChange }
                                      required
                                  >
                                  </input>
                              </div>
                          </div>

                          <div className='form-group mb-3'>
                              <button type='submit' className='btn btn-primary' onClick={ handleAddProductForm }>Submit</button>
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
              message="Product Added Successfully"
          />
        </Box>

  </div>
  )
}

export default AddProduct;

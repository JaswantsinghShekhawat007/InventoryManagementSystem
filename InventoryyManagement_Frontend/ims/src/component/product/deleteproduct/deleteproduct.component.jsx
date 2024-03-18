import { Box, Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, Snackbar, TextField } from '@mui/material';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { deleteProduct } from '../../../services/ProductServices';
import { getLoggedInUser } from '../../../services/AuthService';

const DeleteProduct = () => {
  
  const [ productId, setProductId ] = useState('');

  const [ confirmation, setConfirmation ] = useState('');

  const [open, setOpen] = useState(false);

   //PopOver
   const [openP, setOpenP] = React.useState(false);

   const handleCloseP = (event, reason) => {
     if (reason === 'clickaway') {
       return;
     }
 
     setOpenP(false);
   };
   //pop Over end

  const navigator = useNavigate();

  const handleClickOpen =() => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    navigator('/merchant-home');
  };

  const loggedInUser = getLoggedInUser();

  const handleDelete = (event) => {
    event.preventDefault();

    if(confirmation === loggedInUser){
      deleteProduct(productId).then( (response) => {
          console.log(response)
      }).catch( (error) =>{
          console.log(error)
      });
      setOpenP(true);
      setOpen(false);
      setProductId('');

      navigator('/merchant-home');
    }

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
                              <label className='col-md-3 control-label'> Enter Product Id To Delete </label>
                              <div className='col-md-9'>
                                  <input
                                      type='text'
                                      name='productId'
                                      className='form-control'
                                      placeholder='Enter Product Id To Delete'
                                      value={productId}
                                      onChange = { (e) => setProductId(e.target.value) }
                                      required
                                  >
                                  </input>
                              </div>
                          </div>

                          <div className='form-group mb-3'>
                              <button type='button' className='btn btn-primary' onClick={handleClickOpen}>Delete</button>
                              <Dialog
                                open={open}
                                onClose={handleClose}
                                PaperProps={{
                                  component: 'form',
                                  onSubmit: (event) => {
                                    event.preventDefault();
                                    // const formData = new FormData(event.currentTarget);
                                    // const formJson = Object.fromEntries(formData.entries());
                                    // const email = formJson.email;
                                    // console.log(email);
                                    handleClose();
                                  },
                                }}
                              >
                              <DialogTitle>Delete Product With Id:{productId}</DialogTitle>
                              <DialogContent>
                                <DialogContentText>
                                  Are You Sure Delete This Product.
                                  You Can't Undo Changes.
                                </DialogContentText>
                                <TextField
                                  autoFocus
                                  required
                                  margin="dense"
                                  id="name"
                                  name="userId"
                                  label="Enter Your 'UserId' to Delete Product"
                                  type="text"
                                  value={confirmation}
                                  onChange={ (e) => setConfirmation(e.target.value) }
                                  fullWidth
                                  variant="standard"
                                />
                              </DialogContent>
                              <DialogActions>
                                <Button onClick={handleClose}>Cancel</Button>
                                <Button type="submit" onClick={handleDelete}>Delete</Button>
                              </DialogActions>
                            </Dialog>
                          </div>

                      </form>
                  </div>
              </div>
          </div>
      </div>
      <Box sx={{ display: 'flex', justifyContent: 'center' }}>            
            <Snackbar
              anchorOrigin={{ vertical: 'top', horizontal: 'center' }}
              open={openP}
              autoHideDuration={2000}
              onClose={handleCloseP}
              message="Product Deleted Successfully"
          />
        </Box>

  </div>
  )
}

export default DeleteProduct;

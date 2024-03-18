import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField } from '@mui/material';
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { getLoggedInUser } from '../../../services/AuthService';
import { deleteMerchantCall } from '../../../services/Auth-Admin';

const DeleteMerchant = () => {
    const [ merchantId, setMerchantId ] = useState('');

    const [ confirmation, setConfirmation ] = useState('');
  
    const [open, setOpen] = useState(false);
  
    const navigator = useNavigate();
  
    const handleClickOpen =() => {
      setOpen(true);
    };
  
    const handleClose = () => {
      setOpen(false);
      navigator('/admin-home');
    };
  
    const loggedInUser = getLoggedInUser();
  
    const handleDelete = (event) => {
      event.preventDefault();
  
      if(confirmation === loggedInUser){
        deleteMerchantCall(merchantId).then( (response) => {
            console.log(response)
        }).catch( (error) =>{
            console.log(error)
        });
  
        setOpen(false);
        setMerchantId('');
  
        navigator('/admin-home');
      }else{
        alert("Id Don't Match")
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
                                <label className='col-md-3 control-label'> Enter Merchant Id To Delete </label>
                                <div className='col-md-9'>
                                    <input
                                        type='text'
                                        name='merchantId'
                                        className='form-control'
                                        placeholder='Enter Merchant Id To Delete'
                                        value={merchantId}
                                        onChange = { (e) => setMerchantId(e.target.value) }
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
                                      handleClose();
                                    },
                                  }}
                                >
                                <DialogTitle>Delete Merchant With Id:{merchantId}</DialogTitle>
                                <DialogContent>
                                  <DialogContentText>
                                    Are You Sure Delete This Merchant.
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
                                  <Button type="button" onClick={handleDelete}>Delete</Button>
                                </DialogActions>
                              </Dialog>
                            </div>
  
                        </form>
                    </div>
                </div>
            </div>
        </div>
  
    </div>
    )
}

export default DeleteMerchant;

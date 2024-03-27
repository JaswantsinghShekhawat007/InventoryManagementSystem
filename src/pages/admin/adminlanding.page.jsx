import * as React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import AllProducts from '../../component/product/allproduct/allproducts.component';
import AllMerchant from '../../component/merchant/allmerchant.component';
import UpdateAdmin from './updateadmin/updateadmin';
import DeleteMerchant from '../merchantLanding/deletemerchant/deletemerchant.component';


const CustomTabPanel =    (props) => {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`simple-tabpanel-${index}`}
      aria-labelledby={`simple-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

CustomTabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    'aria-controls': `simple-tabpanel-${index}`,
  };
}

export default function AdminLanding() {
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Box sx={{ width: '100%' }}>
      
      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
        
        <Tabs
            value={value} 
            onChange={handleChange} 
            aria-label="Merchant Tabs" 
            centered
        >
          
            <Tab label="All Products" {...a11yProps(0)} />
            <Tab label="All Merchants" {...a11yProps(1)} />
            <Tab label="Update Admin" {...a11yProps(2)} />
            <Tab label="Delete Merchant" {...a11yProps(3)} />

        </Tabs>
      </Box>
      
      <CustomTabPanel value={value} index={0}>
        <AllProducts />
      </CustomTabPanel>
      <CustomTabPanel value={value} index={1}>
        <AllMerchant />
      </CustomTabPanel>
      <CustomTabPanel value={value} index={2}>
        <UpdateAdmin />
      </CustomTabPanel>
      <CustomTabPanel value={value} index={3}>
        <DeleteMerchant />
      </CustomTabPanel>
    </Box>
  );
}

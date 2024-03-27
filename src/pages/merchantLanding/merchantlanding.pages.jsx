import * as React from 'react';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import AllProductById from '../../component/product/allproduct/allproductById.component';
import AddProduct from '../../component/product/addproduct/addproduct.component';
import UpdateProduct from '../../component/product/updateproduct/updateproduct.component';
import DeleteProduct from '../../component/product/deleteproduct/deleteproduct.component';


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

export default function MerchantLanding() {
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
          
            <Tab label="All Product" {...a11yProps(0)} />
            <Tab label="Add Product" {...a11yProps(1)} />
            <Tab label="Update Product" {...a11yProps(2)} />
            <Tab label="Delete Product" {...a11yProps(3)} />

        </Tabs>
      </Box>
      
      <CustomTabPanel value={value} index={0}>
        <AllProductById />
      </CustomTabPanel>
      <CustomTabPanel value={value} index={1}>
        <AddProduct />
      </CustomTabPanel>
      <CustomTabPanel value={value} index={2}>
        <UpdateProduct />
      </CustomTabPanel>
      <CustomTabPanel value={value} index={3}>
        <DeleteProduct />
      </CustomTabPanel>
    </Box>
  );
}

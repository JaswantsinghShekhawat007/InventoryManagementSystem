import React, { useEffect, useState } from 'react';

import './allproduct.styles.scss';
import SearchBox from '../../search-box/search-box.component';
import ProductCard from '../../product-card/product-card.component';
import { getAllProducts } from '../../../services/AdminServices';

const AllProducts = () => {
  const [products, setProducts] = useState([]);
  const [searchField, setSearchField ] = useState('');
  const [filteredProducts, setFilteredProducts] = useState(products);

  useEffect( () => {
      const newfilteredProducts = products.filter( (product) => {
        return product.id.toLowerCase().includes(searchField); 
      });
        setFilteredProducts(newfilteredProducts);
    }
    , [products,searchField ] 
  );

  const onSearchChange = (event) => {
    const searchString = event.target.value.toLowerCase();
    setSearchField(searchString);
  }

  useEffect( () => {
    listProducts();
  } ,[]);

  const listProducts = () => {
    getAllProducts().then( (response) => {
      console.log(response.data); 
      setProducts(response.data);
    }).catch( (error) => {
      console.log(error);
    });
  }

  return(
    <div>
        <SearchBox 
          className='search-box' 
          placeholder='Search Product By Id' 
          onChangeHandler={onSearchChange} 
        />

        <div className="products-container">
            {
                filteredProducts.map( (product) => (
                    <ProductCard key={product.id} product={product} />
                ))
            }
        </div>
    </div>
  );
}

export default AllProducts;

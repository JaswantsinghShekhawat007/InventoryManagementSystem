import React, { useEffect, useState } from 'react';
import ProductCard from '../../product-card/product-card.component';

import './allproduct.styles.scss';
import { getAllProductsById } from '../../../services/ProductServices';

const AllProductById = () => {
  const [products, setProducts] = useState([]);

  useEffect( () => {
    listProducts();
  } ,[]);

  const listProducts = () => {
    getAllProductsById().then( (response) => {
      console.log(response.data); 
      setProducts(response.data);
    }).catch( (error) => {
      console.log(error);
    });
  }

  return(
      <div className="products-container">
          {
              products.map( (product) => (
                  <ProductCard key={product.id} product={product} />
              ))
          }
      </div>
  );
}

export default AllProductById;

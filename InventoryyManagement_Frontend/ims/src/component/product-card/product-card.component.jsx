import React from 'react';
import './product-card.styles.scss';
import Button from '../button/button.component';


const ProductCard = ( {product} ) => {

    const {id,name, urls, price, quantity, tag} = product;

    return(
        <div className='product-card-container'>
            <img src={urls} alt={`${name}`} />
            
            <div className='tag'>
                <span className='name'>{tag}</span>
            </div>

            <div className='footer'>
                <span className='name'>{name}</span>
                <span className='price'>{price}</span>
                <span className='quantity'>{quantity}</span>
            </div>

            <Button buttonType="inverted"> 
                <span className='name'>{id}</span>
            </Button>
        </div>
    );
};

export default ProductCard;
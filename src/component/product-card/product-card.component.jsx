import React, { useContext } from 'react';
import './product-card.styles.scss';
import Button from '../button/button.component';
import { ShowProductContext } from '../../contexts/showproduct.context';
import { useNavigate } from 'react-router-dom';


const ProductCard = ( {product} ) => {

    const {id, name, urls, price, quantity, tag} = product;
    const { addProductToShow } = useContext( ShowProductContext );

    const navigator = useNavigate();

    const handleClick = () => {
        addProductToShow(product);
        navigator("/product-page");
    }

    return(
        <div className='product-card-container'>
            <img src={urls[0]} alt={`${name}`} />
            
            <div className='tag'>
                <span className='name'>{tag}</span>
            </div>

            <div className='footer'>
                <span className='name'>{name}</span>
                <span className='price'>{price}</span>
                <span className='quantity'>{quantity}</span>
            </div>

            <Button buttonType="inverted" onClick={handleClick} > 
                <span className='name'>{id}</span>
            </Button>
        </div>
    );
};

export default ProductCard;
import React, { useContext } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import CustomCarousel from '../../component/carousel/carousel.component';
import { ShowProductContext } from '../../contexts/showproduct.context';

import './product.scss';

const Product = () => {
    const { productToShow } = useContext( ShowProductContext );
    const { name, description, tag, price, discount, quantity, urls } = productToShow;

    const finalPrice = () => {
        return price-(price*(discount/100));
    }

  return (
    <Container className='col-lg-8 offset-2'>
        <Row className='containerself' >
        <Col xs={12} md={8}>
          <CustomCarousel className='imagecomp' urls={urls} name={name} tag={tag}/>
        </Col>
        <Col className='product-detail' xs={6} md={4}>
          
          <div className='prodname'>
            <h1>{name}</h1>
          </div>
          <br></br>
          <div className='description'>
            <h3>Description: </h3>
            <h5>{description}</h5>
          </div>
          <br></br>
          <br></br>
          <div className='detail'>
            <p> MRP: {price} </p>
            <p> Discount: {discount} </p>
            <h6> Final Price: {finalPrice()} </h6>
            <h5> Quantity: {quantity} </h5>
          </div>


        </Col>
        </Row>
    </Container>
  )
}

export default Product;

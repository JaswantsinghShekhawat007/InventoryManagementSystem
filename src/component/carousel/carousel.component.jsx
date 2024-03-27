import React from 'react';
import Carousel from 'react-bootstrap/Carousel';

import './carousel.scss';

const CustomCarousel = ({urls, name, tag}) => {
  return (
    <Carousel className='carouselimg'>
        {
            urls.map( (url) => {

                return(
                    <Carousel.Item interval={2000}>
                        <img src={url} alt={`${name}`} />
                        <Carousel.Caption>
                        <h3 className='imgtag'>{tag}</h3>
                        </Carousel.Caption>
                    </Carousel.Item>
                )
            })
        }
    </Carousel>
  );
}

export default CustomCarousel;

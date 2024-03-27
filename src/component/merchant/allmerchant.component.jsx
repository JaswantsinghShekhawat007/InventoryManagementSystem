import React, { useEffect, useState } from 'react';
import { getAllMerchants } from '../../services/AdminServices';
import CardList from '../card-list/card-list.component';

const AllMerchant = () => {

    const [merchants, setMerchants] = useState([])

    useEffect( () => {
        listMerchants();
      } ,[]);

    const listMerchants = () => {
        getAllMerchants().then( (response) => {
          console.log(response.data); 
          setMerchants(response.data);
        }).catch( (error) => {
          console.log(error);
        });
      }

    return (
        <div>
            <CardList merchants={merchants} />
        </div>
    )
}

export default AllMerchant;

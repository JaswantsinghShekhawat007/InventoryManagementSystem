import { createContext, useState } from "react";


export const ShowProductContext = createContext({
    productToShow: [],
    addProductToShow: () => {}
});

export const ShowProductProvider = ( {children} ) => {

    const [ productToShow, setProductToShow ] = useState([]);

    const addProductToShow = (product) => {
        setProductToShow(product);
    }

    const value = { productToShow, addProductToShow };
    return <ShowProductContext.Provider value={value}> {children} </ShowProductContext.Provider>
};
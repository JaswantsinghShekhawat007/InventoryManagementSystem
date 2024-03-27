import axios from 'axios';
import { getLoggedInUser } from './AuthService';

const AUTH_REST_API_BASE_URL = "http://localhost:8085/api/user-product";


export const addProductCall = (productObj) => axios.post(AUTH_REST_API_BASE_URL+'/add', productObj);

export const updateProductCall = (productId, productObj) => axios.put(AUTH_REST_API_BASE_URL+`/update/${productId}`,productObj)

const userId = getLoggedInUser();
export const getAllProductsById = () => axios.get(AUTH_REST_API_BASE_URL+`/products/${userId}`);

export const deleteProduct = (productId) => axios.delete(AUTH_REST_API_BASE_URL+`/delete/${productId}`);
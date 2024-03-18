import axios from 'axios';
import { getLoggedInUser } from './AuthService';

const AUTH_REST_API_BASE_URL = "http://localhost:8085/api/admin";


export const getAllProducts = () => axios.get(AUTH_REST_API_BASE_URL+`/getAllProduct`);

export const getAllMerchants = () => axios.get(AUTH_REST_API_BASE_URL+`/getAllMerchant`);

const adminId = getLoggedInUser();
export const updateAdminCall = (adminObj)  => axios.put(AUTH_REST_API_BASE_URL+`/update/${adminId}`,adminObj);
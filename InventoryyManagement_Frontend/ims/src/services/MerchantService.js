import axios from 'axios';
import { getLoggedInUser } from './AuthService';

const AUTH_REST_API_BASE_URL = "http://localhost:8085/api/merchant";

const merchantId = getLoggedInUser();
export const updateMerchantCall = (merchantObj) => axios.put(AUTH_REST_API_BASE_URL+`/update-merchant/${merchantId}`,merchantObj);
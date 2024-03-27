import axios from "axios";

const AUTH_REST_API_BASE_URL = "http://localhost:8085/api/auth-admin";


export const deleteMerchantCall = (merchantId) => axios.delete(AUTH_REST_API_BASE_URL+`/delete-merchant/${merchantId}`);

export const deleteAdminCall = (adminId) => axios.delete(AUTH_REST_API_BASE_URL+`/delete/${adminId}`);

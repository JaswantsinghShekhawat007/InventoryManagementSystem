import axios from "axios";
import { getToken } from "./AuthService";

const AUTH_REST_API_BASE_URL = "http://localhost:8085/api/auth";

//Request interceptor
axios.interceptors.request.use(function (config) {
    
    config.headers['Authorization'] = getToken();

    return config;
    
}, function (error) {

    return Promise.reject(error);

});


//Managing Logged User Methods
export const registerAdminCall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL+'/admin/register', registerObj);




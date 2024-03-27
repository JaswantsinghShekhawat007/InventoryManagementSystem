import axios from 'axios';

const AUTH_REST_API_BASE_URL = "http://localhost:8085/api/auth";

//Auth Methods
export const registerMerchantCall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL+'/merchant/register', registerObj);

export const loginApiCall = (loginObj) => axios.post(AUTH_REST_API_BASE_URL+'/login', loginObj);




//Token Methods
export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");



export const saveLoggedInUser = (userId, role) => {

    sessionStorage.setItem("authenticatedUser", userId);
    sessionStorage.setItem("role", role)

}

export const isUserLoggedIn = () => {
    const  userId = sessionStorage.getItem("authenticatedUser");

    if(userId == null) return false;
    else return true;
    
};

export const getLoggedInUser = () => sessionStorage.getItem("authenticatedUser");

export const logout = () => {
    localStorage.clear();
    sessionStorage.clear();
};

export const isAdminUser = () =>{

    let role = sessionStorage.getItem("role"); 

    if( role!=null && role === 'ROLE_ADMIN') return true;
    else return false;
}
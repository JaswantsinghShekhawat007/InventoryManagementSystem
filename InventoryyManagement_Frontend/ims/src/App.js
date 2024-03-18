  import { Navigate, Route, Routes } from 'react-router-dom';
import './App.css';

import Home from './pages/home/home.component';
import RegisterMerchant from './pages/register/register.component';
import Login from './pages/login/login.component';
import Navigation from './component/navigation/navigation.component';
import RegisterAdmin from './pages/register/adminregister.component';
import MerchantLanding from './pages/merchantLanding/merchantlanding.pages';
import { isUserLoggedIn } from './services/AuthService';
import AdminLanding from './pages/admin/adminlanding.page';
import UpdateMerchant from './pages/merchantLanding/updatemerchant/updatemerchant';

function App() {

  const AuthenticatedRoute = ( { children } ) => {

    const isLoggedIn = isUserLoggedIn();

    if( isLoggedIn ) return children;
    else return <Navigate to="/" />

  }

  return (
    <Routes>

        <Route path='/' element={<Navigation />}  >
          

        <Route index element={<Home />} />

        {/* Auth Routes  */}
        <Route path='register' element={ <RegisterMerchant /> } />

        <Route path='register-admin' element={ 
          <AuthenticatedRoute>
            <RegisterAdmin /> 
          </AuthenticatedRoute>
          } />

        <Route path='login' element={ <Login /> } />

        {/* Merchant Routes */}
        <Route path='merchant-home' element={ 
          <AuthenticatedRoute>
            <MerchantLanding /> 
          </AuthenticatedRoute>
          } />

        <Route path='admin-home' element={ 
          <AuthenticatedRoute>
            <AdminLanding /> 
          </AuthenticatedRoute>
          } />

        <Route path='update-merchant' element={ 
          <AuthenticatedRoute>
            <UpdateMerchant /> 
          </AuthenticatedRoute>
          } />

      </Route>


    </Routes>
  );
}

export default App;

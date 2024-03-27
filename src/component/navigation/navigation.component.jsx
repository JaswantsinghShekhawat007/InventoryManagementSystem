import { Fragment } from "react";
import { Link, Outlet, useNavigate } from "react-router-dom";

import './navigation.styles.scss';
import { isAdminUser, isUserLoggedIn, logout } from "../../services/AuthService";

const Navigation = () => {

    const navigator = useNavigate();

    const isLoggedIn = isUserLoggedIn();
    const isAdmin = isAdminUser();

    const signOutUser = () => {
        logout();
        navigator("/");
    };

    return( 
        <Fragment>
            <div className="navigation">

                <Link className="logo-container" to='/'>
                    <h4>Inventory Management System</h4>
                </Link>

                <div className="nav-links-container">

                    {
                        isLoggedIn && !isAdmin &&
                        <Link className="nav-link" to='update-merchant'>
                            Update Data
                        </Link>
                    }

                    {   
                        !isLoggedIn &&    
                        <Link className="nav-link" to='register'>
                            SIGN UP
                        </Link>
                    }

                    {
                        (isLoggedIn && isAdmin) && 
                        <Link className="nav-link" to='admin-home'>
                                ADMIN-HOME
                        </Link> 
                    }

                    {
                        (isLoggedIn && !isAdmin) && 
                        <Link className="nav-link" to='merchant-home'>
                                MERCHANT-HOME
                        </Link> 
                    }

                    {
                        (isLoggedIn && isAdmin) && 
                        <Link className="nav-link" to='register-admin'>
                                ADD ADMIN
                        </Link> 
                    }

                    {
                        isLoggedIn ? ( 
                            <span className="nav-link" onClick={signOutUser}>
                                SIGN OUT
                            </span>
                        ) : (
                            <Link className="nav-link" to='login'>
                                SIGN IN
                            </Link>
                        )
                    }

                </div>
            
            </div>
            <Outlet />
        </Fragment>
    );
};

export default Navigation;
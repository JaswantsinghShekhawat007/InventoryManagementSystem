import { Component } from "react";

import "./search-box.styles.scss";

class SearchBox extends Component{

    render() {

        const { onChangeHandler, placeholder, className } = this.props

        return(

            <div>
                
                <input 
                    className={className} 
                    type='search' 
                    placeholder={placeholder} 
                    onChange={ onChangeHandler }
                />

            </div>


        );

    }

}

export default SearchBox;
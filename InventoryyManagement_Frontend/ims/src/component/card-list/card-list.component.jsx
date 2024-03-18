import Card from '../card/card.component';
import './card-list.styles.scss';

const CardList = ({ merchants }) => {
    return (
        
        <div  className="card-list">
            
            {merchants.map( (merchant) => { 
                
                return(
                    <div key={merchant.merchantId}>
                        <Card merchant={merchant} />
                    </div>
                )})}

        </div>
    );
}

export default CardList;
import './card.styles.scss';

const Card = ({merchant}) => {

    const { name, contactNo } = merchant;

    return (

        <div className="glass card-container"> 
            <img 
                alt={`Merchant ${name}`}  
                src={`https://api.dicebear.com/8.x/notionists/svg?seed=${name}&scale=160&backgroundType=gradientLinear&backgroundColor=d1d4f9,ffd5dc,b6e3f4,c0aede,ffdfbf`}            
            />
            <h2>{name}</h2>
            <h3>{contactNo}</h3>
        </div>
    );
}

export default Card;
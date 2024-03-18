import './button.styles.scss';

const BUTTON_TYPE_CLASSES = {
    inverted: 'inverted'
}

const Button = ( { children, buttonType, ...OtherProps } ) => {
    return (
        <button 
            className={`button-container ${BUTTON_TYPE_CLASSES[buttonType]}`}
            {...OtherProps}
        >
            {children}
        </button>
    );
};

export default Button;
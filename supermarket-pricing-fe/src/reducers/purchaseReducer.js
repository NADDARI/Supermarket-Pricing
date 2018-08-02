import update from 'immutability-helper'


const purchaseReducer = (state = {}, action) => {
    switch (action.type) {
        case 'ADD_PRODUCT':
            return update(state, {
                cart: {$apply: cart => incrementProduct(cart, action.product)},
                pricing: {$remove: [action.product]}
            })
        case 'REMOVE_PRODUCT':
            return update(state, {
                cart: {$apply: cart => decrementProduct(cart, action.product)},
                pricing: {$remove: [action.product]}
            })
        case 'CHECKOUT_FULFILLED':
            return update(state, {
                pricing: {$set: new Map(Object.entries(action.payload))}
            })
        case 'INIT_FULFILLED':
            return update(state, {
                products: {$set: action.payload}
            })
        case 'EMPTY_CART':
            return update(state, {
                cart: {$set: []},
                pricing: {$set: new Map()}
            })
        default:
            return state
    }
};

const incrementProduct = (cart, product) => {
    let index = cart.findIndex(p => p.item === product)
    if (index === -1) {
        return update(cart, {
            $push: [{item: product, quantity: 1}]
        });
    } else {
        return update(cart, {
            [index]: {
                quantity: {$set: cart[index].quantity + 1}
            }
        });
    }
};

const decrementProduct = (cart, product) => {
    let index = cart.findIndex(p => p.item === product)
    if (index !== -1 && cart[index].quantity > 1) {
        return update(cart, {
            [index]: {
                quantity: {$set: cart[index].quantity - 1}
            }
        });
    } else if (index !== -1 && cart[index].quantity > 0) {
        return cart.filter(p => p.item !== product)
    }
    return cart
};

export default purchaseReducer
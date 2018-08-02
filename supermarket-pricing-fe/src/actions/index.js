import axios from 'axios'

const API_PRICING = 'http://localhost:8080/supermarket/pricing/';
const INIT = 'init';
const DISCOUNT = 'discount';

export const addProduct = product => ({
    type: 'ADD_PRODUCT',
    product
})

export const removeProduct = product => ({
    type: 'REMOVE_PRODUCT',
    product
})

export const checkoutCart = (dispatch, cart) =>
    dispatch({
        type: 'CHECKOUT',
        payload: axios.post(API_PRICING + DISCOUNT, cart)
            .then(({data}) => data)
            .catch(err => err)
    })

export const initProducts = (dispatch) =>
    dispatch({
        type: 'INIT',
        payload: axios.post(API_PRICING + INIT)
            .then(({data}) => data)
            .catch(err => err)
    })

export const emptyCart = {
    type: 'EMPTY_CART'
}
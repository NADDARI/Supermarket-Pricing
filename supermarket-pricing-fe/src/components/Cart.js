import React, {Component} from 'react';
import {Button, Card, Icon, Tooltip} from 'antd';
import {connect} from 'react-redux';
import {checkoutCart, emptyCart} from '../actions/index'

@connect(store => ({
    cart: store.cart,
    pricing: store.pricing
}))
class Cart extends Component {
    state = {
        showPrices: false
    }

    cancel = () => this.props.dispatch(emptyCart)
    checkout = () => checkoutCart(this.props.dispatch, this.props.cart)

    render() {
        const Purchases = ({cart, pricing}) => {
            if (cart.length > 0) {
                return cart.map(purchase => <Card.Meta key={`Product${purchase.item}`}
                                                       description={`Product: ${purchase.item}, Quantity: ${purchase.quantity}, Price: ${pricing.has(purchase.item) ? pricing.get(purchase.item) : '-'} `}/>)
            }
            return 'No product selected'
        }
        return (
            <Card title="CART" bordered={false}
                  actions={[
                      <Tooltip placement="bottom" title={"Clear Cart"}>
                          <Button onClick={this.cancel}><Icon type="close"/></Button>
                      </Tooltip>,
                      <Tooltip placement="bottom" title={"Apply Discount"}>
                          <Button onClick={this.checkout}><Icon type="check"/></Button>
                      </Tooltip>]}
            >
                <Purchases cart={this.props.cart} pricing={this.props.pricing}/>
            </Card>
        );
    }
}

export default Cart
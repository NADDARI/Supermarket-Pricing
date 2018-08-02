import React, {Component} from 'react';
import './App.css';
import {Badge, Button, Card, Icon} from 'antd';
import 'antd/dist/antd.css';
import {connect} from 'react-redux';
import {addProduct, removeProduct} from '../actions/index'

@connect(store => ({cart: store.cart}))
class Product extends Component {

    add = item => this.props.dispatch(addProduct(item));
    remove = item => this.props.dispatch(removeProduct(item));

    render() {
        const getCount = (cart, item) => {
            let purchase = cart.find(p => p.item === item)
            if (purchase === undefined) {
                return 0
            }
            return purchase.quantity
        }

        return (
            <div>
                <Badge count={getCount(this.props.cart, this.props.item)} style={{backgroundColor: '#52c41a'}}>
                    <Card title={`Product: ${this.props.item}`} bordered={false}
                          actions={[
                              <Button onClick={() => this.remove(this.props.item)}><Icon type="minus"/></Button>,
                              <Button onClick={() => this.add(this.props.item)}><Icon type="plus"/></Button>]}
                    >
                        <Card.Meta description={this.props.meta}/>
                    </Card>
                </Badge>
            </div>
        );
    }
}

export default Product
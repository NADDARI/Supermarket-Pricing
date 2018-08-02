import React, {Component} from 'react';
import './App.css';
import {Col, Row} from 'antd';
import 'antd/dist/antd.css';
import Product from './Product'
import Cart from './Cart'
import {connect} from 'react-redux';
import {initProducts} from '../actions/index'


@connect(store => ({products: store.products}))
class App extends Component {
    constructor(props) {
        super(props)
        initProducts(this.props.dispatch)
    }

    render() {
        const Products = ({products}) => Object.entries(products).map(([item, price]) =>
            <Col span={8} key={`P_${item}`}>
                <Product item={item} meta={`Price : ${price}`}/>
            </Col>
        )

        return (
            <Row>
                <Col span={6} push={18}>
                    <Cart/>
                </Col>
                <Col span={18} pull={6}>
                    <div style={{background: '#5D6D7E', padding: '30px'}}>
                        <Row>
                            <Products products={this.props.products}/>
                        </Row>
                    </div>
                </Col>

            </Row>
        );
    }
}

export default App;

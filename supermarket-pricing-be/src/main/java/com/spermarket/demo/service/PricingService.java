package com.spermarket.demo.service;

import com.spermarket.demo.domain.Purchase;

import java.util.List;
import java.util.Map;

/**
 * File PricingService <br>
 * 26/07/2018 <br>
 * Author "Mohamed Naddari" <br>
 * Description of PricingService : <br>
 * <p>
 * <p>
 * This copyright notice should not be removed <br>
 **/
public interface PricingService {

    Map<String, Double> cartPrice(List<Purchase> purchases);

    Map<String, Double> unitsPrices();
}

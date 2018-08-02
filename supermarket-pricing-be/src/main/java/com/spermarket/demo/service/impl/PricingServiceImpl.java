package com.spermarket.demo.service.impl;

import com.spermarket.demo.dao.PricingDao;
import com.spermarket.demo.domain.DiscountRule;
import com.spermarket.demo.domain.Purchase;
import com.spermarket.demo.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File PricingServiceImpl <br>
 * 26/07/2018 <br>
 * Author "Mohamed Naddari" <br>
 * Description of PricingServiceImpl : <br>
 * <p>
 * <p>
 * This copyright notice should not be removed <br>
 **/
@Service
public class PricingServiceImpl implements PricingService {

    private PricingDao pricingDao;

    @Override
    public Map<String, Double> cartPrice(List<Purchase> purchases) {
        HashMap<String, Double> cart = new HashMap<>();
        List<DiscountRule> rules = pricingDao.getRules();
        purchases.forEach(purchase -> cart.merge(purchase.getItem(), discountPrice(rules, purchase), (a, b) -> a + b));
        return cart;
    }

    @Override
    public Map<String, Double> unitsPrices() {
        Map<String, Double> unitsPrices = new HashMap<>();
        List<DiscountRule> rules = pricingDao.getRules();
        rules.forEach(rule -> unitsPrices.put(rule.getItem(), rule.getPrice()));
        return unitsPrices;
    }

    private Double discountPrice(List<DiscountRule> rules, Purchase purchase) {
        DiscountRule rule = rules.stream().filter(discountRule -> discountRule.getItem().equals(purchase.getItem())).findFirst().orElse(null);
        if (rule == null) {
            // return -1 if product pricing is not found
            return -1.0;
        }
        return purchase.getQuantity() / rule.getDiscountQuantity() * rule.getDiscountPrice() + purchase.getQuantity() % rule.getDiscountQuantity() * rule.getPrice();
    }

    @Autowired
    public void setPricingDao(PricingDao pricingDao) {
        this.pricingDao = pricingDao;
    }
}

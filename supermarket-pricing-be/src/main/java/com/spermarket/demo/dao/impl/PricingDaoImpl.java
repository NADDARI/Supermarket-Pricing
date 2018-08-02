package com.spermarket.demo.dao.impl;

import com.spermarket.demo.dao.PricingDao;
import com.spermarket.demo.domain.DiscountRule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * File PricingDaoImpl <br>
 * 26/08/2018 <br>
 * Author "Mohamed Naddari" <br>
 * Description of PricingDaoImpl : <br>
 * <p>
 * <p>
 * This copyright notice should not be removed <br>
 **/

@Component
public class PricingDaoImpl implements PricingDao {

    @Override
    public List<DiscountRule> getRules() {
        // no discount on product A
        DiscountRule rule1 = new DiscountRule("A", 0.65, 1, 0.65);
        // 3 for 1 dollar
        DiscountRule rule2 = new DiscountRule("B", 0.6, 3, 1.0);
        // buy 2 get 1 for free
        DiscountRule rule3 = new DiscountRule("C", 1.0, 3, 2.0);

        return Stream.of(rule1, rule2, rule3).collect(Collectors.toList());
    }
}

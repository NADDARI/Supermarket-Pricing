package com.spermarket.demo.dao;

import com.spermarket.demo.domain.DiscountRule;

import java.util.List;

/**
 * File PricingDao <br>
 * 26/08/2018 <br>
 * Author "Mohamed Naddari" <br>
 * Description of PricingDao : <br>
 * <p>
 * <p>
 * This copyright notice should not be removed <br>
 **/
public interface PricingDao {
    List<DiscountRule> getRules();
}

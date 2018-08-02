package com.spermarket.demo.controller;

import com.spermarket.demo.domain.Purchase;
import com.spermarket.demo.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * File PricingController <br>
 * 26/07/2018 <br>
 * Author "Mohamed Naddari" <br>
 * Description of PricingController : <br>
 * <p>
 * <p>
 * This copyright notice should not be removed <br>
 **/

@RestController
@RequestMapping("/pricing")
@CrossOrigin(origins = "*")
public class PricingController {

    private PricingService pricingService;

    @RequestMapping(value = "/discount", method = RequestMethod.POST)
    public Map<String, Double> getDiscountPrice(@RequestBody List<Purchase> purchases) {
        return pricingService.cartPrice(purchases);
    }

    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public Map<String, Double> getInitialPrices() {
        return pricingService.unitsPrices();
    }

    @Autowired
    public void setPricingService(PricingService pricingService) {
        this.pricingService = pricingService;
    }
}

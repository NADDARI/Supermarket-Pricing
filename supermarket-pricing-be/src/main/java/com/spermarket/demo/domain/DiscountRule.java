package com.spermarket.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * File DiscountRule <br>
 * 26/07/2018 <br>
 * Author "Mohamed Naddari" <br>
 * Description of DiscountRule : <br>
 * <p>
 * <p>
 * This copyright notice should not be removed <br>
 **/
@Getter
@Setter
@AllArgsConstructor
public class DiscountRule {
    private String item;
    private Double price;
    private Integer discountQuantity;
    private Double discountPrice;
}

package com.spermarket.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * File Purchase <br>
 * 27/07/2018 <br>
 * Author "Mohamed Naddari" <br>
 * Description of Purchase : <br>
 * <p>
 * <p>
 * This copyright notice should not be removed <br>
 **/

@Getter
@Setter
@AllArgsConstructor
public class Purchase {
    private String item;
    private int quantity;
}

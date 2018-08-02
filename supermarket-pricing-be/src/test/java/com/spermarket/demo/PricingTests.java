package com.spermarket.demo;

import com.spermarket.demo.dao.PricingDao;
import com.spermarket.demo.domain.DiscountRule;
import com.spermarket.demo.domain.Purchase;
import com.spermarket.demo.service.PricingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PricingTests {

    @Autowired
    private PricingService pricingService;

    @MockBean
    private PricingDao pricingDao;

    @Test
    public final void xUnitsForYRule() {
        // 3 for 1 dollar
        DiscountRule rule = new DiscountRule("A", 0.6, 3, 1.0);
        when(this.pricingDao.getRules()).thenReturn(Stream.of(rule).collect(Collectors.toList()));

        Purchase purchase = new Purchase("A", 3);
        List<Purchase> purchases = Stream.of(purchase).collect(Collectors.toList());

        Map<String, Double> expected = new HashMap<>();
        expected.put("A", 1.0);

        Assert.assertEquals(expected, pricingService.cartPrice(purchases));
    }

    @Test
    public final void buyXGetYForFreeRule() {
        // buy 2 get 1 for free
        DiscountRule rule = new DiscountRule("A", 1.0, 4, 2.0);
        when(this.pricingDao.getRules()).thenReturn(Stream.of(rule).collect(Collectors.toList()));

        Purchase purchase = new Purchase("A", 9);
        List<Purchase> purchases = Stream.of(purchase).collect(Collectors.toList());

        Map<String, Double> expected = new HashMap<>();
        expected.put("A", 5.0);

        Assert.assertEquals(expected, pricingService.cartPrice(purchases));
    }

    @Test
    public final void noDiscountRule() {
        // no discount
        DiscountRule rule = new DiscountRule("A", 1.0, 1, 1.0);
        when(this.pricingDao.getRules()).thenReturn(Stream.of(rule).collect(Collectors.toList()));

        Purchase purchase = new Purchase("A", 9);
        List<Purchase> purchases = Stream.of(purchase).collect(Collectors.toList());

        Map<String, Double> expected = new HashMap<>();
        expected.put("A", 9.0);

        Assert.assertEquals(expected, pricingService.cartPrice(purchases));
    }

    @Test
    public final void noRules() {
        //empty rules array
        when(this.pricingDao.getRules()).thenReturn(new ArrayList<>());

        Purchase purchase = new Purchase("A", 3);
        List<Purchase> purchases = Stream.of(purchase).collect(Collectors.toList());

        Map<String, Double> expected = new HashMap<>();
        expected.put("A", -1.0);

        Assert.assertEquals(expected, pricingService.cartPrice(purchases));
    }

    @Test
    public final void noPurchases() {
        Assert.assertEquals(new HashMap<>(), pricingService.cartPrice(new ArrayList<>()));
    }

    @Test
    public final void initialPricingWithoutDiscount() {
        DiscountRule rule1 = new DiscountRule("A", 1.0, 5, 4.0);
        DiscountRule rule2 = new DiscountRule("B", 1.5, 3, 4.0);
        when(this.pricingDao.getRules()).thenReturn(Stream.of(rule1, rule2).collect(Collectors.toList()));

        Map<String, Double> expected = new HashMap<>();
        expected.put("A", 1.0);
        expected.put("B", 1.5);

        Assert.assertEquals(expected, pricingService.unitsPrices());
    }
}

package com.spermarket.demo;

import com.spermarket.demo.dao.PricingDao;
import com.spermarket.demo.domain.DiscountRule;
import com.spermarket.demo.domain.Purchase;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingControllerTests {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private HttpHeaders headers = new HttpHeaders();

    @MockBean
    private PricingDao pricingDao;

    @Before
    public void setRules() {
        DiscountRule rule1 = new DiscountRule("A", 1.0, 3, 2.0);
        DiscountRule rule2 = new DiscountRule("K", 1.5, 3, 4.0);
        when(this.pricingDao.getRules()).thenReturn(Stream.of(rule1, rule2).collect(Collectors.toList()));
    }

    @Test
    public void initialPricesEndpoint() throws JSONException {
        HttpEntity entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/pricing/init"),
                HttpMethod.POST, entity, String.class);
        String expected = "{\"A\":1.0,\"K\":1.5}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void discountPricesEndpoint() throws JSONException {
        Purchase purchase1 = new Purchase("A", 3);
        Purchase purchase2 = new Purchase("K", 3);
        List<Purchase> purchases = Stream.of(purchase1, purchase2).collect(Collectors.toList());
        HttpEntity entity = new HttpEntity<>(purchases, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/pricing/discount"),
                HttpMethod.POST, entity, String.class);
        String expected = "{\"A\":2.0,\"K\":4.0}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


}

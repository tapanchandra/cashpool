package com.invia.interview.cashpoolapplication.services;

import com.invia.interview.cashpoolapplication.models.Expense;
import com.invia.interview.cashpoolapplication.models.Passbook;
import com.invia.interview.cashpoolapplication.models.Traveller;
import com.invia.interview.cashpoolapplication.models.Trip;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TripServiceTests {

    @Autowired
    TripService tripService;

    @Test
    public void testURLShortener() {
        String shortCode = tripService.generateNewTripCode(314159265L);
        Assert.assertEquals("Invalid Short Code Generated","kYqh1",shortCode);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testURLShortenerOnNegativeCode() {
        tripService.generateNewTripCode(-1L);
    }
}


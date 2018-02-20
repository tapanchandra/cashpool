package com.invia.interview.cashpoolapplication.controllers;

import com.invia.interview.cashpoolapplication.CashpoolApplicationTests;
import com.invia.interview.cashpoolapplication.models.Traveller;
import com.invia.interview.cashpoolapplication.models.dto.TravellerDto;
import com.invia.interview.cashpoolapplication.models.dto.TripDto;
import com.invia.interview.cashpoolapplication.models.dto.TripIdentificationDto;
import com.invia.interview.cashpoolapplication.services.TravellerService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@Ignore
public class IndexControllerTest extends CashpoolApplicationTests{

    @Autowired
    TravellerService travellerService;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private TripIdentificationDto trip = null;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        //Create a trip
        MvcResult result = mockMvc.perform(post("/trip/new").accept(MediaType.APPLICATION_JSON)).andReturn();
        String responseJson = result.getResponse().getContentAsString();

        Map<String,Object> tripProps  = JsonParserFactory.getJsonParser().parseMap(responseJson);
        String tripCode = String.valueOf(tripProps.get("tripCode"));
        String tripName = String.valueOf(tripProps.get("tripName"));
        TravellerDto traveller1 = TravellerDto.builder().name("ABC").email("abc@mail.com").build();
        TravellerDto traveller2 = TravellerDto.builder().name("XYZ").email("xyz@mail.com").build();
        TravellerDto traveller3 = TravellerDto.builder().name("PQW").email("pqw@mail.com").build();

//        travellerService.addTraveller(traveller1,tripCode);
//        travellerService.addTraveller(traveller2,tripCode);
//        travellerService.addTraveller(traveller3,tripCode);
    }

    @Test
    public void givenrooturl_returnindexview() throws Exception {
//        this.mockMvc
//                .perform(get("/"))
//                .andDo(print())
//                .andExpect(view().name("index"));
    }

    @Test
    public void testNewTripCreation() throws Exception {

        //mockMvc.perform()
    }


}

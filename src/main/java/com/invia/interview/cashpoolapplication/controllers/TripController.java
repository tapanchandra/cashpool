package com.invia.interview.cashpoolapplication.controllers;

import com.invia.interview.cashpoolapplication.exceptions.TripCodeEmptyException;
import com.invia.interview.cashpoolapplication.services.TravellerService;
import com.invia.interview.cashpoolapplication.services.TripService;
import com.invia.interview.cashpoolapplication.models.dto.ExpenseDto;
import com.invia.interview.cashpoolapplication.models.dto.TravellerDto;
import com.invia.interview.cashpoolapplication.models.dto.TripDto;
import com.invia.interview.cashpoolapplication.models.dto.TripIdentificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/trip")
public class TripController extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired TripService tripService;
    @Autowired TravellerService travellerService;

    @GetMapping("")
    public String getNewTrip() throws TripCodeEmptyException {
        throw new TripCodeEmptyException();
    }

    @PostMapping("/new")
    public @ResponseBody TripIdentificationDto createNewTrip() {
        logger.info("Creating a new trip.");
        return tripService.createNewTrip();
    }

    @GetMapping("/{tripCode}")
    public String loadExistingTrip(@PathVariable("tripCode") String tripCode, final Model model)
    {
        TripDto tripDto = tripService.getTripDtoByCode(tripCode);
        model.addAttribute("trip",tripDto);
        model.addAttribute("hasExpenses",tripDto.getExpenses().size() > 0);
        model.addAttribute("totalExpense",tripDto.getExpenses().stream().mapToDouble(value -> value.getAmount()).sum());
        model.addAttribute("haspassbook",tripDto.getPassbookEntries().size() > 0);
        return "trip";
    }

    @PostMapping("/{tripCode}/update")
    public String udpateExistingTrip(TripDto tripDto, @PathVariable("tripCode") String tripCode)
    {
        tripService.updateTrip(tripDto);
        return "redirect:/trip/" + tripCode;
    }

    @PostMapping("/{tripCode}/start")
    public String startExistingTrip(@PathVariable("tripCode") String tripCode)
    {
        TripDto tripDto = tripService.startTrip(tripCode);
        return "redirect:/trip/" + tripCode;
    }

    @PostMapping("/{tripCode}/finish")
    public String finishExistingTrip(@PathVariable("tripCode") String tripCode,Model model)
    {
        TripDto tripDto = tripService.finishTrip(tripCode);
        model.addAttribute("trip",tripDto);
        return "redirect:/trip/" + tripCode;
    }

    @PostMapping("/{tripCode}/traveller/add")
    public String addTraveller(@RequestParam Map<String,String> allRequestParams, @PathVariable("tripCode") String tripCode)
    {
        TravellerDto travellerDto = TravellerDto.builder().email(allRequestParams.get("email")).name(allRequestParams.get("name")).build();
        travellerService.addTraveller(travellerDto,tripCode);
        return "redirect:/trip/" + tripCode;
    }

    @PostMapping("/{tripCode}/traveller/{travellerId}/remove")
    public String excludeTraveller(@RequestParam Map<String,String> allRequestParams, @PathVariable("tripCode") String tripCode)
    {
        TravellerDto travellerDto = TravellerDto.builder().email(allRequestParams.get("email")).name(allRequestParams.get("name")).build();
        travellerService.excludeTraveller(travellerDto,tripCode);
        return "redirect:/trip/" + tripCode;
    }

    @PostMapping("/{tripCode}/expense/add")
    public String addExpense(@RequestParam Map<String,String> allRequestParams, @RequestParam String[] paidFor, @PathVariable("tripCode") String tripCode)
    {
        ExpenseDto expenseDto = ExpenseDto.builder()
                .trip(tripService.getTripIdentificationDtoByCode(tripCode))
                .expenseDescription(allRequestParams.get("expenseDescription"))
                .paidBy(travellerService.getTravellerDtoById(Long.parseLong(allRequestParams.get("paidBy"))))
                .paidFor(Arrays.stream(paidFor).map(id -> travellerService.getTravellerDtoById(Long.parseLong(id))).collect(Collectors.toList()))
                .amount(Double.parseDouble(allRequestParams.get("amount")))
                .build();

        tripService.addExpense(expenseDto,tripCode);
        return "redirect:/trip/" + tripCode;
    }

    @PostMapping("/{tripCode}/expense/{expenseId}/remove")
    public String removeExpense(@RequestParam Map<String,String> allRequestParams, @PathVariable("tripCode") String tripCode)
    {

//        TravellerDto travellerDto = TravellerDto.builder().email(allRequestParams.get("email")).name(allRequestParams.get("name")).build();
//        TravellerDto traveller = tripService.addTraveller(travellerDto,tripCode);
        return "redirect:/trip/" + tripCode;
    }
}

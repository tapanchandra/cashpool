package com.invia.interview.cashpoolapplication.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@RequestMapping("/")
public class IndexController extends WebMvcConfigurerAdapter {

    @GetMapping("")
    public String greeting() {
        return "index";
    }
}

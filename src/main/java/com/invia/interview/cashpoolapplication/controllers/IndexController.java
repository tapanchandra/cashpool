package com.invia.interview.cashpoolapplication.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@RequestMapping("/")
public class IndexController extends WebMvcConfigurerAdapter {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("")
    public String index() {
        return "index";
    }
}

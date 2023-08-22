package com.pureplate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class DashboardController {

  @GetMapping("/")
  public String index(Model model) {
    model.addAttribute("greeting", "Welcome to our dynamic website!");
    return "index";
  }
}

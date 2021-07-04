package com.computergurukul.covidtracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.computergurukul.covidtracker.services.CoronaVirusDataServices;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataServices coronaVirusDataServices;
	@GetMapping("/")
	public String home(Model theModel) {
		theModel.addAttribute("locationStats",coronaVirusDataServices.getAllStats());
		theModel.addAttribute("welcome","COVID 19 TRACKER");
		return "home";
		
	}
}

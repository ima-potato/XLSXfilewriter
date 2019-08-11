package com.lilo.liloproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AngularController {
	@RequestMapping(value = {"/*.js", "/*.bundle.*", "/assets/*"})
	public String redirectAssets(HttpServletRequest request) {
		System.out.println("HERE 1");

		return "forward:/client" + request.getRequestURI();
	}

	@RequestMapping(value = {"", "/", "/view-timesheet", "/kiosk", "/guard-dashboard"})

	public String redirect() {

		System.out.println("HERE 2");

		return "forward:/client/index.html";
	}
}

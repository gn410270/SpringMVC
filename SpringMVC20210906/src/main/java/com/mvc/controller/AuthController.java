package com.mvc.controller;


import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.exception.AuthException;

@Controller
@RequestMapping(value = "/auth")
public class AuthController {
	@RequestMapping(value = "/")
	@ResponseBody
	public String welcome() {
		int n = new Random().nextInt(3);
		switch (n) {
		case 1:
			throw new AuthException(new Date(),"auth error...");
		case 2:
			throw new NullPointerException("other error...");
		}

		return "Welcome";
	}
	
}

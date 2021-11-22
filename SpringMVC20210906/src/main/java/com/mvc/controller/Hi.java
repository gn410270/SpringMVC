package com.mvc.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/hi")
public class Hi {
	//@RequestMapping(value = "/greet",produces="text/plain;charset=UTF-8")
	//@RequestMapping(value = "/greet")
	@RequestMapping(value = "/{welcome}", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String greet(@PathVariable("welcome") String welcome,
						@RequestParam("name") String name) {
		// �ѨM���|������D
		// �w�]���s�X�O�G ISO-8859-1
		// ���ܽs�X�G UTF-8
		try {
			welcome = new String(welcome.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return welcome+" "+name+" !";
	}
	@GetMapping(value = "/sayhi")
	public ModelAndView sayhi() {
		ModelAndView mav = new ModelAndView();
		//mav.setViewName("/WEB-INF/view/sayhi.jsp");
		mav.setViewName("sayhi");  //�� springmvc-servlet.xml �����w�q ViewResolver ���Ҫ��g�k
		mav.addObject("username", "John");
		return mav;
	}
	
	@GetMapping(value = "/sayhi2")
	public String sayhi2(Model model) {
		model.addAttribute("username", "Mary");
		return "sayhi";  // �����^�Ǫ��N�O view ���W�r(�r��)
	}
	
}

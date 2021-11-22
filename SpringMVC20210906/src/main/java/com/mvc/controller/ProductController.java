package com.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mvc.entity.products.Product;
import com.mvc.service.ProductService;

@Controller
@RequestMapping(value = "/product")
@SessionAttributes(value = {"groups","products","sizes","levels"})
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = {"/","input"})
	public String index(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("groups", productService.groups.values());
		model.addAttribute("products", productService.query());
		model.addAttribute("sizes", productService.sizes.values());
		model.addAttribute("levels", productService.levels.values());
		model.addAttribute("action", "save");
		
		return "product";
	}
	
	@PostMapping(value = "/save")
	//@RequestMapping(value = "/save", method = RequestMethod.POST)
	//�Y�n�ϥ� JSR 303 ���ҫh�n�b���Ҹ�ƼҲիe���[�W @Valid �׹�
	public String save(@Valid Product product,BindingResult result,Model model) {
		if(result.hasErrors()) { //�O�_�o�Ϳ��~
			model.addAttribute("action", "save");
			return "product"; //�N���~��T�a�����w jsp ����
		}
		productService.save(product);
		return "redirect:/mvc/product/";
	}
	
	@GetMapping(value = "/get/{name}")
	public String get(@PathVariable(value = "name") String name, Model model) {
		Product product = productService.get(name);
		model.addAttribute("product",product );
		model.addAttribute("action", "update");
		
		return "product";
	}
	
	@PostMapping(value = "/update")
	public String update(@Valid Product product,BindingResult result,Model model) {
		if(result.hasErrors()) {
			model.addAttribute("product",product );
			model.addAttribute("action", "update");
			return "product";
		}
		productService.update(product);
		return "redirect:/mvc/product/";
	}
	
	@GetMapping(value = "/delete/{name}")
	public String delete(@PathVariable(value = "name") String name) {
		productService.delete(name);
		return "redirect:/mvc/product/";
	}
	
	//�N����ର json �榡��~�o�G
	@GetMapping(value = "/query_json",produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Product> query(){
		return productService.query();
	}
	
}

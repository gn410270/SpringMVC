package com.mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.entity.Exam;
import com.mvc.entity.ExamName;
import com.mvc.validator.ExamValidator;

@Controller
@RequestMapping(value = "/exam")
public class ExamController {

	private static List<Exam> exams = new CopyOnWriteArrayList<Exam>();
	private static List<ExamName> examNames = new ArrayList<>();
	static {
		examNames.add(new ExamName("808","1Z0-808"));
		examNames.add(new ExamName("809","1Z0-809"));
		examNames.add(new ExamName("900","1Z0-900"));
		examNames.add(new ExamName("819","1Z0-819"));
	}
	
	
	@Autowired
	private ExamValidator examValidator;

	@RequestMapping(value = "/") // "/" 跟 "/index" 都可
	public String index(Model model) {
		Exam e = new Exam();
		// e.setId("101");
		// e.setName("809");
		// e.setSlot(new String[] {"A","C"});
		// e.setPay("true");
		// e.setNote("Thanks...");
		model.addAttribute("exam", e); // 給表單使用
		model.addAttribute("exams", exams); // 給資料呈現使用
		model.addAttribute("action", "create");
		model.addAttribute("examNames",examNames);
		
		// 統計資料
		// 1.各科考試報名人數
		Map<String, Long> stat1 = exams.stream().collect(Collectors.groupingBy(Exam::getName, Collectors.counting()));
		// 2.考試付款狀況
		Map<String, Long> stat2 = exams.stream().collect(Collectors.groupingBy(Exam::getPay, Collectors.counting()));

		model.addAttribute("stat1", stat1);
		model.addAttribute("stat2", stat2);
		return "exam";
	}

	// CRUD create,read,update,delete
	@RequestMapping(value = "/create")
	public String create(Exam exam, BindingResult result, Model model) {
		// 驗證 exam 物件
		examValidator.validate(exam, result);
		// 驗證結果是否有錯誤 ?
		if (result.hasErrors()) {
			model.addAttribute("exams", exams); // 給資料呈現使用
			model.addAttribute("action", "create");
			// 統計資料
			model.addAttribute("stat1", getStat1());
			model.addAttribute("stat2", getStat2());
			return "exam";
		}
		exams.add(exam);
		return "redirect:/mvc/exam/"; // 重導到首頁
	}

	@RequestMapping(value = "/get/{id}")
	public String get(@PathVariable("id") String id, Model model) {
		Optional<Exam> optExam = exams.stream().filter(e -> e.getId().equals(id)).findFirst();
		model.addAttribute("exam", optExam.isPresent() ? optExam.get() : new Exam()); // 給表單使用
		model.addAttribute("exams", exams); // 給資料呈現使用
		model.addAttribute("action", "update");

		model.addAttribute("stat1", getStat1());
		model.addAttribute("stat2", getStat2());

		return "exam";
	}

	@RequestMapping(value = "/update")
	public String update(Exam exam) {
		Optional<Exam> optExam = exams.stream().filter(e -> e.getId().equals(exam.getId())).findFirst();
		if (optExam.isPresent()) {
			Exam oExam = optExam.get();
			oExam.setName(exam.getName());
			oExam.setSlot(exam.getSlot());
			oExam.setPay(exam.getPay());
			oExam.setNote(exam.getNote());
		}
		return "redirect:/mvc/exam/";
	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable("id") String id) {
		exams.removeIf(e -> e.getId().equals(id));
		return "redirect:/mvc/exam/";
	}

	private Map<String, Long> getStat1() {
		return exams.stream().collect(Collectors.groupingBy(Exam::getName, Collectors.counting()));
	}

	private Map<String, Long> getStat2() {
		return exams.stream().collect(Collectors.groupingBy(Exam::getPay, Collectors.counting()));
	}

}

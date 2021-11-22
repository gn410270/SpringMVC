package com.mvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mvc.entity.Exam;

@Component
public class ExamValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		//判定當前 clazz 是不是 Exam 的類別
		return Exam.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Exam exam = (Exam)target;
		//判定 id 不可以是 null 或空的
		if(exam.getId()==null || exam.getId().trim().length()==0) {  // trim() 去除兩邊空白
			// field, errorCode, defaultMessage
			// field : 要驗證的物件變數
			// errorCode : 錯誤名稱(通常是指 errorMessage.properties 所設定的名稱)
			// defaultMessage : 預設的錯誤訊息
			errors.rejectValue("id",null,"id 不可空白");//"exam.id.required"
		}
		if(exam.getName() == null ||exam.getName().trim().length() == 0) {
			errors.rejectValue("name",null,"請選擇考試代號");//"exam.name.required"
		}
		if(exam.getSlot() == null ||exam.getSlot().length == 0) {
			errors.rejectValue("slot",null,"請選擇考試時段");//"exam.slot.required"
		}
		if(exam.getPay() == null ||exam.getPay().trim().length() == 0) {
			errors.rejectValue("pay",null,"請選擇繳費狀況");//"exam.pay.required"
		}
	}

}

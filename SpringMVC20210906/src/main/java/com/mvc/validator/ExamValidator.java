package com.mvc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.mvc.entity.Exam;

@Component
public class ExamValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		//�P�w��e clazz �O���O Exam �����O
		return Exam.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Exam exam = (Exam)target;
		//�P�w id ���i�H�O null �ΪŪ�
		if(exam.getId()==null || exam.getId().trim().length()==0) {  // trim() �h������ť�
			// field, errorCode, defaultMessage
			// field : �n���Ҫ������ܼ�
			// errorCode : ���~�W��(�q�`�O�� errorMessage.properties �ҳ]�w���W��)
			// defaultMessage : �w�]�����~�T��
			errors.rejectValue("id",null,"id ���i�ť�");//"exam.id.required"
		}
		if(exam.getName() == null ||exam.getName().trim().length() == 0) {
			errors.rejectValue("name",null,"�п�ܦҸեN��");//"exam.name.required"
		}
		if(exam.getSlot() == null ||exam.getSlot().length == 0) {
			errors.rejectValue("slot",null,"�п�ܦҸծɬq");//"exam.slot.required"
		}
		if(exam.getPay() == null ||exam.getPay().trim().length() == 0) {
			errors.rejectValue("pay",null,"�п��ú�O���p");//"exam.pay.required"
		}
	}

}

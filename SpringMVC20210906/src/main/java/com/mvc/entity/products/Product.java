package com.mvc.entity.products;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Product {
	@NotNull(message = "�п�ܰӫ~����")
	@NotBlank(message = "�п�ܰӫ~����")
	private Group group; //�ӫ~����
	
	@NotNull(message = "�п�ܤؤo�W��")
	@NotBlank(message = "�п�ܤؤo�W��")
	private String size; //�ؤo�W��
	
	@NotEmpty(message = "�п�J�ӫ~�ŧO") //�o�Ӥ~���ĪG
	@NotNull(message = "�п�ܯŧO")
	@NotBlank(message = "�п�ܯŧO")
	private Integer[] levelIds; //�ŧOid(�h��)
	
	@NotNull(message = "�ӫ~�W�٤��i�O�ŭ�")
	@Size(min = 3,max = 50,message = "�ӫ~�W�٥�������3~50�r����")
	private String name; //�ӫ~�W��
	
	@NotNull(message = "�ӫ~���椣�i�O�ŭ�")
	@Range(min = 1,max = 10000,message = "�ӫ~���楲������1~10000������")
	private Double price; //�ӫ~����
	
	@NotNull(message = "�ӫ~�ƶq���i�O�ŭ�")
	@Min(value = 1,message = "�ӫ~�ƶq�����j��ε���1")
	private Integer amount; //�ӫ~�ƶq
	
	@NotNull(message = "������i���ŭ�")
	@PastOrPresent(message = "�W�[������i�j�󤵤�")
	//@Past(message = "�W�[������i�j�󤵤�")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
	private Date date; //�W�[���

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Integer[] getLevelIds() {
		return levelIds;
	}

	public void setLevelIds(Integer[] levelIds) {
		this.levelIds = levelIds;
	}

	@Override
	public String toString() {
		return "Product [group=" + group + ", size=" + size + ", levelIds=" + Arrays.toString(levelIds) + ", name="
				+ name + ", price=" + price + ", amount=" + amount + ", date=" + date + "]";
	}

}

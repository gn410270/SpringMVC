package com.mvc.entity;

import java.util.Arrays;

public class Exam {
	private String id;
	private String name;
	private String[] slot;
	private String pay;
	private String note;
	
	//±N slot °}¦CÂà¦r¦ê
	public String getSlotToString() {
		return Arrays.toString(slot);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getSlot() {
		return slot;
	}
	public void setSlot(String[] slot) {
		this.slot = slot;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	
	
}

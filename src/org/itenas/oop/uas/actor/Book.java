package org.itenas.oop.uas.actor;

import java.util.Date;

public class Book {
	
	private String isbn;
	private String code;
	private String idMem;
	private Date startDate;
	private Date dueDate;
	private boolean status;
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setIdMem(String idMem) {
		this.idMem = idMem;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getCode() {
		return code;
	}
	public String getIdMem() {
		return idMem;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public boolean isStatus() {
		return status;
	}
	
	public double getTotalFine(int day) {
		double result;
		
		result = 5000 * Math.abs(day);
		
		return result;
	}
	
	public double getRansom(int day) {
		double result = 0;
		if(day < 0)
			return result = getTotalFine(day);
		else
			return 0;
	}
	
	
	

}
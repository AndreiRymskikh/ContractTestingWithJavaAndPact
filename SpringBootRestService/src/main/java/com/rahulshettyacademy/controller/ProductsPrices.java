package com.rahulshettyacademy.controller;

import org.springframework.stereotype.Component;

@Component
public class ProductsPrices {

	long booksPrice;
	long  coursesPrice;
	
	public void setBooksPrice(long booksPrice) {
		this.booksPrice = booksPrice;
	}
	public void setCoursesPrice(long coursesPrice) {
		this.coursesPrice = coursesPrice;
	}

	public long getBooksPrice() {
		return booksPrice;
	}
	public long getCoursesPrice() {
		return coursesPrice;
	}
}

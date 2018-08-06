package com.hd.pojo;

import java.util.Date;

public class Comment {
	
	private int id;
	
	private int c_id;
	
	private int star;
	
	private String text;
	
	private Date date;
	
	public Comment(){super();}

	public Comment(int id, int c_id, int star, String text, Date date) {
		super();
		this.id = id;
		this.c_id = c_id;
		this.star = star;
		this.text = text;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) throws Exception {
		if(star < 1 || star > 5) throw new Exception();
		this.star = star;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}

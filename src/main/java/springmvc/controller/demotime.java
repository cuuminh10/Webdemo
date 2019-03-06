package springmvc.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class demotime {
	private Timestamp time;

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public demotime(Timestamp time) {
		super();
		this.time = time;
	}

	public demotime() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

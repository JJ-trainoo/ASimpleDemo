package com.trainoo.quartz;

import java.util.Date;

public class QuartzJob {

	public void work(){
		
		System.out.println(new Date().getTime());
	}
}

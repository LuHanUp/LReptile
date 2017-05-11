package com.luhan.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**@Description {打印的工具类}
 * @author luHan
 * @date   2017年5月5日 下午12:30:04
 */
public class PrintUtils {
	private static String className = "";
	private static PrintUtils print = null;
	
	private PrintUtils() {}
	
	
	public static PrintUtils getPrintUtils(Class<?> className) {
		if(print == null){
			print = new PrintUtils();
		}
		PrintUtils.className = className.getName();
		
		return print;
		
	}
	
	public void error(String msg){
		System.err.println("["+getDate()+"]"+"-"+className+":"+msg);
	}
	public void normal(String msg){
		System.out.println("["+getDate()+"]"+"-"+className+":"+msg);
	}
	/**
	 * @desic  {获取计算机当前的日期}
	 * @author luHan
	 * @return
	 */
	private String getDate(){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}

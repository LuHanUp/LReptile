package com.luhan.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**@Description {properties文件的工具类}
 * @author luHan
 * @date   2017年5月5日 下午12:16:28
 */
public class PropertiesUtils {
	private PrintUtils print = PrintUtils.getPrintUtils(PropertiesUtils.class);
	
	
	Properties p = null;
	FileInputStream in = null;
	FileOutputStream out = null;
	
	public PropertiesUtils(String proPath) {
		try {
			if(p == null){
				p = new Properties();
				in = new FileInputStream(proPath);
			}
			p.load(in);
		} catch (IOException e) {
			print.normal(e.getMessage());
		}
	}

	/**
	 * @desic  {获取属性值}
	 * @author luHan
	 * @param key 获取的哪一个属性
	 * @return 返回获取到的值
	 */
	public String getValueByKey(String key){
		String result = "";
		try{
			result = p.getProperty(key);
			close("getValueByKey");
		}catch(Exception e){
			print.normal(e.getMessage());
		}
		
		return result;
	}
	/**
	 * @desic  {获取pro文件的全部属性值}
	 * @author luHan
	 * @return
	 */
	public Map<String,String> getValue(){
		Map<String,String> result = new LinkedHashMap<String,String>();
		try{
			Iterator<String> it =  p.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key = it.next();
				String value = p.getProperty(key,"");
				
				result.put(key, value);
			}
			
		}catch(Exception e){
			print.normal(e.getMessage());
		}
		return result;
	}
	
	/**
	 * @desic  {给properties文件赋值}
	 * @author luHan
	 * @param key 需要赋值的键
	 * @param value 值
	 */
	public void setValueByKey(String key,String value){
		try{
			p.setProperty(key, value);
		}catch(Exception e){
			print.normal(e.getMessage());
		}
	}
	/**
	 * @desic  {根据map集合来给pro文件赋值}
	 * @author luHan
	 * @param map 需要的map集合
	 */
	public void setValueByMap(Map<String, String> map){
		try{
			//获取map的键集合
			Set<String> set = map.keySet();
			//然后给pro文件一一赋值
			for(String key : set){
				p.setProperty(key, map.get(key));
			}
		}catch(Exception e){
			print.normal(e.getMessage());
		}
	}
	/**
	 * @desic  {保存到当前pro文件中}
	 * @author luHan
	 */
	public void save(String proPath){
		try {
			out = new FileOutputStream(proPath);
			p.store(out, "operation time:"+System.currentTimeMillis());
			close("save");
		} catch (IOException e) {
			print.normal(e.getMessage());
		}
	}
	
	/**
	 * @desic  {关闭流}
	 * @author luHan
	 * @param methodName
	 */
	private void close(String methodName){
		switch (methodName) {
		case "getValueByKey":
			if(in != null){
				try{
					in.close();
				}catch(Exception e){
					print.normal(e.getMessage());
				}
			}
			break;
		case "save":
			if(out != null){
				try{
					out.close();
				}catch(Exception e){
					print.normal(e.getMessage());
				}
			}
			break;
		}
	}
}

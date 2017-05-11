package com.luhan.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**@Description {连接数据库工具类}
 * @author luHan
 * @date   2017年5月5日 下午1:58:43
 */
public class JDBCUtils {
	private PrintUtils print = PrintUtils.getPrintUtils(JDBCUtils.class);
	//定义返回的执行数据库操作的变量
	private PreparedStatement ps;
	private Connection conn;
	//定义数据库驱动字符串
	String driverName = "";
	//定义数据库用户名和密码
	String userName = "",password = "";
	//定义数据库地址
	String url = "";
	
	public JDBCUtils(String driverName,String url,String username,String password) {
		this.driverName = driverName;
		this.userName = username;
		this.password = password;
		this.url = url;
		
		conn = getConn();
	}
	
	private Connection getConn(){
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, userName, password);
			print.normal("数据库连接成功");
		} catch (Exception e) {
			conn = null;
			print.normal(e.getMessage());
		}
		
		return conn;
	}
	
	public boolean insert(String sql){
		int result = 0;
		try {
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			result = 0;
			print.normal(e.getMessage());
		}
		
		return result >0 ? true:false;
	}
	
	public void select(String sql){
//		StringBuffer sb = new StringBuffer();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			ResultSetMetaData m = set.getMetaData();
			int columns = m.getColumnCount()+1;
			//获取列名称
			for(int i = 1;i<columns;i++){
//				print.normal(m.getColumnName(i));
			}
			while(set.next()){
				set.getString(0);
			}
		} catch (SQLException e) {
			print.normal(e.getMessage());
		}
	}
	
	public void close(){
		try {
			if(ps != null){
				ps.cancel();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			print.normal(e.getMessage());
		}
	}
}

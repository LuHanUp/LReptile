package com.luhan.JFram;

import javax.swing.*;

import com.luhan.control.LJButton;
import com.luhan.control.LJButton.ILOnclick;
import com.luhan.control.LJLable;
import com.luhan.control.LJTextFiled;
import com.luhan.params.PlaceParams;
import com.luhan.util.JDBCUtils;
import com.luhan.util.PrintUtils;
import com.luhan.util.PropertiesUtils;

/**@Description {主界面}
 * @author luHan
 * @date   2017年5月4日 下午7:28:56
 */
public class MainFrame extends JFrame implements ILOnclick{
	private PrintUtils print = PrintUtils.getPrintUtils(MainFrame.class);
	private static final long serialVersionUID = 1L;
	
	private final String TITLE = "我的爬虫程序——luHan";
	private final String IP_ADDRESS = "请输入MySQL的IP地址";
	private final String DB_NAME = "请输入MySQL中的数据库名称";
	private final String BTN_NAME_CONFIRM = "连接";
	private final String BTN_NAME_CANCEL = "取消";
	private final String BTN_NAME_EMPTY = "清空";
	//定义窗口大小
	private final int WIDTH = 500;
	private final int HEIGHT = 300;
	
	//定义存放的容器
	private JPanel jp_panel;
	//定义填写ip地址、数据库名称的控件
	private JTextField jt_ipValue,jt_dbNameValue;
	//定义确定、清空、取消按钮
	private LJButton btn_confirm,btn_empty,btn_cancel;
	
	String proPaht = "pro/dbparams.properties";
	
	private PropertiesUtils pUtils = new PropertiesUtils(proPaht);
	
	JDBCUtils db;
	

	public MainFrame() {
		//设置标题
		this.setTitle(TITLE);
		//设置窗口大小
		this.setSize(WIDTH, HEIGHT);
		//设置窗口居中显示
		this.setLocationRelativeTo(null);
		//设置退出程序时关闭进程
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//添加到窗体中
		this.add(setPanel());
		//设置是否显示
		this.setVisible(true);
	}
	/**
	 * @desic  {设置容器配置参数}
	 * @author luHan
	 * @return 返回设置好的容器
	 */
	private JPanel setPanel(){
		if(jp_panel == null){
			jp_panel = new JPanel();
			jp_panel.setSize(WIDTH, HEIGHT);
			jp_panel.setLayout(null);
		}

		jt_ipValue = new LJTextFiled(PlaceParams.gap_10 * 20,PlaceParams.gap_10);
		jt_ipValue.setText("localhost");
		jt_dbNameValue = new LJTextFiled(PlaceParams.gap_10 * 20,PlaceParams.gap_40);
		jt_dbNameValue.setText("luhanblog");
		
		btn_confirm = new LJButton(BTN_NAME_CONFIRM,PlaceParams.gap_10 * 10,PlaceParams.gap_100);
		btn_cancel = new LJButton(BTN_NAME_CANCEL,PlaceParams.gap_30 * 10,PlaceParams.gap_100);
		btn_empty = new LJButton(BTN_NAME_EMPTY,PlaceParams.gap_20 * 10,PlaceParams.gap_100);
		
		
		btn_confirm.setOnclick(this);
		btn_cancel.setOnclick(this);
		btn_empty.setOnclick(this);
		
		

		
		jp_panel.add(new LJLable(IP_ADDRESS,PlaceParams.gap_20,PlaceParams.gap_10 + 5));
		jp_panel.add(new LJLable(DB_NAME,PlaceParams.gap_20,PlaceParams.gap_40 + 5));
		jp_panel.add(jt_ipValue);
		jp_panel.add(jt_dbNameValue);
		jp_panel.add(btn_confirm);
		jp_panel.add(btn_cancel);
		jp_panel.add(btn_empty);
		return jp_panel;
	}
	
	@Override
	public void onClick(String btnName) {
		switch (btnName) {
		case BTN_NAME_CONFIRM:
			String ip = jt_ipValue.getText();
			String dbName = jt_dbNameValue.getText();
			String url = "jdbc:mysql://"+ip+":3306/"+dbName+"?useUnicode=true&characterEncoding=utf8";
			pUtils.setValueByKey("url", url);
			//获取driverName
			String driverName = pUtils.getValueByKey("driver");
			String username = pUtils.getValueByKey("username");
			String password = pUtils.getValueByKey("password");
			//连接数据库
			db = new JDBCUtils(driverName, url, username, password);
			new GrapFrame(db);
			this.setVisible(false);
			break;
		case BTN_NAME_EMPTY:
			jt_ipValue.setText("");
			jt_dbNameValue.setText("");
			break;
		case BTN_NAME_CANCEL:
			print.normal(pUtils.getValueByKey("url"));
			db.close();
			break;
		}
	}
}

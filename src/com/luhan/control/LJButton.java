package com.luhan.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**@Description {自定义JButton}
 * @author luHan
 * @date   2017年5月5日 上午10:36:56
 */
public class LJButton extends JButton{
	private static final long serialVersionUID = 1L;
	
	private String defaultBtnName = "按钮";
	
	private ILOnclick listener;
	
	public LJButton() {
		this.setText(defaultBtnName);
		this.setBounds(0, 0, getWidthByBtnName(defaultBtnName), 30);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listener != null){
					listener.onClick(defaultBtnName);
				}
			}
		});
	}
	public LJButton(String btnName) {
		this.defaultBtnName = btnName;
		this.setText(defaultBtnName);
		this.setBounds(0, 0, getWidthByBtnName(defaultBtnName), 30);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listener != null){
					listener.onClick(defaultBtnName);
				}
			}
		});
	}
	public LJButton(String btnName,int x,int y) {
		this.defaultBtnName = btnName;
		this.setText(defaultBtnName);
		this.setBounds(x, y, getWidthByBtnName(defaultBtnName), 30);
		this.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(listener != null){
					listener.onClick(defaultBtnName);
				}
			}
		});
	}
	
	/**
	 * @desic  {根据按钮名称设置宽度}
	 * @author luHan
	 * @param btnName
	 * @return 返回宽度
	 */
	private int getWidthByBtnName(String btnName){
		int length = btnName.length();
		
		return length * 30;
	}
	
	/**
	 * @desic  {获取按钮名称}
	 * @author luHan
	 * @return 返回按钮的名称
	 */
	public String getBtnName(){
		return defaultBtnName;
	}
	
	/**
	 * @desic  {设置回调函数}
	 * @author luHan
	 * @param listener
	 */
	public void setOnclick(ILOnclick listener){
		this.listener = listener;
	}
	
	/**
	 * @Description {回调接口类}
	 * @author luHan
	 * @date   2017年5月5日 上午11:06:45
	 */
	public interface ILOnclick{
		/**
		 * @desic  {单击事件}
		 * @author luHan
		 * @param btnName 按钮的名称
		 */
		void onClick(String btnName);
	}
}

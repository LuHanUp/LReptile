package com.luhan.control;

import javax.swing.JLabel;

import com.luhan.params.PlaceParams;

/**@Description {自定义JLabel类}
 * @author luHan
 * @date   2017年5月4日 下午8:04:18
 */
public class LJLable extends JLabel{
	private static final long serialVersionUID = 1L;
	//控件的高度
	private final int HEIGHT = 13;

	public LJLable(String title) {
		this.setText(title);
		this.setBounds(PlaceParams.default_X, PlaceParams.default_Y,countWidthAndHeightByTitle(title), HEIGHT);
	}
	
	public LJLable(String title,int x,int y) {
		this.setText(title);
		this.setBounds(x, y,countWidthAndHeightByTitle(title), HEIGHT);
	}
	/**
	 * @desic  {根据文字来获取应该有的宽度}
	 * @author luHan
	 * @param title 文字
	 * @return 返回宽度值
	 */
	private int countWidthAndHeightByTitle(String title){
		//获取title文字的长度
		int length = title.length();
		//因为JLable中一个文字所占的宽高为13，所以根据文字的多少来相乘即可
		int width = length * 13;
		//返回这个宽度
		return width;
	}
}

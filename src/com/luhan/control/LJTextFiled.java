package com.luhan.control;


import javax.swing.JTextField;

/**@Description {自定义JTextFiled类}
 * @author luHan
 * @date   2017年5月5日 上午10:10:46
 */
public class LJTextFiled extends JTextField{
	private static final long serialVersionUID = 1L;
	/** 默认的Columns */
	private int defaultColumns = 150;
	/** 默认的组件的宽 */
	private int defaultWidth = 150;
	/** 默认的组件的高 */
	private int defaultHeight = 22;

	public LJTextFiled() {
		this.setColumns(defaultColumns);
		this.setBounds(0, 0, defaultWidth, defaultHeight);
	}
	public LJTextFiled(int columns) {
		this.defaultColumns = columns;
		this.setColumns(defaultColumns);
		this.setBounds(0,0,defaultColumns,defaultHeight);
	}
	public LJTextFiled(int x,int y) {
		this.setColumns(defaultColumns);
		this.setBounds(x,y,defaultColumns,defaultHeight);
	}
}

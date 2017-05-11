package com.luhan.JFram;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.luhan.control.LJButton;
import com.luhan.control.LJButton.ILOnclick;
import com.luhan.control.LJLable;
import com.luhan.control.LJTextFiled;
import com.luhan.params.PlaceParams;
import com.luhan.util.JDBCUtils;
import com.luhan.util.PrintUtils;

/**@Description {用于抓取数据的界面}
 * @author luHan
 * @date   2017年5月5日 下午4:18:34
 */
@SuppressWarnings("unused")
public class GrapFrame extends JFrame implements ILOnclick{
	private PrintUtils print = PrintUtils.getPrintUtils(GrapFrame.class);
	private static final long serialVersionUID = 1L;
	private final String TITLE = "开始爬虫——luHan";
	private JPanel jPanel = null;
	//定义窗口大小
	private final int WIDTH = 500;
	private final int HEIGHT = 300;
	
	
	private LJTextFiled jt_url;
	private LJButton jb_confirm;
	
	JDBCUtils db;
	
	public GrapFrame(JDBCUtils db) {
		this.db = db;
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

	/**@desic  {方法的说明}
	 * @author luHan
	 * @return
	 */
	private JPanel setPanel() {
		if(jPanel == null){
			jPanel = new JPanel();
			jPanel.setSize(WIDTH, HEIGHT);
			jPanel.setLayout(null);
		}
		
		jt_url = new LJTextFiled(PlaceParams.gap_80 * 2, PlaceParams.gap_20);
		jt_url.setText("http://news.baidu.com/ent");
		
		jb_confirm = new LJButton("抓取", PlaceParams.gap_60 * 3, PlaceParams.gap_60);
		jb_confirm.setOnclick(this);
		
		jPanel.add(new LJLable("输入需要抓取的网址", PlaceParams.gap_20, PlaceParams.gap_20 + 5));
		jPanel.add(jt_url);
		jPanel.add(jb_confirm);
		
		return jPanel;
	}
	@Override
	public void onClick(String btnName) {
		switch (btnName) {
		case "抓取":
			String url = jt_url.getText();
			boolean fl = false;
			try {
				Document document = Jsoup.connect(url).get();
				Element div = document.getElementsByClass("b-left").get(0);
				Elements uls = div.getElementsByTag("ul");
				for(Element ul : uls){
					Elements lis = ul.getElementsByTag("li");
					for(Element li : lis){
						Element a = li.getElementsByTag("a").get(0);
						//获取新闻地址
						String newsPath = a.attr("href");
						//获取新闻简介
						String newsdesic = a.text();
						//插入到数据库中
						String sql = "insert into newdesc(descs,newspath,time) values('"+newsdesic+"','"+newsPath+"','"+System.currentTimeMillis()+"')";
						fl = db.insert(sql);
					}
				}
			} catch (IOException e) {
				
			}
			if(fl){
				JOptionPane.showMessageDialog(null, "抓取成功", "提示", JOptionPane.PLAIN_MESSAGE);
				db.close();
			}else{
				JOptionPane.showMessageDialog(null, "抓取失败", "提示", JOptionPane.PLAIN_MESSAGE);
				db.close();
			}
			break;
		}
	}
}

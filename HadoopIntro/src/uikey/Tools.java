package uikey;

import java.awt.Button;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.parser.ParseException;

import facebook4j.FacebookException;

class Tools {
	public static JPanel p1, p2;
	public void func(String title, int p, int vp, int n, int vn, int ne, double total){
		JFrame f= new JFrame("SENTIMENT ANALYTIC TOOL");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setSize(400, 400);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		p1 = new JPanel();
		p1.setSize(100, 100);
		p1.setBackground(Color.red);

		PieChart pie = new PieChart("Sentiment Analytic Tool", p, vp, n, vn, ne, total);
		p2 = new JPanel();
		p2 = pie.createPanel();//.setSize(100, 100);
		p2.setSize(100, 100);
		mainPanel.add(p1);
		mainPanel.add(p2);
  		
  		
  		f.add(mainPanel);
		f.setSize(400, 400);
		f.setVisible(true);
	}
}

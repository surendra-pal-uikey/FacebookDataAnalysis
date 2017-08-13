package uikey;

import java.awt.Button;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class PieChart// extends ApplicationFrame {
{
   public static int positiveP;
   public static int vpositiveP;
   public static int negativeP;
   public static int vnegativeP;
   public static int neutralP;
   public static int total;
   /*public static void main(String[] args){
	   PieChart pc = new PieChart("pie", 2, 2, 2, 2, 2, 10.0);
	   JFrame fr = new JFrame();
	   JPanel p = new JPanel();
	   p = pc.createPanel();
	   p.setBounds(50,50, 200,200);
	   Button b1 = new Button("search");
	   JPanel p1 = new JPanel();
	   b1.setBounds(50,260, 50,50);
	   p1.setBounds(260,50, 200,200);
	   p1.add(b1);
	   fr.add(p);
	   fr.add(p1);
	   
	   fr.setSize(1366, 768);
	   fr.setVisible(true);
   }*/
   public PieChart( String title, int positive, int vpositive, int negative, int vnegative, int neutral, double total ) {
      //super( title );
	   positiveP = (int)( (positive*100)/total + 0.5 );
	   vpositiveP = (int)( (vpositive*100)/total + 0.5 );
	   negativeP = (int)( (negative*100)/total + 0.5 );
	   vnegativeP = (int)( (vnegative*100)/total + 0.5 );
	   neutralP = (int)( (neutral*100)/total + 0.5);
      //setContentPane(createPanel( ));
   }
   
   private static PieDataset createDataset( ) {
      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "POSITIVE" , new Double( positiveP ) );
      dataset.setValue( "VERY POSITIVE" , new Double( vpositiveP ) );
      dataset.setValue( "NEGATIVE" , new Double( negativeP  ) );   
      dataset.setValue( "VERY NEGATIVE" , new Double( vnegativeP  ) );   
      dataset.setValue( "NEUTRAL" , new Double( neutralP ) );    
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Sentiment Analysis", 
         dataset,              
         true,             
         true, 
         false);
      return chart;
   }
   
   public static JPanel createPanel( ) {
      JFreeChart chart = createChart(createDataset( ) );  
      return new ChartPanel( chart ); 
   }
   /*public void run(PieChart demo){
	   demo.setSize( 560 , 367 );	   
	   RefineryUtilities.centerFrameOnScreen( demo );    
	   demo.setVisible( true );
   }*/
}
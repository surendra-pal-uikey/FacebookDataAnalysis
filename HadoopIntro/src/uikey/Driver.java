package uikey;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class Driver {     
	public static double total = 0;
	/*public static void main(String[] args) throws Exception{
		Driver d = new Driver ();
		d.startHadoop();
	}*/
	public void startHadoop( ) throws Exception{
		gui g = new gui();
		Read r = new Read();
		total = r.read();
		System.out.println("Yes");
        String in = "input.txt";
        String out = "out";
        JobConf conf = new JobConf(Driver.class);
        conf.setJobName("Driver");
        
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        
        conf.setMapperClass(MapperClass.class);
        conf.setCombinerClass(ReducerClass.class);
        conf.setReducerClass(ReducerClass.class);
        
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);
        
        FileInputFormat.setInputPaths(conf, new Path(in));
        FileOutputFormat.setOutputPath(conf, new Path(out));
        
        JobClient.runJob(conf);
        System.out.println("completed");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("out/part-00000")));
        String line;
        
        int neg = 0;
        int vneg = 0;
        int neu = 0;
        int pos = 0;
        int vpos = 0;
        
		while( (line = br.readLine()) != null){
			line = line.replaceAll("\\s+",  " ");
			String[] pr = line.split(" ");
			int i = 0;
			String name = null;
			int freq = 0;
			for(String s : pr)
			{
				if(i == 0){
					name = s;
				}
				else{
					freq = Integer.parseInt(s);
				}
				i++;
			}
			if( name.equals("VeryPositive")){
				vpos = freq;
			}else if( name.equals("Positive")){
				pos = freq;
			}else if( name.equals("Negative")){
				neg = freq;
			}else if( name.equals("VeryNegative")){
				vneg = freq;
			}
			else{
				neu = freq;
			}
			System.out.println(name + " " + freq);
        }
		br.close();
		System.out.println("Positive : " + pos);
		System.out.println("VeryPositive : " + vpos);
		System.out.println("Negative : " + neg);
		System.out.println("VeryNegative : " + vneg);
		System.out.println("Neutral : " + neu);
		System.out.println();
		total = pos + vpos + neg + vneg + neu;
		/*PieChart p = new PieChart("Sentiment Analysis", pos, vpos, neg, vneg, neu, total);
        p.run(p);*/
		Tools t = new Tools();
		t.func("Sentiment Analysis", pos, vpos, neg, vneg, neu, total);
    }
}


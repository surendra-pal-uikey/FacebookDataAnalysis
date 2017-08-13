package uikey;

import java.io.IOException;


import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

 
public class MapperClass extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
    	private final static IntWritable one = new IntWritable(1);    		
    	public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
    	      String line = value.toString();
    	      System.out.println(line);
    	      if( line.length() > 1 ){
    	    	  SentimentAnalyser sa = new SentimentAnalyser();
    	    	  String result = sa.findSentiment(line);
    	    	  output.collect(new Text(result), one);
    	      }
    	}
}


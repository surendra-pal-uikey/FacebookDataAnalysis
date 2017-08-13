package uikey;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Read {
	public double read() throws IOException, ParseException{
		FileWriter fr1 = new FileWriter("input.txt"); 
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("comment.json"), "UTF-8"));
	    BufferedWriter buffer = new BufferedWriter(fr1);
	    String line;
	    double total = 0;
	    while( (line = br.readLine()) != null ){
	    	JSONParser parser1 = new JSONParser();
	        Object obj = parser1.parse(line);
	        JSONObject jsonObj = (JSONObject) obj;
	        String oldString = (String)jsonObj.get("message");
	       	oldString.replaceAll("[\\n]*", " ");
	       	RemovalOfSpl rsp = new RemovalOfSpl();
	       	oldString = rsp.prepro(oldString);
	        buffer.write(oldString);
	        buffer.newLine();
	        total++;
	    }
	    br.close();
	    buffer.close();
	    fr1.close();
	    return total;
	}
}

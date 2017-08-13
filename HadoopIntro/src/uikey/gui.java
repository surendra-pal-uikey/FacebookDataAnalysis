package uikey;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Like;
import facebook4j.PagableList;
import facebook4j.Reading;

class gui {
	public static void main(String[] args)
    {  
	    JFrame f= new JFrame("SENTIMENT ANALYTIC TOOL");  
	    JLabel label1, label2;  
	    label1 = new JLabel("SENTIMENT ANALYTIC TOOL");
	    label2 = new JLabel();
	    
	    label1.setBounds(400,50, 600,50);
	    label1.setHorizontalAlignment(JLabel.CENTER);
	    label1.setFont(new Font(label1.getName(), Font.ROMAN_BASELINE, 28));
	    
	    JTextField t1 = new JTextField("Enter Url");
	    t1.setBounds(400,140, 600,30);
	    
	    label2.setIcon(new ImageIcon("images/images.png"));
	    label2.setBounds(550, 190, 300,300);
	    
	    JButton button = new JButton("Analyse");
	    button.setBounds(600, 550, 200,30);
	    button.addActionListener( new ActionListener( ) {
	        public void actionPerformed( ActionEvent e )
	        {
	        	System.out.println( t1.getText().toString() );
	        	PostExtract demo = new PostExtract();
	        	String id = t1.getText().toString();
	        	id = id.replaceAll("[^a-zA-Z0-9_]", " ");
	        	id = id.replaceAll("\\s+", " ");
	        	String [] ar = id.split(" ");
	        	String aid = null;
	        	for(int i = 0; i < ar.length; i++){
	        		//System.out.println(aid);
	        		if( ar[i].equals("ft_ent_identifier") ){
	        			//System.out.println(aid);
	        			aid = ar[i+1];
	        		}
	        	}
	        	System.out.println(aid);
	        	try {
	        		//demo.Ext(id);
	        		System.getProperties().put("https.proxySet", "true");
	            	System.getProperties().put("https.proxyHost", "172.31.1.3");
	                System.getProperties().put("https.proxyPort", "8080");
	                System.getProperties().put("https.proxyUser", "iwm2014006");
	                System.getProperties().put("https.proxyPassword", "Pal7771854857");
	        		        
	                Facebook facebook = new FacebookFactory().getInstance();
	                String feedId="";

	                feedId= aid;
	                
	                try {
	                    PrintWriter commentWriter = new PrintWriter("comment.json", "UTF-8");
	                    PrintWriter likesWriter = new PrintWriter("likes.json", "UTF-8");
	                    		
	                    //Connection<Comment> commentConnection  = facebook.fetchConnection(feedId + "/comments", Comment.class);
	        	                PagableList<Comment> comments = facebook.getCommentReplies(feedId, new Reading().limit(200)); //for getting comments
	        	                PagableList<Like> likes = facebook.getAlbumLikes(feedId);  // for getting likes
	        	                
	        	                for (Comment comment : comments) {
	        	                    // Get comments.
	        	                    String msg = comment.getMessage();
	        	                    String commentId = comment.getId();
	        	                    String created_time = comment.getCreatedTime().toString();
	        	                    JSONObject obj = new JSONObject();
	        	                    obj.put("id", commentId);
	        	                    obj.put("message", msg);
	        	                    commentWriter.println(obj.toString());
	        	                }
	        	                for (Like like : likes) {
	        	                    // Get likes.
	        	                    // Get (string) message.
	        	                    String likeId = like.getId();
	        	                    String category = like.getCategory();
	        	                    String name = like.getName();
	        	                    String l = like.toString();
	        	                    
	        	                    JSONObject obj = new JSONObject();
	        	                    obj.put("category", category);
	        	                    obj.put("id", likeId);
	        	                    likesWriter.println(obj.toString());
	        	                }
	        		            commentWriter.flush();
	        		            likesWriter.flush();
	        		            commentWriter.close();
	        		            likesWriter.close();
	        		            System.out.println("Loading completed");
	                } catch (FileNotFoundException e1) {
	                    e1.printStackTrace();
	                } catch (UnsupportedEncodingException e1) {
	                    e1.printStackTrace();
	                }
					Driver d = new Driver();
					d.startHadoop();
					f.dispose();
					
				} catch (FacebookException | IOException | ParseException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        }
	    });
	    
	    f.add(label1); f.add(t1); f.add(label2);f.add(button); 
	    f.setSize(1366,768);  
	    f.setLayout(null);  
	    f.setVisible(true);
    }
}

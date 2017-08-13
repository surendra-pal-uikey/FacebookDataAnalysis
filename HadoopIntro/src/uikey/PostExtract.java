package uikey;

import facebook4j.*;
/**
*
* @author SURENDRA
*/

import facebook4j.json.DataObjectFactory;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.sun.net.httpserver.Authenticator;
public class PostExtract {
	/*public static void main(String[] args) throws FileNotFoundException, FacebookException, IOException, ParseException, InterruptedException{
		PostExtract p = new PostExtract();
		p.Ext("1658897734125952");
	}*/
    public void Extract(String postId) throws FacebookException, FileNotFoundException, IOException, ParseException, InterruptedException {  	
    	System.getProperties().put("https.proxySet", "true");
    	System.getProperties().put("http.proxyHost", "172.31.1.4");
        System.getProperties().put("http.proxyPort", "8080");
        System.getProperties().put("http.proxyUser", "iwm2014006");
        System.getProperties().put("http.proxyPassword", "Pal7771854857");
		        
        Facebook facebook = new FacebookFactory().getInstance();

        // Set limit to 25 feeds.
        String feedId="";

        feedId= postId;
        
        try {
            PrintWriter postWriter = new PrintWriter("post.json", "UTF-8");
            PrintWriter commentWriter = new PrintWriter("comment.json", "UTF-8");
            PrintWriter likesWriter = new PrintWriter("likes.json", "UTF-8");
            long start = System.currentTimeMillis();
            long end = start + 10*1000; 
            ResponseList<Post> feeds = facebook.getFeed(feedId);
            while(System.currentTimeMillis() < end){
	            for (Post post : feeds) {
	            	// Get post.
	                String message = post.getMessage();
	                PagableList<Comment> comments = post.getComments(); //for getting comments
	                PagableList<Like> likes = post.getLikes(); // for getting likes
	                String id = post.getId();
	                
	                String json = DataObjectFactory.getRawJSON(post);
	                postWriter.println(json);
	                
	                
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
	            }
	
	            postWriter.flush();
	            commentWriter.flush();
	            likesWriter.flush();
	            Paging<Post> page = feeds.getPaging();
	            feeds=facebook.fetchNext(page);
	            if(feeds.size()<1)break;
	        }
            postWriter.close();
            commentWriter.close();
            likesWriter.close();
            System.out.println("Loading completed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void Ext(String postId) throws FacebookException, FileNotFoundException, IOException, ParseException, InterruptedException {  	
    	System.getProperties().put("https.proxySet", "true");
    	System.getProperties().put("http.proxyHost", "172.31.1.3");
        System.getProperties().put("http.proxyPort", "8080");
        System.getProperties().put("http.proxyUser", "iwm2014006");
        System.getProperties().put("http.proxyPassword", "Pal7771854857");
		        
        Facebook facebook = new FacebookFactory().getInstance();
        String feedId="";

        feedId= postId;
        
        try {
            PrintWriter commentWriter = new PrintWriter("comment.json", "UTF-8");
            PrintWriter likesWriter = new PrintWriter("likes.json", "UTF-8");            
	                PagableList<Comment> comments = facebook.getCommentReplies(feedId); //for getting comments
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
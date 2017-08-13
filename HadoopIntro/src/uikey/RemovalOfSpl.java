package uikey;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author SURENDRA
*/
public class RemovalOfSpl {
	public String prepro(String oldString){
		oldString = this.replaceAbb(oldString);
		oldString = this.Emocation(oldString);
		oldString  = oldString.toLowerCase();
		oldString = this.removeEmphWord(oldString);
		oldString = this.removeURL(oldString);
		oldString = this.removalSplChar(oldString);
		String[] old = oldString.split(" ");
		String oldString1 = " ";
		for( String s : old){
			oldString1 = oldString1 + s + " "; 
		}
		return this.whiteSpace(oldString1);
	}
	public String whiteSpace(String oldString){
		oldString = oldString.replaceAll("\\s+", " ");
		return oldString;
	}
	public String removalSplChar(String oldString){
		oldString = oldString.replaceAll("[^a-zA-Z',:()$0-9]", " ");
		return oldString;
	}
	public String removeURL(String oldString){
		String regex = "(https?|ftp|file|http)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(oldString);
        if( m.find()){
        	oldString = oldString.replaceAll(regex, " ");
            //System.out.println(oldString);
        }
		return oldString;
	}
	public String Emocation(String oldString){
		oldString = oldString.replaceAll(":\\)", "happy");
		oldString = oldString.replaceAll(":\\(", "sad");
		oldString = oldString.replaceAll(":'‑\\(", "crying");
		oldString = oldString.replaceAll(":'\\(", "crying");
		oldString = oldString.replaceAll(":\\$", "Embarrassed");
		oldString = oldString.replaceAll("\\|\\;‑\\)", "cool");
		oldString = oldString.replaceAll("\\|‑O", "yawning");
		return oldString;
	}
	
	public String replaceAbb(String oldString){
		oldString = oldString.replaceAll("gr8", "great");
		oldString = oldString.replaceAll("Fb",  "Facebook");
		oldString = oldString.replaceAll("SOS", "Someone on Shoulder");
		oldString = oldString.replaceAll("POS", "Parents Over shoulder");
		oldString = oldString.replaceAll("ASAP", "As soon as possible");
		oldString = oldString.replaceAll("1NAM", "One in a million");
		oldString = oldString.replaceAll("AFAIK", "As far as I know");
		oldString = oldString.replaceAll("B4N", "Bye for now");
		oldString = oldString.replaceAll("2N8", "Tonight");
		oldString = oldString.replaceAll("404",  "No clue");
		oldString = oldString.replaceAll("4Eva", "Forever");
		oldString = oldString.replaceAll("BRB", "Be right back");
		oldString = oldString.replaceAll("LOL", "Laugh out loud");
		oldString = oldString.replaceAll("LMAO", "Laugh my Ass off");
		oldString = oldString.replaceAll("WTH", "What the hell");
		oldString = oldString.replaceAll("LMK", "Let me know");
		oldString = oldString.replaceAll("TTYL",  "Talk to you later");
		oldString = oldString.replaceAll("N", "And");
		oldString = oldString.replaceAll("BFF", "Best Friends forever");
		oldString = oldString.replaceAll("kk", "ok");
		oldString = oldString.replaceAll("Kl", "Cool");
		oldString = oldString.replaceAll("BMT", "Before my time");
		oldString = oldString.replaceAll("OMG", "OH my god");
		oldString = oldString.replaceAll("AFAIK",  "As far as I know");
		oldString = oldString.replaceAll("AIDA", "Attention, Interest, Desire, Action");
		oldString = oldString.replaceAll("AMA" , "Ask Me Anything");
		oldString = oldString.replaceAll("API", "Application Programming Interface");
		oldString = oldString.replaceAll("B2B", "Business-to-business");
		oldString = oldString.replaceAll("B2C", "Business-to-consumer");
		oldString = oldString.replaceAll("ESP", "Email service provider");
		oldString = oldString.replaceAll("F2F", "Face to face");
		oldString = oldString.replaceAll("FB",  "Facebook");
		oldString = oldString.replaceAll("L8", "Late");
		return oldString;
	}
	public String removeEmphWord(String oldString){
		oldString = oldString.replaceAll("[a]{3,}", "");oldString = oldString.replaceAll("[m]{3,}", "");
		oldString = oldString.replaceAll("[b]{3,}", "");oldString = oldString.replaceAll("[n]{3,}", "");
		oldString = oldString.replaceAll("[c]{3,}", "");oldString = oldString.replaceAll("[o]{3,}", "");
		oldString = oldString.replaceAll("[d]{3,}", "");oldString = oldString.replaceAll("[p]{3,}", "");
		oldString = oldString.replaceAll("[e]{3,}", "");oldString = oldString.replaceAll("[q]{3,}", "");
		oldString = oldString.replaceAll("[f]{3,}", "");oldString = oldString.replaceAll("[r]{3,}", "");
		oldString = oldString.replaceAll("[g]{3,}", "");oldString = oldString.replaceAll("[s]{3,}", "");
		oldString = oldString.replaceAll("[h]{3,}", "");oldString = oldString.replaceAll("[t]{3,}", "");
		oldString = oldString.replaceAll("[i]{3,}", "");oldString = oldString.replaceAll("[u]{3,}", "");
		oldString = oldString.replaceAll("[j]{3,}", "");oldString = oldString.replaceAll("[v]{3,}", "");
		oldString = oldString.replaceAll("[k]{3,}", "");oldString = oldString.replaceAll("[w]{3,}", "");
		oldString = oldString.replaceAll("[l]{3,}", "");oldString = oldString.replaceAll("[x]{3,}", "");
		oldString = oldString.replaceAll("[y]{3,}", "");oldString = oldString.replaceAll("[z]{3,}", "");
		return oldString;
	}
}

package uikey;

import java.util.Properties;
import java.util.Scanner;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
//import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/**
*
* @author SURENDRA
*/
class SentimentAnalyser {
	/*public static void main(String[] args){
		SentimentAnalyser se = new SentimentAnalyser();
		int t=10;
		while(t>=0)
		{
			t--;
			Scanner s=new Scanner(System.in);
			String p=s.next();
			System.out.println(se.findSentiment(p));
			
		}
		
		//System.out.println(se.findSentiment("this product is very good"));
	}*/
    public String findSentiment(String line) {
       if(line == null || line.isEmpty()) {
          throw new IllegalArgumentException("The line must not be null or empty.");
       }
       Annotation annotation = processLine(line);
       int mainSentiment = findMainSentiment(annotation);
       
       if( mainSentiment == 0 )
    	   return "VeryNegative";
       else if ( mainSentiment == 1)
    	   return "Negative";
       else if( mainSentiment == 3 )
    	   return "Positive";
       else if( mainSentiment == 4)
    	   return "VeryPositive";
       if(mainSentiment < 0 || mainSentiment > 4)  
    	   return null; 
       else 
    	   return "Neutral";
    }
    private int findMainSentiment(Annotation annotation) {
       int mainSentiment = Integer.MIN_VALUE;       
       int longest = Integer.MIN_VALUE;
       for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
          int sentenceLength = String.valueOf(sentence).length();
          if(sentenceLength > longest) {
        	Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);  
            //Tree tree = sentence.get(SentimentAnnotatedTree.class);
            mainSentiment = RNNCoreAnnotations.getPredictedClass(tree);
            longest = sentenceLength ;
           }
       }
       return mainSentiment;
    }
    private Annotation processLine(String line) {
       StanfordCoreNLP pipeline = createPieline();
       return pipeline.process(line);
    }
    private StanfordCoreNLP createPieline() {
       Properties props = createPipelieProperties();
       StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
       return pipeline;
    }
    private Properties createPipelieProperties() {
       Properties props = new Properties();
       props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
       return props;
    }
}
package uikey;

import java.util.Properties;
import edu.stanford.nlp.coref.CorefCoreAnnotations;
import edu.stanford.nlp.coref.data.CorefChain;
import edu.stanford.nlp.coref.data.Mention;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

public class Pronountonoun {
  public static void main(String[] args) throws Exception {
    Annotation document = new Annotation("the car is nice,it is not so big");
    Properties props = new Properties();
    props.put("annotators", "tokenize,ssplit,pos,lemma,ner,parse,mention,coref");
    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
    pipeline.annotate(document);
    System.out.println("---");
    System.out.println("coref chains");
    for (CorefChain cc : document.get(CorefCoreAnnotations.CorefChainAnnotation.class).values()) {
    	System.out.println("\t" + cc.getRepresentativeMention());
    	System.out.println("\t" + cc.toString());
    }
    for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
      System.out.println("---");
      System.out.println("mentions");
      for (Mention m : sentence.get(CorefCoreAnnotations.CorefMentionsAnnotation.class)) {
        System.out.println("\t" + m);
        System.out.println("\t" + m.endIndex+"  "+m.spanToString()+m.originalRef);
       }
    }
  }
}
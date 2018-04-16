import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.logging.JavaUtilLoggingAdaptor;
import edu.stanford.nlp.util.logging.Redwood;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;
import edu.stanford.nlp.util.logging.RedwoodPrintStream;

import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by dakshins on 07/04/18.
 */
public class CoreNLPWrapper
{
    final String searchText;
    final CoreDocument document;
    private static StanfordCoreNLP pipeline;
    
    public static void initialize()
    {
        System.out.println("Initializing NLP pipeline...");
        RedwoodConfiguration.standard().apply();
        pipeline = new StanfordCoreNLP(properties());
    
        System.out.println("Completed initializing pipeline...");
    }
    
    public CoreNLPWrapper(final String searchText)
    {
        this.searchText = searchText.replace('+', ' ');
        document = new CoreDocument(searchText);
    }
    
    private static Properties properties()
    {
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse,depparse,coref,kbp,quote");
        props.setProperty("coref.algorithm", "neural");
        return props;
    }
    
    public CoreNLPHttpResponse response()
    {
        annotate(document);
        StringBuilder builder = new StringBuilder();
        builder.append("<ul>");
    
        CoreSentence sentence = document.sentences().get(0);
    
        String nerTags = String.join(",", sentence.nerTags());
        builder.append("<li>" + nerTags + "</li>");
    
        String posTags = String.join(",", sentence.posTags());
        builder.append("<li>" + posTags + "</li>");
    
        builder.append("</ul>");
        return new CoreNLPHttpResponse(builder.toString(), 200);
    }
    
    private synchronized void annotate(CoreDocument coreDocument)
    {
        pipeline.annotate(coreDocument);
    }
}

import java.util.Map;
import java.util.Properties;
import edu.stanford.nlp.util.ArrayMap;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.logging.RedwoodConfiguration;

/**
 * Created by dakshins on 07/04/18.
 */
public class CoreNLPWrapper
{
    final String searchText;
    final CoreDocument document;
    private static StanfordCoreNLP pipeline;
    
    public static void initialize() throws Exception
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

        prepare(builder);

        return CoreNLPHttpResponse.NEW
                .withBody(builder.toString())
                .withHttpCode(200)
                .withHeaders(headers());
    }

    private void prepare(StringBuilder builder) {

        CoreSentence sentence = document.sentences().get(0);

        String nerTags = String.join(",", sentence.nerTags());
        builder.append("<li>" + nerTags + "</li>");

        String posTags = String.join(",", sentence.posTags());
        builder.append("<li>" + posTags + "</li>");

        builder.append("</ul>");
    }

    private synchronized void annotate(CoreDocument coreDocument)
    {
        pipeline.annotate(coreDocument);
    }

    public Map<String, String> headers()
    {
        ArrayMap<String,String> map = new ArrayMap<>();
        map.put("ContentType", "application/xhtml+xml");
        return map;
    }
}

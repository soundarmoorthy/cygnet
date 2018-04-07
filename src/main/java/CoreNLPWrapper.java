import edu.stanford.nlp.coref.data.*;
import edu.stanford.nlp.coref.data.Document;
import edu.stanford.nlp.pipeline.CoreNLPProtos;

import java.util.Properties;


import edu.stanford.nlp.simple.*;

import java.util.*;

/**
 * Created by dakshins on 07/04/18.
 */
public class CoreNLPWrapper
{
    final String searchText;
    final Sentence sentence;
    public CoreNLPWrapper(final String searchText)
    {
        this.searchText = searchText;
        sentence = new Sentence(searchText);
    }
    
    
    public CoreNLPHttpResponse response()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<ul>");
        for(String tag : sentence.nerTags())
        {
            builder.append("<li>"+ tag + "</li>");
        }
        builder.append("</ul>");
        return new CoreNLPHttpResponse(builder.toString(), 200);
    }
}

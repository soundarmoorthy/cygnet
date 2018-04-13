import edu.stanford.nlp.simple.*;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CollectionFactory;

import java.util.Collection;
import java.util.Collections;


/**
 * Created by dakshins on 07/04/18.
 */
public class CoreNLPWrapper
{
    final String searchText;
    final Sentence sentence;
    public CoreNLPWrapper(final String searchText)
    {
        this.searchText = searchText.replace('+',' ');
        Collections.sort(, Collections.reverseOrder());
        sentence = new Sentence(searchText);
    }
    
    
    public CoreNLPHttpResponse response()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("<ul>");
        Tree tree =  sentence.parse();
        for(String tag : sentence.nerTags())
        {
            builder.append("<li>"+ tag + "</li>");
        }
        builder.append("</ul>");
        return new CoreNLPHttpResponse(builder.toString(), 200);
    }
}

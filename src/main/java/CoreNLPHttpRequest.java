/**
 * Created by dakshins on 07/04/18.
 */

import com.sun.net.httpserver.HttpExchange;

public class CoreNLPHttpRequest
{
    private final String searchTextHeaderText="searchText=";
    final private HttpExchange exchange;
    private String searchString, queryString;
    private boolean valid;
    
    public CoreNLPHttpRequest(final HttpExchange exchange)
    {
        queryString =  searchString = "";
        this.exchange = exchange;
        valid = parse();
    }
    
    
    public String searchText()
    {
        return searchString;
    }
    
    public boolean valid()
    {
        return valid && searchHeaderPresent();
    }
    
    private boolean parse()
    {
        try
        {
            queryString = exchange.getRequestURI().getQuery();
            searchString = queryString.replace(searchTextHeaderText, "");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
    
    private boolean searchHeaderPresent()
    {
        return queryString.startsWith(searchTextHeaderText);
    }
    
}

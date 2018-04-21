import java.util.Map;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

/**
 * Created by dakshins on 07/04/18.
 */

public class SearchHandler implements HttpHandler
{
    public SearchHandler()
    {
    }
    
    public void handle(HttpExchange e)
    {
        CoreNLPHttpRequest request = new CoreNLPHttpRequest(e);
        CoreNLPHttpResponse response = null;
        if (!request.valid())
        {
            response = CoreNLPHttpResponse.ERROR;
        }
        else
        {
            response = new CoreNLPWrapper(request.searchText()).response();
        }
        respond(e, response);
    }
    
    private void respond(HttpExchange t, CoreNLPHttpResponse response)
    {
        try
        {
            sendResponseHeaders(t, response);
            sendResponseBody(t, response);
        }
        catch (Exception ex)
        {
            //TODO : Think of cases of failures and appropriate return codes
            ex.printStackTrace();
        }
    }

    private void sendResponseBody(HttpExchange t, CoreNLPHttpResponse response) throws Exception
    {
        t.sendResponseHeaders(response.getHttpCode(), response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBody().getBytes());
        os.close();
    }

    private void sendResponseHeaders(HttpExchange t, CoreNLPHttpResponse response) throws Exception
    {
        Map<String, String > headers =response.getHeaders();
        for(String key : headers.keySet())
        {
            t.getResponseHeaders().add(key, headers.get(key));
        }
    }
}

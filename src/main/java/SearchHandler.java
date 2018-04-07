import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.OutputStream;

/**
 * Created by dakshins on 07/04/18.
 */
public class SearchHandler implements HttpHandler
{
    
    public SearchHandler()
    {
    }
    
    public void handle(HttpExchange t)
    {
        CoreNLPHttpRequest request = new CoreNLPHttpRequest(t);
        if (!request.valid())
        {
            respond(t, error(request));
        }
        respond(t, serve(request));
    }
    
    private CoreNLPHttpResponse error(CoreNLPHttpRequest request)
    {
        return CoreNLPHttpResponse.ERROR;
    }
    
    private CoreNLPHttpResponse serve(CoreNLPHttpRequest request)
    {
        return new CoreNLPWrapper(request.searchText()).response();
    }
    
    private void respond(HttpExchange t, CoreNLPHttpResponse response)
    {
        try
        {
            t.sendResponseHeaders(response.httpCode(), response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.body().getBytes());
            os.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            //How do we handle the error in writing response body.
        }
    }
}

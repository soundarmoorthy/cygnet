import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

/**
 * Created by dakshins on 07/04/18.
 */
class IndexHandler implements HttpHandler
{
    
    public IndexHandler()
    {
    }
    
    public void handle(HttpExchange t) throws IOException
    {
        String response = getRootPage();
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    
    private String getRootPage() throws IOException
    {
        URL url = Resources.getResource("index.html");
        return Resources.toString(url, Charsets.UTF_8);
    }
}
